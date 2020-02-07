package pl.szaran.validators;

import pl.szaran.model.Car;

import java.math.BigDecimal;
import java.util.Map;

public class CarValidator extends AbstractValidator<Car> {

    @Override
    public Map<String, String> validate(Car car) {

        if (car == null) {
            errors.put("car", "car is null");
        } else {

            if (!isModelValid(car)) {
                errors.put("model", "not valid: " + car.getModel());
            }

            if (!isMileageValid(car)) {
                errors.put("mileage", "not valid: " + car.getMileage());
            }

            if (!isPriceValid(car)) {
                errors.put("price", "not valid: " + car.getPrice());
            }

            if (car.getCarBody() == null) {
                errors.put("carBody", "carBody is null");
            }

            if (!areComponentsValid(car)) {
                errors.put("carBody", "components are invalid: " + car.getCarBody().getComponents());
            }

            if (car.getEngine() == null) {
                errors.put("engine", "engine is null");
            }

            if (!isPowerValid(car)) {
                errors.put("engine", "power is invalid: " + car.getEngine().getPower());
            }

            if (car.getWheel() == null) {
                errors.put("wheel", "wheel is null");
            }

            if (!isWheelModelValid(car)) {
                errors.put("wheel", "model is invalid: " + car.getWheel().getModel());
            }

            if (!isSizeValid(car)) {
                errors.put("wheel", "size is invalid: " + car.getWheel().getSize());
            }
        }
        return errors;
    }

    private boolean isModelValid(Car car) {
        return car.getModel() != null && car.getModel().matches("[A-Z\\s]+");
    }

    private boolean isMileageValid(Car car) {
        return car.getMileage() != null && car.getMileage() >= 0;
    }

    private boolean isPriceValid(Car car) {
        return car.getPrice() != null && car.getPrice().compareTo(BigDecimal.ZERO) > -1; //metoda compareTo zwraca -1 kiedy vartosc w () jest większa, 0 - kiedy sa rowne i 1 kiedy wartosc w () jest mniejsza
    }

    private boolean areComponentsValid(Car car) {
        return car.getCarBody().getComponents() != null && car.getCarBody().getComponents().stream().allMatch(comp -> comp.matches("[A-Z\\s]+"));
    }

    private boolean isPowerValid(Car car) {
        return car.getEngine().getPower() != null && car.getEngine().getPower() >= 0;
    }

    private boolean isWheelModelValid(Car car) {
        return car.getWheel().getModel() != null && car.getWheel().getModel().matches("[A-Z\\s]+");
    }

    private boolean isSizeValid(Car car) {
        return car.getWheel().getSize() != null && car.getWheel().getSize() >= 0;
    }
}
