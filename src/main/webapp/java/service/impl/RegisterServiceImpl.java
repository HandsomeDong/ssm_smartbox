package service.impl;

import mapper.UserMapper;
import entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import service.RegisterService;
import service.redis.RedisUtil;

@Service
public class RegisterServiceImpl implements RegisterService {
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private RedisUtil redisUtil;

    /**
     * 把手机号作为键、验证码作为值存入redis缓存
     * @param phoneNumber
     * @return verification
     */
    @Override
    public String addRegister(String phoneNumber) {
        String verification = String.valueOf((int) Math.round(Math.random() * (9999 - 1000) + 1000));
        redisUtil.set(phoneNumber, verification, 300);
        return verification;
    }

    /**
     * 判断验证码是否正确
     * @param phoneNumber
     * @param verification
     * @return isValid
     */
    public boolean isValid(String phoneNumber, String verification) {
        boolean isValid = false;
        if (verification.equals(redisUtil.get(phoneNumber))) {
            isValid = true;
        }

        return isValid;
    }

    /**
     * 用于注册成功，删除验证码
     * @param phoneNumber
     * @return
     */
    public boolean deleteRegister(String phoneNumber) {
        redisUtil.delete(phoneNumber);
        return true;
    }

    /**
     * 判断改号码是否已经注册
     * @param phoneNumber
     * @return isRegistered
     */
    public boolean isRegistered(String phoneNumber) {
        boolean isRegistered = false;
        User user = userMapper.selectUserById(phoneNumber);
        if (user != null) {
            isRegistered = true;
        }
        return isRegistered;
    }
}
