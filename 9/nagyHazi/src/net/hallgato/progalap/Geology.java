package net.hallgato.progalap;

class Geology {
    public enum TYPE {
        SHORE,
        ISLAND,
        WATER
    }

    final TYPE type;
    final int width;
    final double extreme;
    final int extremeIndex;

    Geology(TYPE type, int start, int end, double extreme, int extremeIndex) {
        this.type = type;
        this.extreme = extreme;
        this.width = end - start;
        this.extremeIndex = extremeIndex;
    }
}
