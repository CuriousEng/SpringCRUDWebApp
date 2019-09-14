package com.space.service;

import com.space.model.Ship;
import com.space.model.ShipType;
import org.springframework.data.domain.Pageable;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Optional;

public interface ShipService {

    List<Ship> getAllShips(Optional<String>name, Optional<String> planet, Optional<ShipType> shipType, Optional<Long> after, Optional<Long> before,
                           Optional<Double> minSpeed, Optional<Double> maxSpeed, Optional<Integer> minCrewSize, Optional<Integer> maxCrewSize,
                           Optional<Boolean> isUsed, Optional<Double> minRating, Optional<Double> maxRating, Pageable pageable);
    void add(Ship ship);
    void delete(Long id);
    Ship getById(long id);
    boolean isParamsValid(Ship ship);
    boolean isParamsNull(Ship ship);
    Ship convertParamsToShip(LinkedHashMap<String, Object> params, Ship ship);
}
