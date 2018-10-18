import java.util.Arrays;
import java.util.HashSet;
import java.util.Scanner;

public class Main {

    static private Scanner scanner = new Scanner(System.in);

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

    static class Pair<T, U> {
        final T first;
        final U second;
        Pair(T t, U u) {
            first = t;
            second = u;
        }
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
        double[] tomb = new double[(int)hossz];
        for (int i = 0; i < hossz; ++i) {
            tomb[i] = Math.random() * (max - min) + min;
        }
        return tomb;
    }

    static int[] randTombGen(double hossz, int min, int max) {
        int[] tomb = new int[(int)hossz];
        for (int i = 0; i < hossz; ++i) {
            tomb[i] = (int)(Math.random() * (max - min + 1)) + min;
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

    static double safeNextDouble() {
        try { return scanner.nextDouble(); }
        catch (Exception ignore) { return 0; }
    }

    static double diszkriminans(double a, double b, double c) {
        return b * b - 4 * a * c;
    }

    static Pair<Double, Double> egyenletSzamito(double diszkr, double a, double b) {
        if (diszkr < 0) {
            throw new IllegalArgumentException();
        }
        if (diszkr > 0) {
            final double gyok = Math.sqrt(diszkr);
            final double oszto = 2 * a;
            b = -b;
            final Double x1 = ( b + gyok ) / oszto;
            final Double x2 = ( b - gyok ) / oszto;
            return new Pair<>(x1, x2);
        }
        else {
            final Double x = - ( b / ( 2 * a ) );
            return new Pair<>(x, x);
        }
    }

    static void haziEgy() {
        kiir("Másodfokú egyenlet ( a * x^2 + 2 * b * x + c )");
        kiir("a: ");
        final double a = safeNextDouble();
        kiir("b: ");
        final double b = safeNextDouble();
        kiir("c: ");
        final double c = safeNextDouble();

        final StringBuilder buf = new StringBuilder();
        if (a != 0) {
            if (a % 1 == 0) buf.append((int)a);
            else buf.append(a);
            buf.append("x^2");
        }
        if (b != 0) {
            buf.append(b < 0 ? " - " : " + ");
            if (b % 1 == 0) buf.append(2 * (int)b);
            else buf.append(2 * b);
            buf.append("x");
        }
        if (c != 0) {
            buf.append(c < 0 ? " - " : " + ");
            if (c % 1 == 0) buf.append((int)c);
            else buf.append(c);
        }
        kiir(buf.toString());

        final double diszkr = diszkriminans(a, b, c);
        try {
            final Pair<Double, Double> gyokok = egyenletSzamito(diszkr, a, b);
            kiir("x1 = ", gyokok.first, " x2 = ", gyokok.second);
        }
        catch (Exception ignore) {
            kiir("Nincs valós megoldás");
        }
    }

    static boolean megegyezoVane(int[] tomb) {
        final int len = tomb.length;
        switch (len) {
            case 0: case 1:
                return false;
            case 2:
                return tomb[0] == tomb[1];
        }
        for (int i = 0; i < len; ++i) {
            for (int j = 0; i < len; ++i) {
                if (i == j) continue;
                if (tomb[i] == tomb[j]) return true;
            }
        }
        return false;
    }

    static boolean egymasutanMegegyezoVane(int[] tomb) {
        final int len = tomb.length;
        switch (len) {
            case 0: case 1:
                return false;
            case 2:
                return tomb[0] == tomb[1];
        }
        for (int i = 1; i < len; ++i) {
            if (tomb[i - 1] == tomb[i]) return true;
        }
        return false;
    }

    static boolean negyzetszamVane(int[] tomb) {
        final int len = tomb.length;
        for (int elem : tomb) {
            if (Math.sqrt(elem) % 1 == 0) return true;
        }
        return false;
    }

    static void haziKetto(int[] tomb) {
        egyezok: {
            final boolean megegyezo = megegyezoVane(tomb);
            kiir(megegyezo ? "Van" : "Nincs", " kettő egyenlő elem");
            if (!megegyezo) {
                kiir("Nincs egymás után következő, egyenlő elemek");
                break egyezok;
            }
            kiir(egymasutanMegegyezoVane(tomb) ? "Van" : "Nincs", " egymás után következő, egyenlő elem");
        }
        kiir(negyzetszamVane(tomb) ? "Van" : "Nincs", " négyzetszám");
    }

    public static void main(String[] args) {
        // /*
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
        kiir();
        // */

        // HÁZI FELADAT

        haziEgy();
        kiir();
        final int[] tomb1 = randTombGen(1000.0, 0, 100);
        haziKetto(tomb1);
    }
}
