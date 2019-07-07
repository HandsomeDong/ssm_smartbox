package service;

import entity.MedicineOrder;

import java.util.List;

public interface MedicineOrderService {
    List<MedicineOrder> listAll();
    boolean setTransit(String id, int bid);
    int setArrived(String id);
    MedicineOrder getOrderById(String id);
    MedicineOrder getOrderByVerification(int verification);
    boolean deleteOrder(String id);
}
