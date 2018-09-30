package net.hallgato.progalap;

import org.w3c.dom.ranges.RangeException;

import java.util.HashMap;
import java.util.Scanner;
import java.util.Vector;

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
    private static <U, T extends Iterable<U>> Vector<U> uniques(T ... varargs) {
        final Vector<U> result = new Vector<>();
        if (varargs.length == 0) {
            return result;
        }
        final HashMap<U, Integer> map = new HashMap<>();
        for (final T element : varargs) {
            for (final U value : element) {
                final Integer previous = map.get(value);
                map.put(value, previous != null ? previous + 1 : 1);
            }
        }
        map.forEach((value, count) -> {
            if (count == 1) result.addElement(value);
        });
        return result;
    }

    @SafeVarargs
    private static <T> void kiir(T ... varargs) {
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

    static <T extends Comparable<T>> Pair<T, Long> max(T[] array) {
        T max = array[0];
        long count = 0;
        final int length = array.length;
        for (int i = 1; i < length; ++i) {
            final T elem = array[i];
            final int comparison = max.compareTo(elem);
            if (comparison < 0) {
                max = elem;
                count = 1;
            }
            else if (comparison == 0) {
                count++;
            }
        }
        return new Pair<>(max, count);
    }

    static String parosok(int[] tomb) {
        final int hossz = tomb.length;
        for (int i = 0; i < hossz; ++i) {
            if (tomb[i] % 2 == 0) continue;
            if (hossz % 2 == 1 && i == hossz / 2) return "Középső páratlan";
            return "Nem mind páros";
        }
        return "Mind páros";
    }

    private static double minmax(double max) {
        return Math.random() * max;
    }

    private static Pair<Integer, Double> minHol(double[] array) {
        int hol = 0;
        double min = array[0];
        for (int i = 1; i < array.length; ++i) {
            final double val = array[i];
            if (val < min) {
                hol = i;
                min = val;
            }
        }
        return new Pair<>(hol + 1, min);
    }

    private static Pair<Integer, Double> maxHol(double[] array) {
        int hol = 0;
        double max = array[0];
        for (int i = 1; i < array.length; ++i) {
            final double val = array[i];
            if (val > max) {
                hol = i;
                max = val;
            }
        }
        return new Pair<>(hol + 1, max);
    }

    private static int minMax(final int min, final int max) {
        return (int)(Math.random() * (max - min + 1)) + min;
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

    private static void harmadikFeladat() {
        final double[][] pontok = { { 18, -2 }, { 14, 0.5 }, { 11, 2 }, { 5, 1 }, { 0, -0.5 } };
        final double[] H = new double[24];
        double t = 7;

        for (int i = 0; i < 24; ++i) {
            for (final double[] pont : pontok) {
                if (i >= pont[0]) {
                    H[i] = t += minmax(pont[1]);
                    break;
                }
            }
        }

        final Pair<Integer, Double> min = minHol(H);
        final Pair<Integer, Double> max = maxHol(H);

        kiir("Hőmérséklet");
        kiir("Min volt ", min.first, " órakor: ", min.second);
        kiir("Max volt ", max.first, " órakor: ", max.second);
        kiir(Math.abs(min.first - max.first), " óra telt el a kettő között.");
    }

    private static void negyedikFeladat() {
        final int[] kettesek = new int[30];
        kettesek[0] = 1;
        for (int i = 1; i < 30; ++i) {
            kettesek[i] = 2 << (i - 1);
        }

        final int[] rand = new int[30];
        for (int i = 0; i < 30; ++i) {
            rand[i] = (int)(Math.random() + 1);
        }

        long osszeg = 0;
        for (int i = 0; i < 30; ++i) {
            osszeg += kettesek[i] * rand[i];
        }

        kiir("Szorzatok összege: ", osszeg);
    }

    private static void otodikFeladat() {
        final int[] szamok = new int[10];
        int n = (int)(Math.random() * Integer.MAX_VALUE);
        boolean ugyanaz = false;
        for (int i = 0; i < 10; ++i) {
            final int newN = n + (int)(Math.random() * 4);
            if (newN == n) {
                ugyanaz = true;
            }
            szamok[i] = n = newN;
        }

        kiir(ugyanaz ? "Monoton növekvő" : "Szigorúan monoton növekvő");
    }

    private static void hatodikFeladat() {
        final int[] dobasok = new int[100];
        final double[] count = new double[11];
        for (int i = 0; i < 100; ++i) {
            final int dobas = minMax(1, 6) + minMax(1, 6);
            ++count[dobas - 2];
            dobasok[i] = dobas;
        }

        final double legtobb = maxHol(count).second;

        for (int i = 10; i >= 0; --i) {
            final StringBuilder sor = new StringBuilder(78);
            final int displayNum = i + 2;
            sor.append(displayNum > 9 ? " " : "  ");
            sor.append(i + 2);
            sor.append(" | ");
            for (double j = 74 * (count[i] / legtobb); j > 0; --j) {
                sor.append("*");
            }
            kiir(sor.toString());
        }
    }

    private static void haziEgyes() {
        final int tombHossz = 10;
        final int[] szamok = new int[tombHossz];
        int osszeg = 0;
        for (int i = 0; i < tombHossz; ) {
            final int szam = minMax(40, 50);
            szamok[i] = szam;
            kiir(++i, ". rekesz: ", szam);
        }
        kiir();

        final int[] ujszamok = new int[tombHossz];
        ujszamok[0] = szamok[0];
        for (int i = 1; i < tombHossz; ++i) {
            ujszamok[i] = szamok[i - 1] + szamok[i];
        }

        for (int i = tombHossz; i > 0; ) {
            kiir(i, ". rekesz: ", ujszamok[--i]);
        }
        kiir();

        final Scanner scanner = new Scanner(System.in);

        final int[] indexek = { 0, 0 };
        _kulso:
        for (int i = 0; i < 2; ++i) {
            kiir("Add meg a(z) ", i + 1, ". indexet:");
            do {
                try {
                    final int index = scanner.nextInt();
                    if (index < 0 || tombHossz <= index) {
                        throw new Exception("");
                    }
                    indexek[i] = index;
                    continue _kulso;
                }
                catch (Exception e) {
                    kiir("Nem jó szám! Próbáld újra!");
                }
            } while(true);
        }

        kiir("Előtte: [", indexek[0], "] = ", ujszamok[indexek[0]], " [", indexek[1], "] = ", ujszamok[indexek[1]]);
        {
            final int temp = ujszamok[indexek[1]];
            ujszamok[indexek[1]] = ujszamok[indexek[0]];
            ujszamok[indexek[0]] = temp;
        }
        kiir("Utána:  [", indexek[0], "] = ", ujszamok[indexek[0]], " [", indexek[1], "] = ", ujszamok[indexek[1]]);
    }

    private static void haziKettes() {
        long Vanda = 0;
        long Vali = 0;
        int dobas;
        boolean side = false;
        do {
            dobas = minMax(1, 6) + minMax(1, 6);
            if (side = !side) {
                Vali += dobas;
            }
            else {
                Vanda += dobas;
            }
        } while(dobas != 12);
        kiir("Vanda: ", Vanda);
        kiir("Vali: ", Vali);
        kiir(Vanda > Vali ? "Vanda" : "Vali", " dobott nagyobb összeget");
    }

    private static void haziHarmas() {
        _szöveg: {
            boolean egy = Math.random() < 0.5;
            boolean ketto = Math.random() < 0.5;
            kiir(egy, " && ", ketto, " = ", egy && ketto);
        }

        _a: {
            for (int i = 0; i < 100; ++i) {
                if (Math.random() < 0.5 && Math.random() < 0.5) {
                    kiir(i + 1, " számú generálás után lett true && true");
                    break _a;
                }
            }
            kiir("Nem volt két igaz boolean");
        }

        _b: {
            for (int i = 0; i < 100; ++i) {
                /**
                 * egy kimenet érdekünk:
                 *     true && true
                 * 1 boolean 2 értéket adhat, de abból egy kell
                 *  esély = esemény(ek) / lehetséges eredmények
                 *               esély = 1 / 2
                 *                esély = 0.5
                 * tehát
                 *        2 esély = 0.5 ^ 2 * (1 / 1)
                 *                  = 0.25
                 * kb. 1 / 0.25 = 4 futtatás után várhatjuk a kívánt eredményt
                 */
                if (Math.random() < 0.5 && Math.random() < 0.5) {
                    kiir(i + 1, " számú generálás után lett true && true");
                    break _b;
                }
            }
            kiir("Nem volt két igaz boolean");
        }

        _c: {
            for (int i = 0; i < 100; ++i) {
                if (Math.random() < 0.5 || Math.random() < 0.5) {
                    kiir(i + 1, " számú generálás után lett true az egyik");
                    break _c;
                }
            }
            kiir("Nem volt egy igaz boolean");
        }

        _d: {
            for (int i = 0; i < 100; ++i) {
                /**
                 * kettő kimenet érdekünk:
                 *     true (|| ?)
                 *     false || true
                 * 1 boolean 2 értéket adhat, de abból egy kell
                 *  esély = esemény(ek) / lehetséges eredmények
                 *               esély = 1 / 2
                 *                esély = 0.5
                 * tehát
                 *        2 esély = 0.5 ^ 2 * (2 / 1)
                 *                  = 0.5
                 * kb. 1 / 0.5 = 2 futtatás után várhatjuk a kívánt eredményt
                 */
                if (Math.random() < 0.5 || Math.random() < 0.5) {
                    kiir(i + 1, " számú generálás után lett true az egyik");
                    break _d;
                }
            }
            kiir("Nem volt egy igaz boolean");
        }
    }

    public static void main(String[] args) {
        // /*
        elsoFeladat();
        kiir();
        harmadikFeladat();
        kiir();
        negyedikFeladat();
        kiir();
        otodikFeladat();
        kiir();
        hatodikFeladat();
        kiir();
        // */

        /* HÁZI FELADAT */

        haziEgyes();
        kiir();
        haziKettes();
        kiir();
        haziHarmas();
    }
}
