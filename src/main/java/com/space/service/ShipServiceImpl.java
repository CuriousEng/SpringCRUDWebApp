package com.space.service;

import com.space.model.Ship;
import com.space.model.ShipType;
import com.space.repository.ShipDAO;
import com.space.repository.ShipDAOImpl;

import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;

public class ShipServiceImpl implements ShipService {
    private ShipDAO shipDAO = new ShipDAOImpl();

    @Override
    public List<Ship> allShips() {
        return shipDAO.allShips();
    }

    @Override
    public void add(Ship ship) {
        shipDAO.add(ship);
    }

    @Override
    public void delete(Long id) {
        shipDAO.delete(id);
    }

    @Override
    public void edit(Long id) {
        shipDAO.edit(id);
    }

    @Override
    public Ship getById(long id) {
         return shipDAO.getById(id);
    }

    public Ship convertParamsToShip(LinkedHashMap<String, Object> params, Ship ship){
        ship = (ship == null) ? new Ship() : ship;
        try {
            if (params.containsKey("name") && params.get("name") != "" && params.get("name").toString().length() <= 50) {
                ship.setName((String)params.get("name")); }
            if (params.containsKey("planet") && params.get("planet") != "" && params.get("planet").toString().length() <= 50) {
                ship.setPlanet((String)params.get("planet")); }
            if (params.containsKey("shipType")) {
                ship.setShipType(ShipType.valueOf((String)params.get("shipType"))); }
            if (params.containsKey("prodDate")) {
                Date date = new Date((Long) params.get("prodDate"));
                if (date.getYear() + 1900 >= 2800 && date.getYear() + 1900 <= 3019) {
                    ship.setProdDate(date); }
            }
            if (params.containsKey("isUsed")){
                ship.setIsUsed((boolean)params.get("isUsed")); }
            if (params.containsKey("speed")) {
                Double speed = (double) Math.round(Double.parseDouble((String) params.get("speed")) * 100) / 100;
                if (speed >= 0.01 && speed <= 0.99) {
                    ship.setSpeed(speed);
                }
            }
            if (params.containsKey("crewSize")) {
                Integer crewSize = Integer.parseInt((String) params.get("crewSize"));
                if (crewSize >= 1 && crewSize <= 9999) {
                    ship.setCrewSize(crewSize);
                }
            }
            ship.setRating();
        } catch (Exception e) {
            System.out.println("Can`t parse value from request object");
            e.printStackTrace();
        }
        return ship;
    }

    @Override
    public boolean isParamsValid(LinkedHashMap<String, Object> params) {
        if (params.get("name") == null || params.get("name") == "" || params.get("name").toString().length() > 50
                || params.get("planet") == null || params.get("planet") == "" || params.get("planet").toString().length() > 50
                || params.get("shipType") == null || params.get("prodDate") == null || params.get("speed") == null
                || params.get("crewSize") == null) {
            return false;
        }
        Ship ship = convertParamsToShip(params, null);
        if ((ship.getProdDate().getYear() + 1900 >= 2800 && ship.getProdDate().getYear() + 1900 <= 3019) &&
                (ship.getCrewSize() >= 1 && ship.getCrewSize() <= 9999) &&
                (ship.getSpeed() >= 0.01 && ship.getSpeed() <= 0.99)) {
            return true;
        }
        return false;
    }
}
