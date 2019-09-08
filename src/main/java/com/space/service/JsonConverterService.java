package com.space.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.space.model.Ship;

import java.io.IOException;
import java.io.StringWriter;
import java.util.List;

public class JsonConverterService {

    public static String toJSON(Ship ship) {
        StringWriter stringWriter = new StringWriter();
        ObjectMapper mapper = new ObjectMapper();
        try {
            mapper.writeValue(stringWriter, ship);
        } catch (IOException e) {
            System.out.println("Can`t convert POJO to JSON");
        }
        System.out.println("JSON successfully created!");
        return stringWriter.toString();
    }

    public static String toJSON(List<Ship> ships) {
        StringWriter stringWriter = new StringWriter();
        ObjectMapper mapper = new ObjectMapper();
        try {
            mapper.writeValue(stringWriter, ships);
        } catch (IOException e) {
            System.out.println("Can`t convert POJO List to JSON");
        }
        System.out.println("JSON successfully created!");
        return stringWriter.toString();
    }

    public static String countToJSON(Integer count) {
        StringWriter stringWriter = new StringWriter();
        ObjectMapper mapper = new ObjectMapper();
        try {
            mapper.writeValue(stringWriter, count);
        } catch (IOException e) {
            System.out.println("Can`t convert count to JSON");
        }
        System.out.println("JSON successfully created!");
        return stringWriter.toString();
    }

    public static Ship toPOJO(String JSONstring) {
        ObjectMapper mapper = new ObjectMapper();
        Ship ship = new Ship();
        try {
            ship = mapper.readValue(JSONstring, Ship.class);
            System.out.println("POJO successfully created!");
            return ship;
        } catch (IOException e) {
            System.out.println("Can`t convert JSON to POJO");
        }
            System.out.println("Ship was not created/ Something went wrong/");
            return ship;
    }

}
