package net.hallgato.progalap;

import java.util.Arrays;

public class Main {

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

    static void tombKiir(int[] arg) {
        if (arg == null) return;
        if (arg.length == 0) {
            System.out.println();
            return;
        }
        final int length = arg.length;
        for (int i = 0; true; ) {
            System.out.print(arg[i]);
            if (++i >= length) break;
            System.out.print(", ");
        }
        System.out.print('\n');
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
            tomb[i] = randomGen(0, (int)Math.pow(2, 15));
        }
        kiir(Arrays.toString(tomb));
        final Pair<Integer, Integer> min = minHol(tomb);
        kiir("Minimum indexe: ", min.first);
        kiir("Minimum értéke: ", min.second);
    }

    static int[] masodikFeladat(final int[] tomb) {
        return tomb;
    }

    private static void masodikMainCaller() {
        final int[] tomb = new int[20];
        masodikFeladat(tomb);

    }

    public static void main(String[] args) {
        elsoFeladat();
        masodikMainCaller();
    }
}
