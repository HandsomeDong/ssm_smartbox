import entity.User;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import service.impl.UserServiceImpl;
import service.sms.SmsSender;

import java.util.List;

public class TestSms {

    @Autowired
    private SmsSender smsSender;

    @Test
    public void test(){
        smsSender.sendVerification("18814215401", 2019);
    }
}
