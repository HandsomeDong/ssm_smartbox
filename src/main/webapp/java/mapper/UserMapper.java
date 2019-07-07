package mapper;

import entity.User;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface UserMapper {
    List<User> listAll();

    int addUser(User user);

    User selectUserById(String id);

    User login(Map params);

    User getUserWithMedicineOrders(String id);

    User getUserWithHistoryOrders(String id);
}
