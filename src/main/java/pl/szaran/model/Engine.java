package pl.szaran.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import pl.szaran.model.enums.EngineType;

/**
 * Klasa Engine posiada pola składowe type
 * (enum EngineType o wartościach DIESEL, GASOLINE, LPG) oraz power
 * (liczba po przecinku nieujemna).
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Engine {
    private EngineType type;
    private Double power;

    @Override
    public String toString() {
        return
                "\ntype:'" + type + '\'' +
                        ", \n    power:" + power;
    }
}