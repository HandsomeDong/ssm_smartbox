import entity.HistoryOrder;
import entity.MedicineOrder;
import entity.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import service.HistoryOrderService;
import service.impl.HistoryOrderServiceImpl;
import service.impl.UserServiceImpl;

import javax.annotation.Resource;
import java.util.List;


public class UserServiceTest extends BaseTest{
    @Resource
    private UserServiceImpl userService;
    @Resource
    private HistoryOrderServiceImpl historyOrderService;

    @Test
    @Override
    public void test() {
        List<HistoryOrder> historyOrders = userService.getHistoryOrders("18814215401");
        for (HistoryOrder historyOrder : historyOrders){
            System.out.println(historyOrder.getId());
        }
    }

    @Test
    public void testH() {
        List<HistoryOrder> historyOrders = userService.getHistoryOrders("18814215401");
        for (HistoryOrder historyOrder : historyOrders){
            System.out.println(historyOrder.getId());
        }
    }

    @Test
    public void testM() {
        List<MedicineOrder> medicineOrders = userService.getMedicineOrders("18814215401");

    }
}
