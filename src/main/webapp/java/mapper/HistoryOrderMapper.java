package mapper;

import entity.HistoryOrder;

import java.util.List;

public interface HistoryOrderMapper {
    List<HistoryOrder> listAll();
    int insert(HistoryOrder historyOrder);
}
