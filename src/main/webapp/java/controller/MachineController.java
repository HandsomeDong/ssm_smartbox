package controller;

import entity.HistoryOrder;
import entity.MedicineOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import service.impl.BoxServiceImpl;
import service.impl.HistoryOrderServiceImpl;
import service.impl.MedicineOrderServiceImpl;
import service.sms.SmsSender;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/machine")
public class MachineController {
    @Autowired
    private BoxServiceImpl boxService;
    @Autowired
    private MedicineOrderServiceImpl medicineOrderService;
    @Autowired
    private HistoryOrderServiceImpl historyOrderService;
    @Autowired
    private SmsSender smsSender;
    @Autowired
    private ApplicationContext context;

    /*
    获取所有空柜子的接口
     */
    @RequestMapping(value = "/box", method = RequestMethod.GET)
    public Map getEmptyBoxes() {
        Map<String, Object> result = new HashMap<>();
        int[] emptyBoxesIds = boxService.getEmptyBoxes();
        result.put("data", emptyBoxesIds);
        return result;
    }

    /*
    更新订单状态为正在运送
     */
    @RequestMapping(value = "/order/transit", method = RequestMethod.GET)
    public Map setOrderTransit(@RequestParam(value = "id") String medicineOrderId,
                               @RequestParam(value = "box") String boxId) {
        Map<String, Object> result = new HashMap<>();
        int bid = Integer.valueOf(boxId);
        if (medicineOrderService.setTransit(medicineOrderId, bid) && boxService.setFull(bid)) {
            result.put("status", 1);
        } else {
            result.put("status", -1);
        }
        return result;
    }

    /*
    更新订单状态为已经到柜
     */
    @RequestMapping(value = "/order/arrived", method = RequestMethod.GET)
    public Map setOrderArrived(@RequestParam(value = "id") String medicineOrderId) {
        Map<String, Object> result = new HashMap<>();
        int verification = medicineOrderService.setArrived(medicineOrderId);
        if (verification != 0) {
            MedicineOrder medicineOrder = medicineOrderService.getOrderById(medicineOrderId);
            smsSender.sendMedicineVerification(medicineOrder.getUid(), verification, medicineOrder.getBid());
            result.put("status", 1);
        } else {
            result.put("status", -1);
        }
        return result;
    }

    /*
    通过验证码获取柜子号
     */
    @RequestMapping(value = "/order/verify", method = RequestMethod.GET)
    public Map getOrderBoxId(@RequestParam(value = "verification") String verificationStr) {
        Map<String, Object> result = new HashMap<>();
        int verification = Integer.valueOf(verificationStr);
        MedicineOrder medicineOrder = medicineOrderService.getOrderByVerification(verification);
        if (medicineOrder == null) {
            result.put("status", -1);
        } else {
            result.put("status", 1);
            result.put("boxId", medicineOrder.getBid());
            result.put("medicine", medicineOrder.getMedicine());
        }
        return result;
    }

    /*
    删除当前订单，并归档到历史订单，再把柜子号状态置0
     */
    @RequestMapping(value = "/order/delete", method = RequestMethod.GET)
    public Map deleteOrder(@RequestParam(value = "id") String medicineOrderId) {
        Map<String, Object> result = new HashMap<>();
        MedicineOrder medicineOrder = medicineOrderService.getOrderById(medicineOrderId);
        if (medicineOrderService.deleteOrder(medicineOrderId)) {
            HistoryOrder historyOrder = (HistoryOrder) context.getBean("nullHistoryOrder");
            historyOrder.setId(medicineOrder.getId());
            historyOrder.setCreateTime(medicineOrder.getCreateTime());
            historyOrder.setMedicine(medicineOrder.getMedicine());
            historyOrder.setUid(medicineOrder.getUid());
            if (historyOrderService.add(historyOrder) && boxService.setEmpty(medicineOrder.getBid())) {
                smsSender.sendFinish(medicineOrder.getUid(), medicineOrder.getId());
                result.put("status", 1);
            } else {
                result.put("status", -1);
            }
        } else {
            result.put("status", -1);
        }
        return result;
    }
}
