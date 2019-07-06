package controller;

import entity.MedicineOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import service.impl.MedicineOrderServiceImpl;
import service.impl.UserServiceImpl;
import service.jwt.JwtUtil;
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
    @Autowired
    private MedicineOrderServiceImpl medicineOrderService;

    @RequestMapping("/test1")
    @ResponseBody
    public Map test(){
        Map<String,Object> map = new HashMap<>();

        List<MedicineOrder> medicineOrders = medicineOrderService.listAll();
        map.put("orders", medicineOrders);
        return map;

    }

    @RequestMapping("/test2")
    @ResponseBody
    public Map testJson(){
        Map<String,Object> map = new HashMap<>();
        List<MedicineOrder> medicineOrders = userService.getMedicineOrders("18814215401");
        map.put("medicineOrders", medicineOrders);
        return map;
    }
}