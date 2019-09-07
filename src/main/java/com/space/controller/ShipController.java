package com.space.controller;

import com.space.model.Ship;
import com.space.service.JsonConverterService;
import com.space.service.ShipService;
import com.space.service.ShipServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class ShipController {

    private ShipService shipService = new ShipServiceImpl();

    @RequestMapping(value = "rest/ships", method = RequestMethod.GET)
    public ModelAndView allShips() {
        List<Ship> ships = shipService.allShips();
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("ships");
        modelAndView.addObject("shipList", ships);
        return modelAndView;
    }

    @RequestMapping(value = "rest/ships", method = RequestMethod.POST)
    public ResponseEntity createShip(@RequestBody String data) {
        System.out.println(data);
        Ship newShip = JsonConverterService.toPOJO(data);
        if (newShip.getUsed() == null) {
            newShip = new Ship(newShip.getName(), newShip.getPlanet(),
                    newShip.getShipType(), newShip.getProdDate(), false, newShip.getSpeed(), newShip.getCrewSize());
        } else {
            newShip = new Ship(newShip.getName(), newShip.getPlanet(),
                    newShip.getShipType(), newShip.getProdDate(), newShip.getUsed(), newShip.getSpeed(), newShip.getCrewSize());
        }
        shipService.add(newShip);
        return new ResponseEntity(JsonConverterService.toJSON(newShip), HttpStatus.OK);
    }

    @RequestMapping(value = "/ships/{id}", method = RequestMethod.GET)
    public ModelAndView getShip(@PathVariable("id") int id) {
        Ship film = shipService.getById(id);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("ship");
        modelAndView.addObject("film", film);
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
