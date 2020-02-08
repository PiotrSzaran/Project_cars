package pl.szaran.service;


import lombok.Getter;
import pl.szaran.exceptions.MyException;
import pl.szaran.model.Car;
import pl.szaran.model.enums.CarBodyType;

import java.math.BigDecimal;
import java.util.*;
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

    /**
     * Metoda zwraca kolekcję samochodów o określonym rodzaju nadwozia
     * przekazanym jako argument (CarBodyType) oraz o cenie z
     * przedziału <a, b>, gdzie a oraz b to kolejne argumenty metody.
     */

    public List<Car> getCarsByBodyTypeAndPriceBetween(CarBodyType carBodyType, BigDecimal firstPrice, BigDecimal secondPrice) {

        if (carBodyType == null) {
            throw new MyException("Car body type is null");
        }
        if (!EnumSet.allOf(CarBodyType.class).contains(carBodyType)) {
            throw new MyException("car body type incorrect value: " + carBodyType);
        }
        if (firstPrice == null) {
            throw new MyException("first price is null");
        }
        if (firstPrice.compareTo(BigDecimal.ZERO) < 0) {
            throw new MyException("first price should be equal or bigger than 0");
        }
        if (secondPrice == null) {
            throw new MyException("second price is null");
        }
        if (secondPrice.compareTo(BigDecimal.ZERO) < 0) {
            throw new MyException("second price should be equal or bigger than 0");
        }

        return (firstPrice.compareTo(secondPrice)) < 0 ? cars
                .stream()
                .filter(car -> car.getCarBody().getType().equals(carBodyType))
                .filter(car -> car.getPrice().compareTo(firstPrice) > -1)
                .filter(car -> car.getPrice().compareTo(secondPrice) < 1)
                .collect(Collectors.toList())
                : cars
                .stream()
                .filter(car -> car.getCarBody().getType().equals(carBodyType))
                .filter(car -> car.getPrice().compareTo(secondPrice) > -1)
                .filter(car -> car.getPrice().compareTo(firstPrice) < 1)
                .collect(Collectors.toList());
    }
}
