import entity.HistoryOrder;
import entity.MedicineOrder;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import service.impl.HistoryOrderServiceImpl;
import service.impl.MedicineOrderServiceImpl;

import javax.annotation.Resource;

public class HistoryOrderServiceTest extends BaseTest {
    @Resource
    private HistoryOrderServiceImpl historyOrderService;
    @Resource
    private MedicineOrderServiceImpl medicineOrderService;
    @Autowired
    ApplicationContext applicationContext;

    @Override
    @Test
    public void test() {
        testAdd();
    }

    private void testAdd(){
        HistoryOrder historyOrder = (HistoryOrder) applicationContext.getBean("nullHistoryOrder");

        MedicineOrder medicineOrder = medicineOrderService.getOrderById("2");

        historyOrder.setId(medicineOrder.getId());
        historyOrder.setCreateTime(medicineOrder.getCreateTime());
        historyOrder.setMedicine(medicineOrder.getMedicine());
        historyOrder.setUid(medicineOrder.getUid());
        boolean success = historyOrderService.add(historyOrder);

        System.out.println(success);
    }
}
