import entity.User;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import service.UserService;
import service.impl.UserServiceImpl;

import javax.annotation.Resource;
import java.util.List;

public class TestUserDao {
    @Autowired
    UserServiceImpl userService;

    @Test
    public void testUserDao(){
        UserServiceImpl userService = new UserServiceImpl();
        List<User> users = userService.listAll();
    }

}
