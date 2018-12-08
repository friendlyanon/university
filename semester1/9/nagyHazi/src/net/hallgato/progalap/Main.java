package net.hallgato.progalap;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

public class Main extends Measurements {
    private static Geology[] geologies = null;

    static GetAllGeology getAll(final Geology.TYPE type) {
        return new GetAllGeology(geologies, type);
    }

    private static void parse(final double[] measurements) {
        final int len = measurements.length;
        if (len < 1) {
            geologies = new Geology[0];
            return;
        }
        final List<Geology> list = new ArrayList<>(15);
        for (int i = 0; ; ++i) {
            double extreme = measurements[i];
            int extremeIdx = i;
            boolean sentinel = extreme < 0;
            int start = i;
            boolean inRange;
            while (inRange = ++i < len) {
                final double value = measurements[i];
                if (sentinel != (sentinel = value < 0)) break;
                if (sentinel ? extreme > value : extreme < value) {
                    extreme = value;
                    extremeIdx = i;
                }
            }
            final Geology.TYPE type = extreme < 0 ?
                    Geology.TYPE.WATER :
                    (start == 0 || i == len) ?
                            Geology.TYPE.SHORE :
                            Geology.TYPE.ISLAND;
            list.add(new Geology(type, start, --i, extreme, extremeIdx));
            if (!inRange) break;
        }
        final Geology[] array = new Geology[list.size()];
        geologies = list.toArray(array);
    }

    private static void parse() {
        parse(measurements);
    }

    private static int fastLog10(int v) {
        if (v < 0) {
            if (v == Integer.MIN_VALUE) return 9;
            v = -v;
        }
        return (v >= 1000000000) ? 9 : (v >= 100000000) ? 8 :
        (v >= 10000000) ? 7 : (v >= 1000000) ? 6 :
        (v >= 100000) ? 5 : (v >= 10000) ? 4 :
        (v >= 1000) ? 3 : (v >= 100) ? 2 : (v >= 10) ? 1 : 0;
    }

    static Stream<Geology> asStream(GetAllGeology it) {
        return StreamSupport.stream(it.spliterator(), false);
    }

    private static int difference(final int a, final int b) {
        return a > b ? a - b : b - a;
    }

    private static int sumGeologies() {
        return asStream(getAll(Geology.TYPE.WATER))
            .mapToInt(Mandatory.toInt)
            .reduce((a, b) -> a + b)
            .orElse(0);
    }

    // Ha megszámozzuk a szigeteket, melyik két szomszédos sziget van a legtávolabb egymáshoz?
    private static void first() {
        int i = 0;
        int count = 0;
        int width = 0;
        for (final Geology water : getAll(Geology.TYPE.WATER).skip().skipEnd()) {
            final int waterWidth = water.width;
            if (waterWidth >= width) {
                count = i;
                width = waterWidth;
            }
            ++i;
        }
        if (i == 0) {
            System.out.println("Nincsen legalább 2 sziget :(");
            return;
        }
        System.out.println("A(z) " + ++count + ". és " + ++count + ". szigetek vannak legmesszebb egymástól (" + width + " km)");
    }

    // Hány méterrel csökkenjen a tengerszint, hogy a szigetek száma megváltozzék?
    private static void second() {
        final double min = asStream(getAll(Geology.TYPE.ISLAND))
            .mapToDouble(Mandatory.toDbl)
            .reduce(Mandatory.minDbl)
            .orElse(0.0);
        System.out.println("Egy sziget eltűnéséhez " + min + " méternél többel kell növekednie a tengreszintnek");
    }

    // Ha egy méterrel nő a tengerszint, hány kilométer kerül víz alá?
    private static void third(final double rise) {
        final Geology[] original = geologies;
        final int beforeLand = sumGeologies();
        parse(Arrays.stream(measurements).map(x -> x + rise).toArray());
        final int afterLand = sumGeologies();
        geologies = original;
        final int difference = difference(beforeLand, afterLand);
        System.out.println(difference + " km föld került víz alá 1 méteres tengerszint emelkedés után");
    }

    // A legkisebb szigetcsúcs hány km-re van a legmagasabb szigetcsúcstól?
    private static void fourth() {
        final GetAllGeology it = getAll(Geology.TYPE.ISLAND);
        if (!it.hasNext()) {
            System.out.println("Nincsenek szigetek szigetcsúcsok távolságának méréséhez");
            return;
        }
        final Geology firstIsland = it.next();
        Geology min = firstIsland;
        Geology max = firstIsland;
        for (final Geology island : it) {
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
    private static char[] crossLookup = { '┼', '╫', '╪', '╬' };
    private static void fifth() {
        final StringBuilder builder = new StringBuilder("Szigetcsúcsok távolsága mátrixban:\n");
        final Geology[] islands = asStream(getAll(Geology.TYPE.ISLAND))
            .toArray(Geology[]::new);
        final int islandsLength = islands.length;
        if (islandsLength == 0) {
            builder.append("Nincsenek szigetek :(");
            System.out.println(builder.toString());
            return;
        }
        builder.append("  #  ");
        for (int i = 0; i < islandsLength; ) {
            builder.append(i == 0 ? "║  " : "│  ");
            builder.append(++i);
            builder.append("  ");
        }
        builder.append('\n');
        for (int i = 0; i < islandsLength; ++i) {
            final boolean first = i == 0;
            final Geology island = islands[i];
            final String line = first ? "═════" : "─────";
            builder.append(line);
            for (int j = 0; j < islandsLength; ++j) {
                builder.append(crossLookup[(j == 0 ? 1 : 0) | (first ? 2 : 0)]);
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
                    default:
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
        final double[] waterExtremes = asStream(getAll(Geology.TYPE.WATER))
            .sorted((a, b) -> Double.compare(b.extreme, a.extreme))
            .mapToDouble(Mandatory.toDbl)
            .toArray();
        System.out.println("\nBónusz - árkok mélység szerint csökkenő sorrendben:");
        System.out.println(Arrays.toString(waterExtremes));
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
