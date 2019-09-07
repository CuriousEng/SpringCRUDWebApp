package com.space.controller;

import com.space.model.Ship;
import com.space.service.ShipService;
import com.space.service.ShipServiceImpl;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class ShipController {
    private ShipService shipService = new ShipServiceImpl();

    @RequestMapping(value = "/ships", method = RequestMethod.GET)
    public ModelAndView allShips() {
        List<Ship> ships = shipService.allShips();
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("ships");
        modelAndView.addObject("shipList", ships);
        return modelAndView;
    }

    @RequestMapping(value = "/ships", method = RequestMethod.POST)
    public ModelAndView createShip() {
        List<Ship> ships = shipService.allShips();
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("ships");
        modelAndView.addObject("shipList", ships);
        return modelAndView;
    }
}
