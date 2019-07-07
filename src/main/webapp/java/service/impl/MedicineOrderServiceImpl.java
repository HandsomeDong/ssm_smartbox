package service.impl;

import mapper.MedicineOrderMapper;
import entity.MedicineOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import service.MedicineOrderService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class MedicineOrderServiceImpl implements MedicineOrderService {
    @Autowired
    private MedicineOrderMapper medicineOrderMapper;

    @Override
    public List<MedicineOrder> listAll() {
        return medicineOrderMapper.listAll();
    }

    @Override
    public boolean setTransit(String id, int bid) {
        Map<String, Object> params = new HashMap<>();
        boolean success = false;
        params.put("id", id);
        params.put("bid", bid);
        if (medicineOrderMapper.transit(params) > 0) {
            success = true;
        }
        return success;
    }

    //成功更新就返回验证码，不然返回0
    @Override
    public int setArrived(String id) {
        Map<String, Object> params = new HashMap<>();
        int verification = createVerification();
        params.put("id", id);
        params.put("verification", verification);
        if (medicineOrderMapper.arrived(params) > 0) {
            return verification;
        } else {
            return 0;
        }
    }

    @Override
    public MedicineOrder getOrderById(String id) {
        MedicineOrder medicineOrder = medicineOrderMapper.getOrderById(id);
        return medicineOrder;
    }

    @Override
    public MedicineOrder getOrderByVerification(int verification) {
        MedicineOrder medicineOrder = medicineOrderMapper.getOrderByVerification(verification);
        return medicineOrder;
    }

    @Override
    public boolean deleteOrder(String id) {
        boolean success =false;
        int result = medicineOrderMapper.deleteOrderById(id);
        if (result > 0) {
            success =true;
        }
        return success;
    }

    private boolean isUnique(int verification) {
        if (medicineOrderMapper.countVerification(verification) > 0) {
            return false;
        } else {
            return true;
        }
    }

    private int createVerification() {
        int verificaiton = (int) Math.round(Math.random() * (9999 - 1000) + 1000);
        while (!isUnique(verificaiton)) {
            verificaiton = (int) Math.round(Math.random() * (9999 - 1000) + 1000);
        }
        return verificaiton;
    }
}
