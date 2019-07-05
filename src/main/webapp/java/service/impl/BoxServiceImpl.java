package service.impl;

import dao.BoxMapper;
import entity.Box;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import service.BoxService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class BoxServiceImpl implements BoxService {
    private static final int EMPTY = 0;
    private static final int FULL = 1;

    @Autowired
    private BoxMapper boxMapper;

    @Override
    public int[] getEmptyBoxes() {
        List<Box> emptyBoxes = boxMapper.selectByStatus(EMPTY);
        int[] emptyBoxesIds = new int[emptyBoxes.size()];
        int i = 0;
        for (Box box : emptyBoxes) {
            emptyBoxesIds[i] = box.getId();
            i++;
        }
        return emptyBoxesIds;
    }

    @Override
    public boolean setFull(int id) {
        boolean success = setStatus(id, FULL);
        return success;
    }

    @Override
    public boolean setEmpty(int id) {
        boolean success = setStatus(id, EMPTY);
        return success;
    }


    private boolean setStatus(int id, int status){
        boolean success = false;
        Map<String, Object> params = new HashMap<>();
        params.put("id", id);
        params.put("status", status);

        int result = boxMapper.updateStatus(params);

        if (result > 0) {
            success = true;
        }
        return success;
    }
}
