package entity;

import java.util.List;

public class User {
    private String id;

    private String password;

    private String name;

    private List<MedicineOrder> medicineOrders;

    private List<HistoryOrder> historyOrders;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String userName) {
        this.name = userName;
    }

    public List<MedicineOrder> getMedicineOrders() {
        return medicineOrders;
    }

    public void setMedicineOrders(List<MedicineOrder> medicineOrders) {
        this.medicineOrders = medicineOrders;
    }

    public List<HistoryOrder> getHistoryOrders() {
        return historyOrders;
    }

    public void setHistoryOrders(List<HistoryOrder> historyOrders) {
        this.historyOrders = historyOrders;
    }
}
