package dao;

import entity.User;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserMapper {
    List<User> listAll();

    int addUser(User user);

    List<User> selectUser(String id);
}
