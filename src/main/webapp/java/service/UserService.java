package service;

import entity.User;

import java.util.List;

public interface UserService {
    List<User> listAll();
    boolean addUser(User user);
}
