package pl.szaran.service;


import lombok.Getter;
import pl.szaran.exceptions.MyException;
import pl.szaran.model.Car;

import java.util.Set;
import java.util.stream.Collectors;

/**
 * Klasa CarService posiada pole składowe cars, które reprezentuje
 * kolekcję elementów typu Car. Kolekcja nie posiada duplikatów. Klasa
 * posiada konstruktor argumentowy. Argumentem konstruktora jest
 * kolekcja napisów, która przechowuje nazwy plików JSON, z których
 * zostaną pobrane dane dla kolejnych elementów kolekcji cars.
 */

@Getter
public class CarService {
    private final Set<Car> cars;

    public CarService(Set<String> jsonFilenames) {

        this.cars = returnCars(jsonFilenames);
    }

    private Set<Car> returnCars(Set<String> jsonFilenames) {

        if (jsonFilenames == null) {
            throw new MyException("CarService -  FILENAMES COLLECTION IS NULL");
        }
        return jsonFilenames
                .stream()
                .map(s -> new Car(s))
                .collect(Collectors.toSet());
    }
}
