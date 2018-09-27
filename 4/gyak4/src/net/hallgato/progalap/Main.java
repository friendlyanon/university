package net.hallgato.progalap;

public class Main {
    static String parosok(int[] tomb) {
        final int hossz = tomb.length;
        final int fele = (hossz - 1) / 2;
        for (int i = 0; i < hossz; ++i) {
            if (tomb[i] % 2 == 0) continue;
            return i == fele ? "Középső páratlan" : "Nem mind páros";
        }
        return "Mind páros";
    }

    static class Pair<T, U> {
        final T first;
        final U second;

        Pair(T t, U u) {
            this.first = t;
            this.second = u;
        }
    }

    private static void kiir() {
        System.out.println();
    }

    private static <T> void kiir(T ertek) {
        System.out.println(ertek);
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

    public static void main(String[] args) {
	    final int tombHossz = 21;
	    final Integer[] szazalek = new Integer[tombHossz];
	    for (int i = 0; i < tombHossz; ++i) {
	        szazalek[i] = (int)(Math.random() * 101);
        }

	    Pair<Integer, Long> parok = max(szazalek);
        kiir("Legnyagyobb érték: " + parok.first);
        kiir("Hányszor fordul elő: " + parok.second);

        for (final int elem : szazalek) {
            if (elem < 5) {
                kiir("Van 5-nél kisebb szám");
                break;
            }
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
            kiir("Hány szám éri el a 91-et? " + darab);
            kiir("Átlaguk: " + (atlag /= darab));
        }
        else {
            kiir("Egy szám sem éri el a 91-et");
        }
    }
}
