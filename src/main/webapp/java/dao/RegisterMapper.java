package dao;

import entity.Register;

import java.util.List;

public interface RegisterMapper {
    int addRegister(Register register);
    List<Register> getRegisterByVerification(int verification);
    List<Register> getRegisterByPhoneNumber(String phoneNumber);
    int updateRegister(Register register);
}
