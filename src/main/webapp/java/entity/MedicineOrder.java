package entity;

import java.sql.Timestamp;

public class MedicineOrder {
    //订单编号
    private String id;
    //订单状态，1正在配药，2正在运送，3在柜子里
    private int status;
    //所在柜子编号，0表示正在配药，还没有分配柜子
    private int bid;
    //验证码
    private int verification;
    //具体药品
    private String medicine;
    //用户ID，即手机号
    private String uid;
    //最后更新的时间
    private Timestamp updateTime;
    //订单创建的时间
    private Timestamp createTime;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getBid() {
        return bid;
    }

    public void setBid(int bid) {
        this.bid = bid;
    }

    public int getVerification() {
        return verification;
    }

    public void setVerification(int verification) {
        this.verification = verification;
    }

    public String getMedicine() {
        return medicine;
    }

    public void setMedicine(String medicine) {
        this.medicine = medicine;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public Timestamp getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Timestamp updateTime) {
        this.updateTime = updateTime;
    }

    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }
}
