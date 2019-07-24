import org.junit.Test;
import service.impl.RegisterServiceImpl;
import service.sms.SmsSender;

import javax.annotation.Resource;

public class RegisterServiceTest extends BaseTest {
    @Resource
    private RegisterServiceImpl registerService;
    @Resource
    private SmsSender smsSender;

    @Test
    @Override
    public void test() {
        String phoneNumber = "18814215401";
        String verification = registerService.addRegister(phoneNumber);
        smsSender.sendRegisterVerification(phoneNumber, verification);
    }

    @Test
    public void testValid(){
        String phoneNumber = "18814215401";
        String verification = "2806";
        System.out.println(registerService.isValid(phoneNumber, verification));
    }

    @Test
    public void testDelete(){
        String phoneNumber = "18814215401";
        System.out.println(registerService.deleteRegister(phoneNumber));
    }
}
