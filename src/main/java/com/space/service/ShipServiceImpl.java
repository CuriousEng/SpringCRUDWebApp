package com.space.service;

import com.space.model.Ship;
import com.space.model.ShipType;
import com.space.repository.ShipRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
@Transactional
public class ShipServiceImpl implements ShipService {

    @Autowired
    private ShipRepository shipRepository;

    public List<Ship> filterByIsUsedMinMaxSpeed(boolean isUsed, double minSpeed, double maxSpeed, Pageable pageable) {
        return shipRepository.getAllWithFiltersIsUsedMinMaxSpeed(isUsed, minSpeed, maxSpeed, pageable);
    }

    public List<Ship> filterByShipTypeBeforeMaxSpeed(ShipType shipType, Date before, double maxSpeed, Pageable pageable) {
        return shipRepository.getAllWithFiltersShipTypeBeforeMaxSpeed(shipType, before, maxSpeed, pageable);
    }

    public List<Ship> filterByShipTypeMaxCrewSize(ShipType shipType, int maxCrewSize, Pageable pageable) {
        return shipRepository.getAllWithFiltersShipTypeMaxCrewSize(shipType, maxCrewSize, pageable);
    }

    public List<Ship> filterByShipTypeIsUsed(ShipType shipType, boolean isUsed, Pageable pageable) {
        return shipRepository.getAllWithFiltersShipTypeIsUsed(shipType, isUsed, pageable);
    }

    public List<Ship> filterByNameAfterMaxRating(String name, Date after, double maxRating, Pageable pageable) {
        return shipRepository.getAllWithFiltersNameAfterMaxRating(name, after, maxRating, pageable);
    }

    public List<Ship> filterByMinRatingMinCrewSizeMinSpeed(double minRating, int minCrewSize, double minSpeed, Pageable pageable) {
        return shipRepository.getAllWithFiltersMinRatingMinCrewSizeMinSpeed(minRating, minCrewSize, minSpeed, pageable);
    }

    public List<Ship> filterByAfterBeforeMinCrewMaxCrew(Date after, Date before, int minCrewSize, int maxCrewSize, Pageable pageable) {
        return shipRepository.getAllWithFiltersAfterBeforeMinCrewMaxCrew(after, before, minCrewSize, maxCrewSize, pageable);
    }

    public List<Ship> filterByIsUsedMaxSpeedMaxRating(boolean isUsed, double maxSpeed, double maxRating, Pageable pageable) {
        return shipRepository.getAllWithFiltersIsUsedMaxSpeedMaxRating(isUsed, maxSpeed, maxRating, pageable);
    }

    public List<Ship> filterByIsUsedMinMaxRating(boolean isUsed, double minRating, double maxRating, Pageable pageable) {
        return shipRepository.getAllWithFiltersIsUsedMinMaxRating(isUsed, minRating, maxRating, pageable);
    }

    public List<Ship> filterByShipTypeMinCrewSizeMaxCrewSize(ShipType shipType, int minCrewSize, int maxCrewSize, Pageable pageable) {
        return shipRepository.getAllWithFiltersShipTypeMinCrewSizeMaxCrewSize(shipType, minCrewSize, maxCrewSize, pageable);
    }

    public List<Ship> filterByShipTypeMinSpeedMaxSpeed(ShipType shipType, double minSpeed, double maxSpeed, Pageable pageable) {
        return shipRepository.getAllWithFiltersShipTypeMinSpeedMaxSpeed(shipType, minSpeed, maxSpeed, pageable);
    }

    public List<Ship> filterByShipTypeAfterBefore(ShipType shipType, Date after, Date before, Pageable pageable) {
        return shipRepository.getAllWithFiltersShipTypeAfterBefore(shipType, after, before, pageable);
    }

    public List<Ship> filterByNameAndPage(String name, Pageable pageable) {
        return shipRepository.getAllWithFiltersNamePageNumber(name, pageable);
    }

    public List<Ship> filterByPlanetAndPage(String planet, Pageable pageable) {
        return shipRepository.getAllWithFiltersPlanetPageSize(planet, pageable);
    }

    @Override
    public List<Ship> findAll(Pageable pageable) {
        if (pageable == null) {
            return shipRepository.getAll();
        }
        return shipRepository.findAll(pageable).getContent();
    }

    @Override
    public boolean isExist(long id){
        return shipRepository.existsById(id);
    }

    @Override
    public List<Ship> getAllShips(Optional<String> name, Optional<String> planet, Optional<ShipType> shipType, Optional<Long> after, Optional<Long> before,
                                  Optional<Double> minSpeed, Optional<Double> maxSpeed, Optional<Integer> minCrewSize, Optional<Integer> maxCrewSize,
                                  Optional<Boolean> isUsed, Optional<Double> minRating, Optional<Double> maxRating, Pageable pageable) {
        List<Ship> ships;

        if (after.isPresent() && before.isPresent() && minCrewSize.isPresent() && maxCrewSize.isPresent()) {
            ships = filterByAfterBeforeMinCrewMaxCrew(new Date(after.get()), new Date(before.get()), minCrewSize.get(), maxCrewSize.get(), pageable);
        } else if (name.isPresent() && after.isPresent() && maxRating.isPresent()) {
            ships = filterByNameAfterMaxRating(name.get(), new Date(after.get()), maxRating.get(), pageable);
        } else if (shipType.isPresent() && after.isPresent() && before.isPresent()) {
            ships = filterByShipTypeAfterBefore(shipType.get(), new Date(after.get()), new Date(before.get()), pageable);
        } else if (shipType.isPresent() && minSpeed.isPresent() && maxSpeed.isPresent()) {
            ships = filterByShipTypeMinSpeedMaxSpeed(shipType.get(), minSpeed.get(), maxSpeed.get(), pageable);
        } else if (shipType.isPresent() && minCrewSize.isPresent() && maxCrewSize.isPresent()) {
            ships = filterByShipTypeMinCrewSizeMaxCrewSize(shipType.get(), minCrewSize.get(), maxCrewSize.get(), pageable);
        } else if (isUsed.isPresent() && minRating.isPresent() && maxRating.isPresent()) {
            ships = filterByIsUsedMinMaxRating(isUsed.get(), minRating.get(), maxRating.get(), pageable);
        } else if (isUsed.isPresent() && maxSpeed.isPresent() && maxRating.isPresent()) {
            ships = filterByIsUsedMaxSpeedMaxRating(isUsed.get(), maxSpeed.get(), maxRating.get(), pageable);
        } else if (minRating.isPresent() && minCrewSize.isPresent() && minSpeed.isPresent()) {
            ships = filterByMinRatingMinCrewSizeMinSpeed(minRating.get(), minCrewSize.get(), minSpeed.get(), pageable);
        } else if (shipType.isPresent() && before.isPresent() && maxSpeed.isPresent()) {
            ships = filterByShipTypeBeforeMaxSpeed(shipType.get(), new Date(before.get()), maxSpeed.get(), pageable);
        } else if (isUsed.isPresent() && minSpeed.isPresent() && maxSpeed.isPresent()) {
            ships = filterByIsUsedMinMaxSpeed(isUsed.get(), minSpeed.get(), maxSpeed.get(), pageable);
        } else if (shipType.isPresent() && isUsed.isPresent()) {
            ships = filterByShipTypeIsUsed(shipType.get(), isUsed.get(), pageable);
        } else if (shipType.isPresent() && maxCrewSize.isPresent()) {
            ships = filterByShipTypeMaxCrewSize(shipType.get(), maxCrewSize.get(), pageable);
        } else if (planet.isPresent()) {
            ships = filterByPlanetAndPage(planet.get(), pageable);
        } else if (name.isPresent()) {
            ships = filterByNameAndPage(name.get(), pageable);
        } else {
            ships = findAll(pageable);
        }
        return ships;
    }

    @Override
    public void add(Ship ship) {
        shipRepository.save(ship);
    }

    @Override
    public void delete(Long id) {
        shipRepository.deleteById(id);
    }

    @Override
    public Ship getById(long id) {
        Optional<Ship> optional = shipRepository.findById(id);
        return optional.get();
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
    public boolean isParamsNull(Ship ship){
        return ship.getName() == null || ship.getPlanet() == null || ship.getShipType() == null || ship.getProdDate() == null ||
                ship.getSpeed() == null || ship.getCrewSize() == null;
    }

    @Override
    public boolean isParamsValid(Ship ship) {
        if (ship.getName().isEmpty() || ship.getPlanet().isEmpty() || ship.getSpeed() == 0 ||
                ship.getProdDate().getYear() < 0 || ship.getCrewSize() == 0 || ship.getShipType().name().isEmpty())
            return false;
        if ((ship.getName().length() > 50 || ship.getPlanet().length() > 50) ||
            (ship.getProdDate().getYear() + 1900 < 2800 || ship.getProdDate().getYear() + 1900 > 3019) ||
            (ship.getSpeed() < 0.01 || ship.getSpeed() > 0.99) ||
            (ship.getCrewSize() < 1 || ship.getCrewSize() > 9999)) {
            return false;
        }

        return true;
    }
}
