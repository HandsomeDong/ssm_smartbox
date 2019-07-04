package controller;

import entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import service.impl.UserServiceImpl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/test")
public class TestController {

    @Autowired
    public UserServiceImpl userService;
    @RequestMapping("/test")
    @ResponseBody
    public Map test(){
        Map<String,Object> map = new HashMap<>();
        List<User> users = userService.listAll();
        User user = users.get(0);
        ModelAndView m = new ModelAndView("index");
        m.addObject(user);
        map.put("data", user);
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