package pl.szaran.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import pl.szaran.model.enums.TyreType;

/**
 * Klasa Wheel posiada pola składowe model (napis
 * składający się tylko i wyłącznie z dużych liter i białych znaków),
 * size (liczba całkowita większa nieujemna) oraz type (enum TyreType o
 * wartościach WINTER, SUMMER)
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Wheel {
    private String model;
    private Integer size;
    private TyreType type;

    @Override
    public String toString() {
        return
                "\nmodel:'" + model + '\'' +
                        ", \n  size:" + size +
                        ", \n    type:" + type;
    }
}
