package controller;

import entity.Register;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import service.impl.RegisterServiceImpl;
import service.sms.SmsSender;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/register")
public class RegisterController {

    @Autowired
    private SmsSender smsSender;
    @Autowired
    private RegisterServiceImpl registerService;

    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public Map register(String phoneNumber, String verification, String password, String userName) {
        Map<String, Object> result = new HashMap<>();
        return result;
    }

    //    status状态，-1则表示该手机号已经注册，0表示未知错误，1表示发送验证码成功
    @RequestMapping(value = "/sendverification", method = RequestMethod.GET)
    public Map sendVerification(String phoneNumber) {
        Map<String, Object> result = new HashMap<>();

        if (phoneNumber == null) {
            result.put("status", 0);
            return result;
        }

        if (registerService.isRegistered(phoneNumber)) {
            result.put("status", -1);
        } else {
            int verification = registerService.addRegister(phoneNumber);
            if (verification != 0) {
                smsSender.sendVerification("18814215401", verification);
                result.put("status", 1);
            } else {
                result.put("status", 0);
            }
        }

        return result;
    }

}
