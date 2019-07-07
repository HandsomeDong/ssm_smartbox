import entity.MedicineOrder;
import org.junit.Test;
import service.impl.MedicineOrderServiceImpl;

import javax.annotation.Resource;

public class MedicineOrderServiceTest extends BaseTest{
    @Resource
    private MedicineOrderServiceImpl medicineOrderService;

    @Test
    @Override
    public void test() {
        testDeleteOrder();
    }

    private void testSetTransit(){
        boolean success = medicineOrderService.setTransit("1", 4);
        System.out.println(success);
    }

    private void testSetArrived(){
        int verification = medicineOrderService.setArrived("1");
        System.out.println(verification);
    }

    private void testGetOrderById(){
        MedicineOrder medicineOrder = medicineOrderService.getOrderById("2");
        System.out.println(medicineOrder.getUid());
    }

    private void testGetOrderByVerification(){
        MedicineOrder medicineOrder = medicineOrderService.getOrderByVerification(5681);
        System.out.println(medicineOrder.getUid());
    }

    private void testDeleteOrder(){
        boolean success = medicineOrderService.deleteOrder("1");
        System.out.println(success);
    }
}
