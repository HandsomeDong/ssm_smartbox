package controller;

import com.auth0.jwt.impl.JWTParser;
import com.auth0.jwt.interfaces.DecodedJWT;
import entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import service.impl.UserServiceImpl;
import service.jwt.JwtUtil;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/test")
public class TestController {

    @Autowired
    private UserServiceImpl userService;
    @Autowired
    public JwtUtil jwtUtil;

    @RequestMapping("/test")
    @ResponseBody
    public Map test(){
        Map<String,Object> map = new HashMap<>();

        String test = jwtUtil.createToken("18814215401");
        DecodedJWT parse = jwtUtil.parseToken(test);
        String userId = parse.getClaim("userId").asString();
        Date date = parse.getExpiresAt();

        return map;

    }

    @RequestMapping("/testjson")
    @ResponseBody
    public Map testJson(){
        Map<String,Object> map = new HashMap<>();
        List<User> users = userService.listAll();
        User user = users.get(0);
        map.put("message", "success");
        map.put("data", user);
        return map;
    }
}