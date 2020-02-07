package pl.szaran.converters;

import pl.szaran.model.Car;

public class CarJsonConverter extends JsonConverter<Car> {
    public CarJsonConverter(String jsonFilename) {
        super(jsonFilename);
    }
}
