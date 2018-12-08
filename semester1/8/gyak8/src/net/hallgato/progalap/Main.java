package net.hallgato.progalap;

import java.util.Arrays;

public class Main {

    @SafeVarargs
    static <T> void kiir(T ... args) {
        if (args == null) return;
        for (final T e : args) {
            if (e == null) continue;
            System.out.print(e);
        }
        System.out.print('\n');
    }

    static void csere1(int a, int b) {
        // primitív értékek érték szerint vannak átadva, ennek az eljárásnak a
        // testén kívül nem látszik a paraméterek módosítása
        int c = a;
        a = b;
        b = c;
    }

    static void csere2(int[] t) {
        // a tömb az egy összetett típus, ezért referencia értékként van átadva
        // ennek az eljárásnak, tehát látszanak a változások kívülről is
        final int l = t.length - 1;
        if (l < 1) return;
        int a = t[0];
        t[0] = t[l];
        t[l] = a;
    }

    static double[] tombGen(final int hossz, final double min, final double max) {
        final double[] tomb = new double[hossz];
        final double diff = max - min;
        for (int i = 0; i < hossz; ++i) {
            tomb[i] = Math.random() * diff + min;
        }
        return tomb;
    }

    static int maxIndex(final double[] tomb) {
        final int l = tomb.length;
        switch (l) {
            case 0: return -1;
            case 1: return 0;
        }
        double max = tomb[0];
        int index = 0;
        for (int i = 0; i < l; ++i) {
            if (max < tomb[i]) {
                index = i;
            }
        }
        return index;
    }

    public static void main(String[] args) {
        int a = 3, b = 6;
        final int [] tomb = { 1, 2, 3, 4, 5, 6, 7, 8, 9 };
        kiir("Hívás elött: a = ", a, ", b = ", b);
	    csere1(a, b);
	    kiir("Hívás után: a = ", a, ", b = ", b);
	    kiir();
	    kiir("Hívás elött: ", Arrays.toString(tomb));
	    csere2(tomb);
        kiir("Hívás után: ", Arrays.toString(tomb));
        kiir();
        final int hossz = 6;
        final double[] maxSebesseg = tombGen(hossz, 200, 400);
        final double[] fogyasztas = tombGen(hossz, 5.5, 10);
        kiir("Max sebesség és fogyasztás");
        for (int i = 0; i < hossz; ++i) {
            kiir(i + 1, ". ", maxSebesseg[i], " kmph, ", fogyasztas[i], " l/100 km");
        }
        final int maxIdx = maxIndex(maxSebesseg);
        kiir("Leggyorsabb autó:\n", maxIdx + 1, ". ", maxSebesseg[maxIdx], " kmph, ", fogyasztas[maxIdx], " l/100 km");
        kiir();
        // TODO befejezni
    }
}
