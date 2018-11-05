package net.hallgato.progalap;

class Geology {
    public enum TYPE {
        SHORE,
        ISLAND,
        WATER
    }

    final TYPE type;
    final int start;
    final int end;
    final int width;
    final double extreme;
    final int extremeIndex;

    Geology(TYPE type, int start, int end, double extreme, int extremeIndex) {
        this.start = start;
        this.end = end;
        this.type = type;
        this.extreme = extreme;
        this.width = end - start + 1;
        this.extremeIndex = extremeIndex;
    }
}
