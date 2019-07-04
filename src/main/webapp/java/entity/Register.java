package entity;

import org.springframework.stereotype.Component;

@Component("register")
public class Register {
    private String phoneNumber;
    private int verification;

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public int getVerification() {
        return verification;
    }

    public void setVerification(int verification) {
        this.verification = verification;
    }
}
