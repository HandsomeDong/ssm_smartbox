package service.impl;

import dao.MedicineOrderMapper;
import entity.MedicineOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import service.MedicineOrderService;

import java.util.List;

@Service
public class MedicineOrderServiceImpl implements MedicineOrderService {
    @Autowired
    private MedicineOrderMapper medicineOrderMapper;

    @Override
    public List<MedicineOrder> listAll() {
        return medicineOrderMapper.listAll();
    }
}
