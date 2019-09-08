package com.space.controller;

import com.space.model.Ship;
import com.space.service.JsonConverterService;
import com.space.service.ShipService;
import com.space.service.ShipServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Controller
public class ShipController {

    private ShipService shipService = new ShipServiceImpl();

    //CREATE
    @RequestMapping(value = "rest/ships", method = RequestMethod.POST)
    public ResponseEntity createShip(@RequestBody LinkedHashMap<String, Object> params) {
        if (shipService.isParamsValid(params)) {
            Ship newShip = shipService.convertParamsToShip(params, null);
            shipService.add(newShip);
            return new ResponseEntity(JsonConverterService.toJSON(newShip), HttpStatus.OK);
        }
        return new ResponseEntity(HttpStatus.BAD_REQUEST);
    }
    //GET
    @RequestMapping(value = "/ships/{id}", method = RequestMethod.GET)
    public ResponseEntity getShip(@PathVariable("id") long id) {
        try{
            shipService.getById(id);
        } catch (Exception e) {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
        return shipService.getById(id) == null ?
                new ResponseEntity(HttpStatus.NOT_FOUND) :
                new ResponseEntity(JsonConverterService.toJSON(shipService.getById(id)),HttpStatus.OK);
    }
    //DELETE
    @RequestMapping(value = "/ships/{id}", method = RequestMethod.DELETE)
    public ResponseEntity deleteShip(@PathVariable("id") long id) {
        try{
            shipService.getById(id);
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
    @RequestMapping(value = "/ships/{id}", method = RequestMethod.POST)
    public ResponseEntity editShip(@PathVariable("id") long id, @RequestBody LinkedHashMap<String, Object> params) {
        try{
            shipService.getById(id);
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
    //GET SHIPS LIST
    @RequestMapping(value = "rest/ships", method = RequestMethod.GET)
    public ResponseEntity allShips(@RequestBody LinkedHashMap<String, Object> params) {
        List<Ship> ships = shipService.allShips();
        return new ResponseEntity(JsonConverterService.toJSON(ships),HttpStatus.OK);
    }
    //GET SHIPS COUNT
    @RequestMapping(value = "rest/ships", method = RequestMethod.GET)
    public ResponseEntity shipsCount() {
        List<Ship> ships = shipService.allShips();
        return new ResponseEntity(JsonConverterService.countToJSON(ships.size()),HttpStatus.OK);
    }
}
