package mapper;

import entity.MedicineOrder;

import java.util.List;
import java.util.Map;

public interface MedicineOrderMapper {
    List<MedicineOrder> listAll();
    int transit(Map params);
    int arrived(Map params);
    int countVerification(int verification);
    MedicineOrder getOrderById(String id);
    MedicineOrder getOrderByVerification(int verification);
    int deleteOrderById(String id);
}
