package com.space.service;

import com.space.model.Ship;
import com.space.model.ShipType;
import com.space.repository.ShipDAO;
import com.space.repository.ShipDAOImpl;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.TimeZone;

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
    public void delete(Ship ship) {

    }

    @Override
    public void edit(Ship ship) {

    }

    @Override
    public Ship getById(long id) {
        return null;
    }

    public Ship convertParamsToShip(LinkedHashMap<String, Object> params){
        Ship ship = new Ship();
        try {
            Date date = new Date((Long) params.get("prodDate"));
            Double speed = (double) Math.round(Double.parseDouble((String) params.get("speed")) * 100) / 100;
            Integer crewSize = Integer.parseInt((String) params.get("srewSize"));
            ship.setName((String)params.get("name"));
            ship.setPlanet((String)params.get("planet"));
            ship.setShipType((ShipType) params.get("shipType"));
            ship.setProdDate(date);
            ship.setSpeed(speed);
            ship.setCrewSize(crewSize);
            ship.setRating();
            if (params.containsKey("isUsed")){
                ship.setUsed((boolean)params.get("isUsed"));
            }
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
        Ship ship = convertParamsToShip(params);
        if ((ship.getProdDate().getYear() >= 2800 && ship.getProdDate().getYear() <= 3019) &&
                (ship.getCrewSize() >= 1 && ship.getCrewSize() <= 9999) &&
                (ship.getSpeed() >= 0.01 && ship.getSpeed() <= 0.99)) {
            return true;
        }
        return false;
    }
}
