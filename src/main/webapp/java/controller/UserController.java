package controller;

import com.auth0.jwt.interfaces.DecodedJWT;
import entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import service.impl.UserServiceImpl;
import service.jwt.JwtUtil;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserServiceImpl userService;
    @Autowired
    private JwtUtil jwtUtil;

    //-1表示账号密码错误  1表示登陆成功
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public Map login(String userId, String password) {
        Map<String, Object> result = new HashMap<>();
        User user = userService.login(userId, password);
        if (user == null) {
            result.put("status", -1);
        } else {
            result.put("token", jwtUtil.createToken(userId));
            result.put("userData", user);
            result.put("status", 1);
        }
        return result;
    }

    @RequestMapping(value = "/data", method = RequestMethod.GET)
    public Map getUserData(HttpServletRequest request){
        Map<String, Object> result = new HashMap<>();
        DecodedJWT parse = jwtUtil.parseToken(request.getHeader("token"));
        String userId = parse.getClaim("userId").asString();
        User user = userService.getUserData(userId);
        if (user == null) {
            result.put("status", -1);
        } else {
            result.put("userData", user);
            result.put("status", 1);
        }
        return result;
    }
}
