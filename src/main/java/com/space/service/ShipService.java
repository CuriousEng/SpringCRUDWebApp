package com.space.service;

import com.space.model.Ship;

import java.util.LinkedHashMap;
import java.util.List;

public interface ShipService {

    List<Ship> allShips();
    void add(Ship ship);
    void delete(Long id);
    void edit(Long id);
    Ship getById(long id);
    boolean isParamsValid(LinkedHashMap<String, Object> params);
    Ship convertParamsToShip(LinkedHashMap<String, Object> params, Ship ship);
}
