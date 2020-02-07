package pl.szaran.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import pl.szaran.model.enums.CarBodyColor;
import pl.szaran.model.enums.CarBodyType;

import java.util.Set;

/**
 * klasa CarBody posiada pola składowe color
 * (enum CarBodyColor o wartościach BLACK, SILVER, WHITE, RED, BLUE,
 * GREEN), type (enum CarBodyType o wartościach SEDAN, HATCHBACK,
 * COMBI) oraz listę elementów wyposażenia nadwozia components (każdy
 * napis powinien składać się tylko i wyłącznie z dużych liter oraz
 * białych znaków).
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CarBody {
    private CarBodyColor color;
    private CarBodyType type;
    private Set<String> components;

    @Override
    public String toString() {
        return
                "\ncolor:'" + color + '\'' +
                        ", \n  type:" + type +
                        ", \n    components:" + components;
    }
}