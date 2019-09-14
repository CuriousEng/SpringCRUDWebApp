package com.space.controller;

import com.space.model.Ship;
import com.space.model.ShipType;
import com.space.repository.ShipRepository;
import com.space.service.JsonConverterService;
import com.space.service.ShipService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Optional;

@Controller
public class ShipController {

    @Autowired
    ShipService shipService;

    @Autowired
    ShipRepository shipRepository;

    //CREATE
    @RequestMapping(value = "rest/ships", method = RequestMethod.POST)
    public ResponseEntity createShip(@RequestBody Ship ship, @RequestParam(value = "isUsed") Optional<Boolean> isUsed) {
        if (!shipService.isParamsNull(ship) && shipService.isParamsValid(ship)) {
            ship.setIsUsed(ship.getIsUsed() == null ? false : ship.getIsUsed());
            ship.setRating();
            shipService.add(ship);
            return new ResponseEntity(ship, HttpStatus.OK);
        }
        return new ResponseEntity(HttpStatus.BAD_REQUEST);
    }

    //GET
    @RequestMapping(value = "rest/ships/{id}", method = RequestMethod.GET)
    public ResponseEntity getShip(@PathVariable("id") long id) {
        try{
            shipRepository.findById(id).get();
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
        return shipService.getById(id) == null ?
                new ResponseEntity(HttpStatus.NOT_FOUND) :
                new ResponseEntity(JsonConverterService.toJSON(shipService.getById(id)),HttpStatus.OK);
    }
    //DELETE
    @RequestMapping(value = "rest/ships/{id}", method = RequestMethod.DELETE)
    public ResponseEntity deleteShip(@PathVariable("id") long id) {
        try{
            shipRepository.findById(id).get();
        } catch (Exception e) {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
        if (shipService.getById(id) == null) {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        } else {
            shipService.delete(id);
            return new ResponseEntity(JsonConverterService.toJSON(shipService.getById(id)),HttpStatus.OK);
        }
    }
    //UPDATE
    @RequestMapping(value = "rest/ships/{id}", method = RequestMethod.POST)
    public ResponseEntity editShip(@PathVariable("id") long id, @RequestBody LinkedHashMap<String, Object> params) {
        try{
            shipRepository.findById(id).get();
        } catch (Exception e) {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
        if (shipService.getById(id) == null) {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        } else {
            Ship ship = shipService.getById(id);
            shipService.convertParamsToShip(params, ship);
            return new ResponseEntity(JsonConverterService.toJSON(shipService.getById(id)),HttpStatus.OK);
        }
    }

    @RequestMapping(value = "rest/ships", method = RequestMethod.GET)
    public ResponseEntity<List<Ship>> getFiltredShips(@RequestParam(value = "pageNumber") Optional<Integer> page,
                                               @RequestParam(value = "pageSize")   Optional<Integer> pageSize,
                                               @RequestParam(value = "order")      Optional<ShipOrder> shipOrder,
                                               @RequestParam(value = "name")       Optional<String> name,
                                               @RequestParam(value = "planet")     Optional<String> planet,
                                               @RequestParam(value = "shipType")   Optional<ShipType> shipType,
                                               @RequestParam(value = "after")      Optional<Long> after,
                                               @RequestParam(value = "before")     Optional<Long> before,
                                               @RequestParam(value = "minSpeed")   Optional<Double> minSpeed,
                                               @RequestParam(value = "maxSpeed")   Optional<Double> maxSpeed,
                                               @RequestParam(value = "minCrewSize")Optional<Integer> minCrewSize,
                                               @RequestParam(value = "maxCrewSize")Optional<Integer> maxCrewSize,
                                               @RequestParam(value = "isUsed")     Optional<Boolean> isUsed,
                                               @RequestParam(value = "minRating")  Optional<Double> minRating,
                                               @RequestParam(value = "maxRating")  Optional<Double> maxRating) {

        String order = shipOrder.isPresent() ? shipOrder.get().getFieldName() : "id";
        Pageable pageable = new PageRequest(page.orElse(0), pageSize.orElse(3), Sort.by(order));
        return new ResponseEntity<>(shipService.getAllShips(name, planet, shipType, after,
                before, minSpeed, maxSpeed, minCrewSize, maxCrewSize,
                isUsed, minRating, maxRating, pageable), HttpStatus.OK);
    }

    @RequestMapping(value = "rest/ships/count", method = RequestMethod.GET)
    public ResponseEntity<Integer> getShipsCount (@RequestParam(value = "pageNumber") Optional<Integer> page,
                                                    @RequestParam(value = "pageSize")   Optional<Integer> pageSize,
                                                    @RequestParam(value = "order")      Optional<ShipOrder> shipOrder,
                                                    @RequestParam(value = "name")       Optional<String> name,
                                                    @RequestParam(value = "planet")     Optional<String> planet,
                                                    @RequestParam(value = "shipType")   Optional<ShipType> shipType,
                                                    @RequestParam(value = "after")      Optional<Long> after,
                                                    @RequestParam(value = "before")     Optional<Long> before,
                                                    @RequestParam(value = "minSpeed")   Optional<Double> minSpeed,
                                                    @RequestParam(value = "maxSpeed")   Optional<Double> maxSpeed,
                                                    @RequestParam(value = "minCrewSize")Optional<Integer> minCrewSize,
                                                    @RequestParam(value = "maxCrewSize")Optional<Integer> maxCrewSize,
                                                    @RequestParam(value = "isUsed")     Optional<Boolean> isUsed,
                                                    @RequestParam(value = "minRating")  Optional<Double> minRating,
                                                    @RequestParam(value = "maxRating")  Optional<Double> maxRating) {
        String order = shipOrder.isPresent() ? shipOrder.get().getFieldName() : "id";
        return new ResponseEntity<>(shipService.getAllShips(name, planet, shipType, after,
                before, minSpeed, maxSpeed, minCrewSize, maxCrewSize,
                isUsed, minRating, maxRating, null).size(), HttpStatus.OK);
    }
//    //GET SHIPS LIST
//    @RequestMapping(value = "rest/ships", method = RequestMethod.GET)
//    public ResponseEntity allShips() {
//        List<Ship> ships = shipService.allShips();
//        return new ResponseEntity(JsonConverterService.toJSON(shipService.getById(12)),HttpStatus.OK);
//    }
//    //GET SHIPS COUNT
//    @RequestMapping(value = "rest/ships/count", method = RequestMethod.GET)
//    public ResponseEntity shipsCount() {
//        List<Ship> ships = shipService.allShips();
//        return new ResponseEntity(JsonConverterService.countToJSON(ships.size()),HttpStatus.OK);
//    }
}
