package net.hallgato.progalap;

import com.sun.jdi.InvalidTypeException;

import java.util.List;

public class Main {

    static class Pair<T, U> {
        final T first;
        final U second;

        Pair(T t, U u) {
            first = t;
            second = u;
        }
    }

    @SafeVarargs
    private static <T> void kiir(T ... elemek) {
        if (elemek.length == 0) {
            System.out.println();
            return;
        }
        for (final T elem : elemek) {
            System.out.print(elem);
        }
        System.out.print('\n');
    }

    @SuppressWarnings("unchecked")
    static <T extends Comparable<T>> Pair<T, Long> max(T[] ertek) {
        T max = ertek[0];
        long count = 0;
        for (int i = 1; i < ertek.length; ++i) {
            final T elem = ertek[i];
            if (max.compareTo(elem) < 0) {
                max = elem;
                count = 1;
            }
            else {
                count++;
            }
        }
        return new Pair(max, count);
    }

    static String parosok(int[] tomb) {
        final int hossz = tomb.length;
        for (int i = 0; i < hossz; ++i) {
            if (tomb[i] % 2 == 0) continue;
            if (hossz % 2 == 0) return "Nem mind páros";
            return i == hossz / 2 ? "Középső páratlan" : "Nem mind páros";
        }
        return "Mind páros";
    }

    private static void elsoFeladat() {
        final int tombHossz = 21;
        final Integer[] szazalek = new Integer[tombHossz];
        for (int i = 0; i < tombHossz; ++i) {
            szazalek[i] = (int)(Math.random() * 101);
        }

        final Pair<Integer, Long> parok = max(szazalek);
        kiir("Legnyagyobb érték: ", parok.first);
        kiir("Hányszor fordul elő: ", parok.second);

        outer: {
            for (final int elem : szazalek) {
                if (elem < 5) {
                    kiir("Van 5-nél kisebb szám");
                    break outer;
                }
            }
            kiir("Nincs 5-nél kisebb szám");
        }

        double atlag = 0;
        int darab = 0;
        for (final int elem : szazalek) {
            if (elem > 90) {
                ++darab;
                atlag += elem;
            }
        }
        if (darab > 0) {
            kiir("Hány szám éri el a 91-et? ", darab, "\nÁtlaguk: ", (atlag / darab));
        }
        else {
            kiir("Egy szám sem éri el a 91-et");
        }
    }

    public static void main(String[] args) {
        elsoFeladat();
    }
}
