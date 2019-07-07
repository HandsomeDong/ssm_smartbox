package service;

import entity.HistoryOrder;
import entity.MedicineOrder;
import entity.User;

import java.util.List;

public interface UserService {
    List<User> listAll();
    boolean addUser(User user);
    User getUserData(String id);
    User login(String id, String password);
    List<MedicineOrder> getMedicineOrders(String id);
    List<HistoryOrder> getHistoryOrders(String id);
}
