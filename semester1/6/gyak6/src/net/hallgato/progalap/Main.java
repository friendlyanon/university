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

    static void harmadik(int hid, boolean e) {
        Samu samu = new Samu(hid, 1);
        final char[] pozicio = new char[hid += 3];
        for (int i = 0; i < hid; ++i) {
            pozicio[i] = '_';
        }
        final int half = (int)(hid / 2.0);
        while (!samu.leesett) {
            kiiratas: {
                if (e && (samu.lepett == 0 || samu.lepett % 100 != 0)) break kiiratas;
                final int index = half + (int) Math.round(samu.kp);
                pozicio[index] = 'S';
                kiir(new String(pozicio));
                pozicio[index] = '_';
            }
            samu.lepj();
        }
        kiir("Samu leesett ", samu.lepett, " lépés után");
    }

    static double negyedikAtlag(final int[] szamok) {
        if (szamok == null || szamok.length == 0) return 0;
        double osszeg = 0;
        for (final int szam : szamok) {
            osszeg += szam;
        }
        return osszeg / szamok.length;
    }

    static double negyedikAtfogo(final int[] szamok) {
        if (szamok == null || szamok.length != 2) throw new IllegalArgumentException();
        return Math.sqrt(Math.pow(szamok[0], 2) + Math.pow(szamok[1], 2));
    }

    static void negyedik() {
        final int[] szamok = new int[2];
        for (int i = 0; i < 2; ++i) {
            kiir("Add meg a(z) ", i + 1, ". számot:");
            for (;;) {
                try {
                    final int szam = Integer.parseInt(scanner.nextLine());
                    szamok[i] = szam;
                    break;
                }
                catch (Exception e) {
                    kiir("Nem jó! Próbáld újra:");
                }
            }
        }
        kiir("Átlag: ", negyedikAtlag(szamok));
        kiir("Pitagorasz tétel");
        for (int i = 0; i < 2; ++i) {
            kiir("Add meg a(z) ", i + 1, ". oldalt:");
            for (;;) {
                try {
                    final int szam = Integer.parseInt(scanner.nextLine());
                    if (szam <= 0) throw new Exception();
                    szamok[i] = szam;
                    break;
                }
                catch (Exception e) {
                    kiir("Nem jó! Próbáld újra:");
                }
            }
        }
        kiir("Átfogó mérete: ", negyedikAtfogo(szamok));
    }

    static void haziEgyes() {
        long n;
        kiir("Addj meg egy számot:");
        for (;;) {
            try {
                n = Integer.parseInt(scanner.nextLine());
                break;
            }
            catch (Exception e) {
                kiir("Nem egy szám! Próbáld újra:");
            }
        }
        long osszeg = 0;
        for (long i = 1; i <= n; ++i) {
            osszeg += i * i;
        }
        kiir("Megegyezik az összegképlettel? ", osszeg == n * (n + 1) * (2 * n + 1) / 6);
    }

    static void haziKettes() {
        int a = 0;
        int b = 0;
        kiir("a/b + b/a egyszerűsítése");
        kiir("Add meg az a és a b számokat:");
        try { a = Integer.parseInt(scanner.nextLine()); }
        catch (Exception ignore) {}
        try { b = Integer.parseInt(scanner.nextLine()); }
        catch (Exception ignore) {}
        /**
         * Nullával nem oszthatunk
         */
        if (a == 0 || b == 0) {
            kiir("Nincs megoldás.");
            return;
        }
        kiir(a, '/', b, " + ", b, '/', a, " egyszerűsítése:");
        final int lnko = lnko(a, b);
        a = a / lnko;
        b = b / lnko;
        kiir(a, '/', b, " + ", b, '/', a);
    }

    static int[] makeDayTemp(int start) {
        final double[][] pontok = { { 20, -1.25 }, { 15, -0.5 }, { 8, 2 }, { 0, -1 } };
        final int[] H = new int[24];
        double t = start;

        for (int i = 0; i < 24; ++i) {
            for (final double[] pont : pontok) {
                if (i >= pont[0]) {
                    H[i] = (int)(t += Math.random() * pont[1]);
                    break;
                }
            }
        }
        return H;
    }

    static double[] makeStats(final int[] nap) {
        final double[] stats = new double[4];
        final double min = stats[0] = minHol(nap).second;
        final double max = stats[1] = maxHol(nap).second;
        stats[2] = atlag(nap);
        stats[3] = max - min;
        return stats;
    }

    static int leghidegebb(final double[][] stats) {
        final int len = stats.length;
        if (len == 0) return 0;
        int idx = 0;
        double min = stats[0][0];
        for (int i = 1; i < len; ++i) {
            if (min > stats[i][0]) {
                idx = i;
            }
        }
        return idx + 1;
    }

    static int legmelegebb(final double[][] stats) {
        final int len = stats.length;
        if (len == 0) return 0;
        int idx = 0;
        double max = stats[0][1];
        for (int i = 1; i < len; ++i) {
            if (max < stats[i][1]) {
                idx = i;
            }
        }
        return idx + 1;
    }

    static void haziHarmas() {
        final int[][] napok = new int[3][];
        int last = 7;
        for (int i = 0; i < 3; ++i) {
            final int[] nap = makeDayTemp(last);
            last = nap[23];
            napok[i] = nap;
        }
        final double[][] statisztikak = new double[3][];
        for (int i = 0; i < 3; ++i) {
            statisztikak[i] = makeStats(napok[i]);
        }
        kiir("Az ", leghidegebb(statisztikak), ". nap volt a leghidegebb.");
        kiir("Az ", legmelegebb(statisztikak), ". nap volt a legmelegebb.");
    }

    public static void main(String[] args) {
        // /*
        elso();
        kiir();
        masodik(54351, 6456354);
        kiir();
        harmadik(10, false);
        harmadik(20, true);
        harmadik(50, true);
        kiir();
        negyedik();
        kiir();
        Alap.main(); // ötödik
        kiir();
        // */

        // HÁZI FELADAT
        haziEgyes();
        kiir();
        haziKettes();
        kiir();
        haziHarmas();
    }
}
