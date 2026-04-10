package p1.test;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import p1.prog.Trigonometric;

import java.math.BigDecimal;
import java.util.concurrent.ThreadLocalRandom;

import static org.junit.jupiter.api.Assertions.*;

public class TrigonometricTest {

    @ParameterizedTest(name = "tg({0})")
    @DisplayName("Минимальное полное эквивалентное тестирование tg(x)")
    @ValueSource(doubles = {
            // нормальные значения
            -0.5,
            -0.000001,
            0.0,
            0.000001,
            0.5,

            // вне области сходимости
            -1.0,
            -0.99,
            0.99,
            1.0,

            // периодичность
            Math.PI,
            -Math.PI,
            1.0 + Math.PI,
            -1.0 + Math.PI,

            // точки разрыва
            Math.PI / 2,
            -Math.PI / 2,

            // около разрыва
            (Math.PI / 2) - 1e-6,
            -(Math.PI / 2) + 1e-6,

            Double.NaN,
            Double.POSITIVE_INFINITY,
            Double.NEGATIVE_INFINITY
    })
    void checkCornerDots(double x) {

        double actual = Trigonometric.tg(x);

        if (Double.isNaN(x) || Double.isInfinite(x)) {
            assertTrue(Double.isNaN(actual), "tg(" + x + ") должен быть NaN");
            return;
        }

        // точки разрыва
        if (x == Math.PI / 2 || x == -Math.PI / 2) {
            assertTrue(Double.isNaN(actual), "tg(" + x + ") должен быть NaN");
            return;
        }

        // область сходимости ряда
        double nx = Trigonometric.normalize(x);
        if (Math.abs(nx) > 0.8) {
            assertTrue(Double.isNaN(actual),
                    "tg(" + x + ") должен быть NaN тк нет сходимости ряда");
            return;
        }

        double expected=0.0;
        switch ((BigDecimal.valueOf(nx).toString())) {
            case "-0.5": expected = -0.5463024898437905; break;
            case "-0.0000010": expected = -0.000001; break;
            case "0.0": expected = 0.0; break;
            //case "-0.0": expected = 0.0; break;
            case "0.0000010": expected = 0.000001; break;
            case "0.5": expected = 0.5463024898437905; break;
        }

        assertEquals(expected, actual, 0.0001, "tg(" + x + ") неверен");
    }



}
