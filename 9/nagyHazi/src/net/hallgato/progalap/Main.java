package net.hallgato.progalap;

import java.util.Arrays;
import java.util.Collections;

public class Main {
    static final Geology[] geologies = new Geology[Meresek.meresek.length / 2];
    static int validLength = 0;

    private static GetAllGeology getAll(final Geology.TYPE type) {
        return new GetAllGeology(geologies, type, validLength);
    }

    private static void parse(final double[] measurements) {
        validLength = 0;
        final int len = measurements.length;
        double extreme = measurements[0];
        int extremeIdx = 0;
        boolean underWater = extreme < 0;
        int start = 0;
        for (int i = 1; i < len; ++i) {
            final double value = measurements[i];
            if (underWater ? extreme > value : extreme < value) {
                extreme = value;
                extremeIdx = i;
            }
            if (underWater == (underWater = value < 0)) continue;
            final Geology.TYPE type = start == 0 && underWater ?
                    Geology.TYPE.SHORE :
                    underWater ?
                            Geology.TYPE.ISLAND :
                            Geology.TYPE.WATER;
            geologies[validLength++] = new Geology(type, start, i - 1, extreme, extremeIdx);
            start = i;
        }
        geologies[validLength++] = new Geology(
            underWater ? Geology.TYPE.WATER : Geology.TYPE.SHORE,
            start, len - 1, extreme, extremeIdx
        );
    }

    private static void parse() {
        parse(Meresek.meresek);
    }

    private static int fastLog10(int v) {
        if (v < 0) {
            v = v == Integer.MIN_VALUE ? -(v + 1) : -v;
        }
        return (v >= 1000000000) ? 9 : (v >= 100000000) ? 8 :
        (v >= 10000000) ? 7 : (v >= 1000000) ? 6 :
        (v >= 100000) ? 5 : (v >= 10000) ? 4 :
        (v >= 1000) ? 3 : (v >= 100) ? 2 : (v >= 10) ? 1 : 0;
    }

    private static int findFirst(final Geology.TYPE type) {
        for (int i = 0; i < validLength; ++i) {
            if (geologies[i].type == type) {
                return i;
            }
        }
        return -1;
    }

    private static int findLast(final Geology.TYPE type) {
        for (int i = validLength; i >= 0; ) {
            if (geologies[--i].type == type) {
                return i;
            }
        }
        return -1;
    }

    static int findFirst(final Geology.TYPE type, final Geology[] geologies, final int validLength) {
        for (int i = 0; i < validLength; ++i) {
            if (geologies[i].type == type) {
                return i;
            }
        }
        return -1;
    }

    static int findLast(final Geology.TYPE type, final Geology[] geologies, final int validLength) {
        for (int i = validLength; i >= 0; ) {
            if (geologies[--i].type == type) {
                return i;
            }
        }
        return -1;
    }

    private static int difference(final double a, final double b) {
        return (int)(a > b ? a - b : b - a);
    }

    // Ha megszámozzuk a szigeteket, melyik két szomszédos sziget van a legtávolabb egymáshoz?
    private static void first() {
        int i = findFirst(Geology.TYPE.ISLAND) + 1;
        int count = 0;
        int width = 0;
        for (int idx = 0; i < validLength; ++idx, ++i) {
            final Geology water = geologies[i];
            if (geologies[++i].type == Geology.TYPE.SHORE) break;
            final int waterWidth = water.width;
            if (waterWidth < width) continue;
            count = idx;
            width = waterWidth;
        }
        System.out.println("A(z) " + ++count + ". és " + ++count + ". szigetek vannak legmesszebb egymástól (" + width + " km)");
    }

    // Hány méterrel csökkenjen a tengerszint, hogy a szigetek száma megváltozzék?
    private static void second() {
        double min = Double.POSITIVE_INFINITY;
        final int lastIsland = findLast(Geology.TYPE.ISLAND);
        for (int i = findFirst(Geology.TYPE.ISLAND); i <= lastIsland; i += 2) {
            final double extreme = geologies[i].extreme;
            if (extreme < min) min = extreme;
        }
        System.out.println("Egy sziget eltűnéséhez " + min + " méternél többel kell növekednie a tengreszintnek");
    }

    // Ha egy méterrel nő a tengerszint, hány kilométer kerül víz alá?
    private static void third(final double rise) {
        int beforeLand = 0;
        for (int i = Math.max(findFirst(Geology.TYPE.WATER) - 1, 0); i < validLength; i += 2) {
            beforeLand += geologies[i].width;
        }
        {
            final double[] newMeasurements = Meresek.meresek.clone();
            for (int i = 0, len = newMeasurements.length; i < len; ++i) {
                newMeasurements[i] += rise;
            }
            parse(newMeasurements);
        }
        int afterLand = 0;
        for (int i = Math.max(findFirst(Geology.TYPE.WATER) - 1, 0); i < validLength; i += 2) {
            afterLand += geologies[i].width;
        }
        // restore original parsed array
        parse();
        final int difference = difference(beforeLand, afterLand);
        System.out.println(difference + " km föld került víz alá 1 méteres tengerszint emelkedés után");
    }

    // A legkisebb szigetcsúcs hány km-re van a legmagasabb szigetcsúcstól?
    private static void fourth() {
        final int firstIsland = findFirst(Geology.TYPE.ISLAND);
        final int lastIsland = findLast(Geology.TYPE.ISLAND);
        Geology min = geologies[firstIsland];
        Geology max = geologies[firstIsland];
        for (int i = firstIsland + 2; i <= lastIsland; i += 2) {
            final Geology island = geologies[i];
            final double extreme = island.extreme;
            if (extreme < min.extreme) {
                min = island;
            }
            if (extreme > max.extreme) {
                max = island;
            }
        }
        final int difference = difference(min.extremeIndex, max.extremeIndex);
        System.out.println(difference + " km-re van a legkisebb és legnagyobb szigetcsúcs egymástól");
    }

    // Írja le a szigetcsúcsok távolságát mátrixszal!
    private static void fifth() {
        StringBuilder builder = new StringBuilder("Szigetcsúcsok távolsága mátrixban:\n");
        final int firstIsland = findFirst(Geology.TYPE.ISLAND);
        final int lastIsland = findLast(Geology.TYPE.ISLAND);
        final Geology[] islands = new Geology[(lastIsland - firstIsland) / 2 + 1];
        for (int i = firstIsland, k = 0; i <= lastIsland; i += 2) {
            islands[k++] = geologies[i];
        }
        builder.append("  #  ");
        final int islandsLength = islands.length;
        for (int i = 0; i < islandsLength; ) {
            builder.append(i == 0 ? "║  " : "│  ");
            builder.append(++i);
            builder.append("  ");
        }
        builder.append('\n');
        final int rowLength = builder.length();
        for (int i = 0; i < islandsLength; ++i) {
            final boolean first = i == 0;
            final Geology island = islands[i];
            final String line = first ? "═════" : "─────";
            builder.append(line);
            for (int j = 0; j < islandsLength; ++j) {
                switch ((j == 0 ? 1 : 0) | (first ? 2 : 0)) {
                    case 0: builder.append('┼'); break;
                    case 1: builder.append('╫'); break;
                    case 2: builder.append('╪'); break;
                    case 3: builder.append('╬'); break;
                }
                builder.append(line);
            }
            builder.append("\n  ");
            builder.append(i + 1);
            builder.append("  ");
            for (int j = 0; j < islandsLength; ++j) {
                builder.append(j == 0 ? '║' : '│');
                if (i == j) {
                    builder.append("  -  ");
                    continue;
                }
                builder.append(' ');
                final Geology other = islands[j];
                final int difference = difference(island.extremeIndex, other.extremeIndex);
                switch (fastLog10(difference)) {
                    case 0:
                        builder.append(' ');
                    case 1:
                        builder.append(difference);
                        builder.append("  ");
                        continue;
                    case 2:
                        builder.append(difference);
                        builder.append(' ');
                }
            }
            builder.append("\n");
        }
        System.out.print(builder.toString());
    }

    // Rendezze az árkokat mélységük szerint csökkenő sorrendbe!
    private static void sort() {
        final Geology[] waters = new Geology[validLength];
        int watersLen = 0;
        for (final Geology water : getAll(Geology.TYPE.WATER)) {
            waters[watersLen++] = water;
        }
        Arrays.sort(waters, 0, watersLen, (a, b) -> {
            final double diff = b.extreme - a.extreme;
            return diff < 0 ? -1 : diff > 0 ? 1 : 0;
        });
        System.out.println("\nBónusz - árkok mélység szerint csökkenő sorrendben:");
        --watersLen;
        for (int i = 0; i < watersLen; ++i) {
            System.out.print(waters[i].extreme + ", ");
        }
        System.out.println(waters[watersLen].extreme);
    }

    public static void main(String[] args) {
        parse();
        first();
        second();
        third(1);
        fourth();

        /**
         * Ha IDEA konzolban van ez megtekintve, kérem állítsa át a konzol
         * betütípusát egy monospace típusúra a mátrix ideális megjelentítéséhez
         * Ajánlott betütípus:
         * File > Settings > Editor > Font > Font: Consolas
         */
        fifth();

        // KÖTELEZŐ KÉRDÉSEK
        Mandatory.all();

        // BÓNUSZ RENDEZÉS
        sort();

        System.out.println();
    }
}
