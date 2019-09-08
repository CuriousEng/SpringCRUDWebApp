package com.space.repository;

import com.space.model.Ship;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

public class ShipDAOImpl implements ShipDAO{
    private final static AtomicLong AUTO_ID = new AtomicLong(0);
    private static Map<Long, Ship> ships = new HashMap<>();

    @Override
    public List<Ship> allShips() {
        return new ArrayList<>(ships.values());
    }

    @Override
    public void add(Ship ship) {
        ship.setId(AUTO_ID.getAndIncrement());
        ships.put(ship.getId(), ship);
    }

    @Override
    public void delete(Long id) {
        ships.remove(id);
    }

    @Override
    public void edit(Long id) {

    }

    @Override
    public Ship getById(long id) {
         return ships.get(id);
    }
}
