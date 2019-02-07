package net.hallgato.progalap;

import java.util.function.IntBinaryOperator;

public enum Method {
    Min((x, y) -> x < y ? 1 : 0),
    Max((x, y) -> x > y ? 1 : 0);

    private final IntBinaryOperator func;

    Method(IntBinaryOperator fn) {
        func = fn;
    }

    public IntBinaryOperator getFunc() {
        return func;
    }
}
