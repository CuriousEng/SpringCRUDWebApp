package com.space.service;

import com.space.model.Ship;
import com.space.repository.ShipDAO;
import com.space.repository.ShipDAOImpl;

import java.util.List;

public class ShipServiceImpl implements ShipService {
    private ShipDAO shipDAO = new ShipDAOImpl();

    @Override
    public List<Ship> allShips() {
        return shipDAO.allShips();
    }

    @Override
    public void add(Ship ship) {

    }

    @Override
    public void delete(Ship ship) {

    }

    @Override
    public void edit(Ship ship) {

    }

    @Override
    public Ship getById(long id) {
        return null;
    }
}
