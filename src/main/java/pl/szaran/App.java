package pl.szaran;


import pl.szaran.model.enums.CarBodyType;
import pl.szaran.model.enums.EngineType;
import pl.szaran.service.CarService;

import java.math.BigDecimal;
import java.util.Set;

public class App
{
    public static void main( String[] args ) {

        Set<String> someSet = Set.of("audi.json", "bmw.json", "citroen.json", "mercedes.json");
        CarService carService = new CarService(someSet);

        System.out.println(carService.getCarsByEngineType(EngineType.DIESEL));
    }
}
