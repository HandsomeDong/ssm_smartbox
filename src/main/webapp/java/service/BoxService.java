package service;

import entity.Box;

import java.util.List;

public interface BoxService {
    int[] getEmptyBoxes();
    boolean setFull(int id);
    boolean setEmpty(int id);
}
