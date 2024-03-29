package service.impl;

import entity.HistoryOrder;
import mapper.HistoryOrderMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import service.HistoryOrderService;

import java.util.List;

@Service
public class HistoryOrderServiceImpl implements HistoryOrderService {
    @Autowired
    private HistoryOrderMapper historyOrderMapper;

    @Override
    public List<HistoryOrder> listAll() {
        return historyOrderMapper.listAll();
    }

    public boolean add(HistoryOrder historyOrder){
        boolean success = false;
        int result = historyOrderMapper.insert(historyOrder);
        if (result > 0){
            success = true;
        }
        return success;
    }
}
