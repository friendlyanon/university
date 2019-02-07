package net.hallgato.progalap;

import java.util.function.IntUnaryOperator;

public enum Parity {
    NegativeOdd(x -> x < 0 && (x & 1) == 1 ? 1 : 0),
    NegativeEven(x -> x < 0 && (x & 1) == 0 ? 1 : 0),
    Zero(x -> x == 0 ? 1 : 0),
    PositiveEven(x -> x > 0 && (x & 1) == 0 ? 1 : 0),
    PositiveOdd(x -> x > 0 && (x & 1) == 1 ? 1 : 0);

    private final IntUnaryOperator func;

    Parity(IntUnaryOperator fn) {
        func = fn;
    }

    public IntUnaryOperator getFunc() {
        return func;
    }
}
