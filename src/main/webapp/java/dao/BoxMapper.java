package dao;

import entity.Box;

import java.util.List;
import java.util.Map;

public interface BoxMapper {
    List<Box> selectByStatus(int status);
    int updateStatus(Map params);
}
