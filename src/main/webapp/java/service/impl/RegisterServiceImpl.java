package service.impl;

import mapper.RegisterMapper;
import mapper.UserMapper;
import entity.Register;
import entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;
import service.RegisterService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class RegisterServiceImpl implements RegisterService {
    @Autowired
    private RegisterMapper registerMapper;
    @Autowired
    private ApplicationContext applicationContext;
    @Autowired
    private UserMapper userMapper;

    //    返回非0数字则是插入成功
    @Override
    public int addRegister(String phoneNumber) {
        Register register = (Register) applicationContext.getBean("register");
        register.setPhoneNumber(phoneNumber);

        int verification = createVerification();
        register.setVerification(verification);
        int success = 0;

        if (registerMapper.getRegisterByPhoneNumber(phoneNumber).size() > 0) {
            success = updateRegister(register);
        } else {
            success = registerMapper.addRegister(register);
        }

        if (success != 0) {
            return verification;
        } else {
            return 0;
        }
    }

    //判断验证码是否正确
    public boolean isValid(String phoneNumber, int verification) {
        boolean isValid = false;

        Map<String, Object> params = new HashMap<>();
        params.put("phoneNumber", phoneNumber);
        params.put("verification", verification);
        int count = registerMapper.verify(params);
        if (count == 1){
            isValid = true;
        }
        return isValid;
    }


    public boolean deleteRegister(String phoneNumber){
        boolean success = false;
        int result = registerMapper.deleteRegister(phoneNumber);
        if (result > 0) {
            success = true;
        }
        return success;
    }

    private int updateRegister(Register register) {
        int success = registerMapper.updateRegister(register);
        return  success;
    }

    private boolean isUnique(int verification) {
        boolean isUnique = true;
        List<Register> registers = registerMapper.getRegisterByVerification(verification);
        if (registers.size() > 0) {
            isUnique = false;
        }
        return isUnique;
    }

    public boolean isRegistered(String phoneNumber) {
        boolean isRegistered = false;
        User user = userMapper.selectUserById(phoneNumber);
        if (user != null) {
            isRegistered = true;
        }
        return isRegistered;
    }

    private int createVerification() {
        int verificaiton = (int) Math.round(Math.random() * (9999 - 1000) + 1000);
        while (!isUnique(verificaiton)) {
            verificaiton = (int) Math.round(Math.random() * (9999 - 1000) + 1000);
        }

        return verificaiton;
    }
}
