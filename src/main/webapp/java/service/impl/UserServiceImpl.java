package service.impl;

import dao.UserMapper;
import entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import service.UserService;

import java.util.List;

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
}
