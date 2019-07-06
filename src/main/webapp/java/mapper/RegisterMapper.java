package mapper;

import entity.Register;

import java.util.List;
import java.util.Map;

public interface RegisterMapper {
    int addRegister(Register register);
    List<Register> getRegisterByVerification(int verification);
    List<Register> getRegisterByPhoneNumber(String phoneNumber);
    int updateRegister(Register register);
    int verify(Map params);
    int deleteRegister(String phoneNumber);
}
