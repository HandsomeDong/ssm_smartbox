package controller;

import entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import service.impl.RegisterServiceImpl;
import service.impl.UserServiceImpl;
import service.sms.SmsSender;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/register")
public class RegisterController {
    @Autowired
    private ApplicationContext context;
    @Autowired
    private SmsSender smsSender;
    @Autowired
    private RegisterServiceImpl registerService;
    @Autowired
    private UserServiceImpl userService;

    //status    1注册成功  0未知错误   -1验证码错误
    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public Map register(@RequestBody Map<String, Object> params) {
        Map<String, Object> result = new HashMap<>();
        User user = (User) context.getBean("user");
        String id = (String) params.get("id");
        String password = (String) params.get("password");
        String name = (String) params.get("name");
        String verification = (String) params.get("verification");
        user.setId(id);
        user.setPassword(password);
        user.setName(name);
        boolean isValid = registerService.isValid(user.getId(), verification);
        if (isValid) {
            boolean successAdd = userService.addUser(user);
            boolean successDelete = registerService.deleteRegister(user.getId());
            if (successAdd && successDelete) {
                result.put("status", 1);
            } else {
                result.put("status", 0);
            }
        } else {
            result.put("status", -1);
        }

        return result;
    }

    //    status状态，-1则表示该手机号已经注册，0表示未知错误，1表示发送验证码成功
    @RequestMapping(value = "/send", method = RequestMethod.GET)
    public Map sendVerification(String phoneNumber) {
        Map<String, Object> result = new HashMap<>();

        if (phoneNumber == null) {
            result.put("status", 0);
            return result;
        }

        if (registerService.isRegistered(phoneNumber)) {
            result.put("status", -1);
        } else {
            String verification = registerService.addRegister(phoneNumber);
            smsSender.sendRegisterVerification(phoneNumber, verification);
            result.put("status", 1);
        }
        return result;
    }

}
