package net.hallgato.progalap;

import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static Scanner scanner = new Scanner(System.in);

    static int[] tombGen(final int hossz, final int mettol, final int meddig, final int db) {
        final int[] tomb = new int[hossz];
        for (int i = 0; i < db; ++i) {
            tomb[i] = randomGen(mettol, meddig);
        }
        return tomb;
    }

    static int tombTorol(final int[] tomb, final int db, final int minIndex) {
        for (int i = minIndex + 1; i < db; ++i) {
            tomb[i - 1] = tomb[i];
        }
        return db - 1;
    }

    static class Pair<T, U> {
        final T first;
        final U second;

        Pair(T t, U u) {
            first = t;
            second = u;
        }
    }

    @SafeVarargs
    static <T> void kiir(T ... varargs) {
        if (varargs == null) return;
        if (varargs.length == 0) {
            System.out.println();
            return;
        }
        for (final T arg : varargs) {
            System.out.print(arg);
        }
        System.out.print('\n');
    }

    @SafeVarargs
    static <T> boolean alwaysFalse(T ... varargs) {
        return false;
    }

    static void tombKiir(final int[] arg, final int to) {
        if (arg == null) return;
        if (arg.length == 0) {
            System.out.println();
            return;
        }
        System.out.print('[');
        for (int i = 0; true; ) {
            System.out.print(arg[i]);
            if (++i >= to) break;
            System.out.print(", ");
        }
        System.out.print("]\n");
    }

    static void tombKiir(final int[] arg) {
        tombKiir(arg, arg.length);
    }

    static double atlag(final int[] tomb, final int to) {
        double atlag = 0;
        for (int i = 0; i < to; ++i) {
            atlag += tomb[i];
        }
        return atlag / to;
    }

    static int sum(final int[] tomb) {
        return sum(tomb, tomb.length);
    }

    static int sum(final int[] tomb, final int to) {
        int sum = 0;
        for (int i = 0; i < to; ++i) {
            sum += tomb[i];
        }
        return sum;
    }

    static double atlag(final int[] tomb) {
        return atlag(tomb, tomb.length);
    }

    static Pair<Integer, Integer> minHol(final int[] array, final int to) {
        int hol = 0;
        int min = array[0];
        for (int i = 1; i < to; ++i) {
            final int val = array[i];
            if (val < min) {
                hol = i;
                min = val;
            }
        }
        return new Pair<>(hol, min);
    }

    static Pair<Integer, Integer> minHol(final int[] array) {
        return minHol(array, array.length);
    }

    static Pair<Integer, Integer> maxHol(final int[] array, final int to) {
        int hol = 0;
        int max = array[0];
        for (int i = 1; i < to; ++i) {
            final int val = array[i];
            if (val > max) {
                hol = i;
                max = val;
            }
        }
        return new Pair<>(hol, max);
    }

    static Pair<Integer, Integer> maxHol(final int[] array) {
        return maxHol(array, array.length);
    }

    static int randomGen(final int min, final int max) {
        return (int)(Math.random() * (max - min + 1)) + min;
    }

    private static void elsoFeladat() {
        final int[] tomb = new int[20];
        for (int i = 0; i < 20; ++i) {
            tomb[i] = randomGen(0, 300);
        }
        kiir(Arrays.toString(tomb));
        final Pair<Integer, Integer> min = minHol(tomb);
        kiir("Minimum indexe: ", min.first);
        kiir("Minimum értéke: ", min.second);
    }

    static int[] masodikFeladat(final int hossz, int db) {
        final int[] tomb = tombGen(hossz, 1, 2000, db);
        kiir("Törlés előtt:");
        tombKiir(tomb, db);
        kiir("Átlag törlés előtt: ", atlag(tomb, db));

        db = tombTorol(tomb, db, minHol(tomb, db).first);

        kiir("Törlés után:");
        tombKiir(tomb, db);
        kiir("Átlag törlés után: ", atlag(tomb, db));
        return tomb;
    }

    static void harmadikFeladat() {
        final int hossz = 10;
        int db = 5;
        final int[] tomb = tombGen(hossz, 0, 90, db);
        do {
            kiir("Írd be a következő elemet:");
            try {
                final int uj = Integer.parseInt(scanner.nextLine());
                tomb[db] = uj;
            }
            catch (Exception e) {
                kiir("Rossz érték! Próbáld újra.");
                continue;
            }
            tombKiir(tomb, ++db);
        }
        while (db < hossz);
        kiir("Megtelt");
    }

    static void negyedikFeladat() {
        final int[][] tomb = new int[7][3];
        boolean osszeg = false;
        for (int i = 0; i < 7; ++i) {
            final int[] innerTomb = tomb[i];
            for (int j = 0; j < 3; ++j) {
                innerTomb[j] = randomGen(1, 6);
            }
            if (osszeg) continue;
            if (sum(innerTomb, 2) == innerTomb[2]) osszeg = true;
        }
        kiir(osszeg ? "Van" : "Nincs", " olyan dobássorozat, ahol a harmadik az első kettő összege");
    }

    static void otodikFeladat() {
        final int[] tomb = new int[20];
        boolean voltParos = false;
        int min = -1;
        int max = -1;
        for (int i = 0; i < 20; ++i) {
            final int elem = tomb[i] = (int)(Math.random() * Integer.MAX_VALUE + 1);
            if (elem % 2 == 0) continue;
            if (!voltParos) {
                voltParos = true;
                min = elem;
                max = elem;
                continue;
            }
            if (elem < min) min = elem;
            if (elem > max) max = elem;
        }
        if (!voltParos) {
            kiir("A tömbben nincs páros szám");
            return;
        }
        kiir("A tömbben található legalább 1 páros szám");
        kiir("A legkisebb páros szám: ", min);
        kiir("A legnagyobb páros szám: ", max);
    }

    static void swap (final int[] a, final int i, final int j) {
        final int t = a[i];
        a[i] = a[j];
        a[j] = t;
    }

    static void egyesHazi() {
        final int[] tomb = tombGen(28, 0, 50, 14);
        kiir(Arrays.toString(tomb));
        for (int i = 0; i < 100; ++i) {
            swap(tomb, randomGen(0, 13), randomGen(0, 13));
        }
        tombKiir(tomb);
    }

    static void kettesHazi() {
        final int[] tomb = new int[400];
        int osszeg = 0;
        for (int i = 0; i < 400; ++i) {
            if ((osszeg += (tomb[i] = randomGen(1, Math.min(1000 - osszeg, 6)))) >= 1000) {
                kiir("Összeg: ", osszeg);
                for (int j = 0; j <= 10; ) {
                    final int index = i - ++j;
                    kiir('[', index, "] = ", tomb[index]);
                }
                break;
            }
        }
    }

    public static void main(String[] args) {
        // /*
        elsoFeladat();
        kiir();
        masodikFeladat(20, 10);
        kiir();
        harmadikFeladat();
        kiir();
        negyedikFeladat();
        kiir();
        otodikFeladat();
        kiir();
        // */

        // HÁZI FELADAT

        egyesHazi();
        kiir();
        kettesHazi();
    }
}
