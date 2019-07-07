package service;

import entity.HistoryOrder;

import java.util.List;

public interface HistoryOrderService {
    List<HistoryOrder> listAll();
    boolean add(HistoryOrder historyOrder);
}
