package pl.szaran.model;

import lombok.Getter;

import java.math.BigDecimal;

/**
 * Klasa Car posiada pola model (napis składający się tylko i
 * wyłącznie z dużych liter oraz białych znaków), price (wartość
 * nieujemna), mileage (liczba całkowita nieujemna), engine (referencja
 * klasy Engine), carBody (referencja klasy CarBody) oraz wheel
 * (referencja klasy Wheel). Klasa Car posiada konstruktor argumentowy,
 * który przyjmuje jako argument nazwę pliku przechowującego dane
 * samochodu w formacie JSON.
 */

@Getter
public class Car {
    private String model;
    private BigDecimal price;
    private Long mileage;
    private Engine engine;
    private Wheel wheel;
    private CarBody carBody;

}
