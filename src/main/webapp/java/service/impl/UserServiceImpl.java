package service.impl;

import entity.HistoryOrder;
import mapper.UserMapper;
import entity.MedicineOrder;
import entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import service.UserService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public List<User> listAll(){
        List<User> users = userMapper.listAll();
        return users;
    }

    @Override
    public boolean addUser(User user) {
        boolean success = false;
        int result = userMapper.addUser(user);
        if (result > 0) {
            success = true;
        }
        return success;
    }

    public User getUserData(String id){
        User user = userMapper.selectUserById(id);
        return user;
    }

    @Override
    public User login(User user){
        Map<String, String> params = new HashMap<>();
        params.put("id", user.getId());
        params.put("password", user.getPassword());
        User userData = userMapper.login(params);
        return userData;
    }

    @Override
    public List<MedicineOrder> getMedicineOrders(String id) {
        User user = userMapper.getUserWithMedicineOrders(id);
        List<MedicineOrder> medicineOrders = user.getMedicineOrders();
        return medicineOrders;
    }

    @Override
    public List<HistoryOrder> getHistoryOrders(String id) {
        User user = userMapper.getUserWithHistoryOrders(id);
        List<HistoryOrder> historyOrders = user.getHistoryOrders();
        return historyOrders;
    }
}
