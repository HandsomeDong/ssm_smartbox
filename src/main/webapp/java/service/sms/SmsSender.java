package service.sms;

import com.github.qcloudsms.SmsSingleSender;
import com.github.qcloudsms.httpclient.HTTPException;
import entity.SmsProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class SmsSender {
    @Autowired
    private ApplicationContext applicationContext;

    public void sendVerification(String phoneNumber, int verification){
        String[] phoneNumbers = new String[1];
        String[] params = new String[1];
        phoneNumbers[0] = phoneNumber;
        params[0] = String.valueOf(verification);

        SmsProvider smsProvider = (SmsProvider) applicationContext.getBean("registerSms");

        send(phoneNumbers, params, smsProvider);

    }

    private void send(String[] phoneNumbers, String[] params, SmsProvider smsProvider){
        SmsSingleSender ssender = new SmsSingleSender(smsProvider.getAppId(), smsProvider.getAppKey());
        try {
            ssender.sendWithParam("86", phoneNumbers[0],
                    smsProvider.getTemplateId(), params, "", "", "");// 签名参数未提供或者为空时，会使用默认签名发送短信
        } catch (HTTPException | IOException e) {
            e.printStackTrace();
        }
    }
}
