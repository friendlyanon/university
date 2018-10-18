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

    @SafeVarargs
    static <T> String kiirProxy(T ... args) {
        if (args == null) return "";
        final StringBuilder b = new StringBuilder();
        for (final T elem : args) {
            b.append(elem);
        }
        return b.toString();
    }

    static int nagyobb(int a, int b) {
        return Math.max(a, b);
    }

    static class StringWrapper {
        String str = "";
    }

    static int kozepsoProxy(StringWrapper out, int ... szamok) {
        if (szamok == null || szamok.length != 3) return 0;
        Arrays.sort(szamok);
        out.str = "Min: " + szamok[0] + " Max: " + szamok[2];
        return szamok[1];
    }

    static int kozepso(int ... szamok) {
        if (szamok == null || szamok.length != 3) return 0;
        Arrays.sort(szamok);
        kiir("Min: ", szamok[0], " Max: ", szamok[2]);
        return szamok[1];
    }

    static boolean vane7(int[] tomb) {
        for (final int elem : tomb) {
            if (elem % 7 == 0) return true;
        }
        return false;
    }

    static int lnko(int a, int b) {
        if (a == b) return a;
        if (Math.min(a, b) < 0) {
            throw new IllegalArgumentException("Két pozitív egész szám adható csak meg!");
        }
        int c;
        while ((c = a % b) != 0) {
            a = b;
            b = c;
        }
        return b;
    }

    static void tombfeltolto(int[] tomb) {
        final int hossz = tomb.length;
        for (int i = 0, k = 0; k < hossz; ++i) {
            final int szam = i + 1;
            if (szam % 7 == 0 || szam % 13 == 0) continue;
            tomb[k++] = szam;
        }
    }

    static double[] randTombGen(int hossz, int min, int max) {
        double[] tomb = new double[hossz];
        for (int i = 0; i < hossz; ++i) {
            tomb[i] = Math.random() * (max - min) + min;
        }
        return tomb;
    }

    static double pozitivAtlag(double[] tomb) {
        double osszeg = 0.0;
        int db = 0;
        for (final double szam : tomb) {
            if (szam > 0) {
                osszeg += szam;
                ++db;
            }
        }
        switch (db) {
            case 0:
                return 0;
            case 1:
                return osszeg;
            default:
                return osszeg / db;
        }
    }

    public static void main(String[] args) {
        kiir("42 és 69 közül melyik a legnyagyobb? ", nagyobb(42, 69));
        kiir("Középső: ", kozepso(1, 2, 3));
        final int[] tomb = { 4, 5, 6, 7, 8, 9 };
        kiir(vane7(tomb) ? "Van" : "Nincs", " 7-tel osztható szám a tömbben!");
        int a = 8;
        int b = 6;
        int _lnko = lnko(a, b);
        kiir("a) ", a == 8 && b == 6 ? "Nem változtak" : "Megváltoztak" , " az átadott számok");
        kiir("b) ", a / _lnko, '/', b / _lnko);
        final int[] tomb2 = new int[20];
        tombfeltolto(tomb2);
        kiir("Utolsó elem: ", tomb2[tomb2.length - 1]);
        final double[] tomb3 = randTombGen(20, -10, 10);
        kiir(Arrays.toString(tomb3));
        kiir("Pozitív számok átlaga: ", pozitivAtlag(tomb3));
    }
}
