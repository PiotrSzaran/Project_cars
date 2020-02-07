package pl.szaran.model;

import lombok.Getter;
import pl.szaran.converters.CarJsonConverter;
import pl.szaran.exceptions.MyException;
import pl.szaran.validators.CarValidator;

import java.math.BigDecimal;
import java.util.Map;

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

    public Car(String jsonFilename) {
        CarJsonConverter carJsonConverter = new CarJsonConverter(jsonFilename);

        CarValidator carValidator = new CarValidator();

        var car = carJsonConverter.fromJson().orElseThrow(() -> new MyException("JSON PARSE EXCEPTION IN CAR CONSTRUCTOR"));

        Map<String, String> errors = carValidator.validate(car);

        if (carValidator.hasErrors()) {
            errors.forEach((k, v) -> System.out.println(k + " " + v));
            throw new MyException("ERRORS IN JSON FILE: " + jsonFilename);
        }

        this.model = car.model;
        this.price = car.price;
        this.mileage = car.mileage;
        this.engine = car.engine;
        this.wheel = car.wheel;
        this.carBody = car.carBody;
    }

    @Override
    public String toString() {
        return
                "\nmodel:'" + model + '\'' +
                        ", \n  price:" + price +
                        ", \n   mileage:" + mileage +
                        ", \n   engine:" + engine +
                        ", \n    wheel:" + wheel +
                        ", \n    carBody:" + carBody;
    }
}
