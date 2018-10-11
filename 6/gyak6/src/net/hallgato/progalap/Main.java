package net.hallgato.progalap;

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

    static int[] tombGen(final int hossz, final int mettol, final int meddig) {
        return tombGen(hossz, mettol, meddig, hossz);
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
        return new Pair<>(min, hol);
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
        return new Pair<>(max, hol);
    }

    static Pair<Integer, Integer> maxHol(final int[] array) {
        return maxHol(array, array.length);
    }

    static int randomGen(final int min, final int max) {
        return (int)(Math.random() * (max - min + 1)) + min;
    }

    static void elso() {
        int db = 0;
        for (int i = 1; i <= 100; i++) {
            if (i % 7 == 0) continue;
            if (i % 13 == 0) continue;
            ++db;
            kiir(i);
        }
        kiir(db, " darab");
    }

    static int lnko(int a, int b) {
        if (a == b) return a;
        int c;
        while ((c = a % b) != 0) {
            a = b;
            b = c;
        }
        return b;
    }

    static Pair<Integer, Integer> masodik(final int a, final int b) {
        final int lnko = lnko(a, b);
        kiir("Törtünk: ", a, '/', b);
        final int ae = a / lnko;
        final int be = b / lnko;
        kiir("Egyszerűsített törtünk: ", ae, '/', be);
        return new Pair<>(ae, be);
    }

    static class Samu {
        boolean leesett = false;
        int lepett = 0;
        double kp = 0;
        private final double hid;
        private final double lepes;
        Samu(final double hid, final double lepes) {
            this.hid = hid / 2;
            this.lepes = lepes;
        }

        void lepj() {
            if (leesett) return;
            ++lepett;
            final double step = (Math.random() < .5 ? 1 : -1) * lepes;
            if (Math.abs(kp += step) > hid) {
                leesett = true;
            }
        }
    }

    public static void main(String[] args) {
        // Alap.main();

        elso();
        kiir();
        masodik(54351, 6456354);
    }
}
