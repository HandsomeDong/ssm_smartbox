package controller;


import entity.HistoryOrder;
import entity.MedicineOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import service.impl.UserServiceImpl;
import service.jwt.JwtUtil;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/order")
public class OrderController {
    @Autowired
    private UserServiceImpl userService;
    @Autowired
    private JwtUtil jwtUtil;

    @RequestMapping(value = "/medicine", method = RequestMethod.GET)
    public Map getMedicineOrders(HttpServletRequest request, String token){
        Map<String,Object> map = new HashMap<>();
        String userId = jwtUtil.getUserIdByToken(request.getHeader("token"));
        List<MedicineOrder> medicineOrders = userService.getMedicineOrders(userId);
        map.put("medicineOrders", medicineOrders);
        return map;
    }

    @RequestMapping(value = "/history", method = RequestMethod.GET)
    public Map getHistoryOrders(HttpServletRequest request, String token){
        Map<String,Object> map = new HashMap<>();
        String userId = jwtUtil.getUserIdByToken(request.getHeader("token"));
        List<HistoryOrder> historyOrders = userService.getHistoryOrders(userId);
        map.put("historyOrders", historyOrders);
        return map;
    }
}
