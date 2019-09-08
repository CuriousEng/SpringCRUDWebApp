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

import javax.servlet.http.HttpServletRequest;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Controller
public class ShipController {

    private ShipService shipService = new ShipServiceImpl();

    @RequestMapping(value = "rest/ships", method = RequestMethod.POST)
    public ResponseEntity createShip(@RequestBody LinkedHashMap<String, Object> params) {
        ShipServiceImpl shipService = new ShipServiceImpl();
        if (shipService.isParamsValid(params)) {
            Ship newShip = shipService.convertParamsToShip(params);
            shipService.add(newShip);
            return new ResponseEntity(JsonConverterService.toJSON(newShip), HttpStatus.OK);
        }
        return new ResponseEntity(HttpStatus.BAD_REQUEST);
    }

    @RequestMapping(value = "/ships/{id}", method = RequestMethod.GET)
    public ResponseEntity getShip(@PathVariable("id") long id) {
        return shipService.getById(id) == null ?
                new ResponseEntity(HttpStatus.NOT_FOUND) :
                new ResponseEntity(JsonConverterService.toJSON(shipService.getById(id)),HttpStatus.OK);
    }

    @RequestMapping(value = "/ships/{id}", method = RequestMethod.DELETE)
    public ResponseEntity deleteShip(@PathVariable("id") long id) {
        Ship film = shipService.getById(id);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("ship");
        modelAndView.addObject("film", film);
        return modelAndView;
    }

    @RequestMapping(value = "rest/ships", method = RequestMethod.GET)
    public ModelAndView allShips() {
        List<Ship> ships = shipService.allShips();
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("ships");
        modelAndView.addObject("shipList", ships);
        return modelAndView;
    }

    @RequestMapping(value = "/ships/{id}", method = RequestMethod.POST)
    public ModelAndView editShip(@PathVariable("id") int id) {
        Ship film = shipService.getById(id);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("ship");
        modelAndView.addObject("film", film);
        return modelAndView;
    }
}
