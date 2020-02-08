package pl.szaran.service;


import lombok.Getter;
import pl.szaran.exceptions.MyException;
import pl.szaran.model.Car;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
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

    /**
     * Metoda zwraca kolekcję samochodów posortowaną według kryterium
     * podanego jako argument. Metoda powinna umożliwiać sortowanie
     * według ilości komponentów, mocy silnika oraz rozmiaru opony.
     * Dodatkowo metoda powinna umożliwiać sortowanie rosnąco oraz
     * malejąco.
     */

    public List<Car> sortBy() {

        SortType sortType = UserDataService.getSortType();
        boolean ascending = UserDataService.getSortOrder();

        //switch expressions must be on
        var sortedCars = switch (sortType) {
            case COMPONENT_NUMBER -> cars.stream().sorted(Comparator.comparing(car -> car.getCarBody().getComponents().size()));
            case ENGINE_POWER -> cars.stream().sorted(Comparator.comparing(car -> car.getEngine().getPower()));
            default -> cars.stream().sorted(Comparator.comparing(car -> car.getWheel().getSize()));
        };

        var sortedCarsByOrder = sortedCars.collect(Collectors.toList());
        if (!ascending) {
            Collections.reverse(sortedCarsByOrder);
        }
        return sortedCarsByOrder;
    }
}
