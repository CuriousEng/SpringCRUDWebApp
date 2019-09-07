package com.space.repository;

import com.space.model.Ship;

import java.util.List;

public interface ShipDAO {

    List<Ship> allShips();
    void add(Ship ship);
    void delete(Long id);
    void edit(Ship ship);
    Ship getById(long id);
}
