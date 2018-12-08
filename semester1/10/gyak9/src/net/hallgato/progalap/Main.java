package net.hallgato.progalap;

public class Main {
    private static Pont[] pontok = null;
    private static int ennyiminjo(Hallgato[] gain1, int maxpont) {
        int count = 0;
        double hetven = maxpont * 0.7;
        for (Hallgato hallgato : gain1) {
            if (hallgato.pont >= hetven) {
                System.out.println("Hallgató (kód: " + hallgato.kod + ") elérte a maximum pont 70%-át (pont: " + hallgato.pont + ")");
                ++count;
            }
        }
        return count;
    }
    private static boolean novekvo(int[] t, int db) {
        for (int i = 1; i < db; ++i) {
            if (t[i - 1] > t[i]) {
                return false;
            }
        }
        return true;
    }
    private static int keres(int[] t, int db, int q) {
        if (novekvo(t, db) && (q < t[0] || t[db - 1] < q)) {
            return -1;
        }
        for (int i = 0; i < db; ++i) {
            if (t[i] == q) return i;
        }
        return -1;
    }
    private static int binkeres(int[] t, int db, int q) {
        if (db < 0 || t.length < db) {
            return -2;
        }
        for (int start = 0, end = db - 1; start <= end; ) {
            int mid = (start + end) >> 1;
            int val = t[mid];
            if (val == q) {
                return mid;
            }
            if (val < q) {
                start = mid + 1;
            }
            else {
                end = mid - 1;
            }
        }
        return -1;
    }
    private static void elso() {
        Hallgato[] hallgatok = new Hallgato[5];
        for (int i = 0; i < 5; ++i) {
            Hallgato hallgato = new Hallgato();
            hallgatok[i] = hallgato;
            hallgato.pont = (int)(Math.random() * 100);
            hallgato.kod = (int)(Math.random() * 100);
        }
        System.out.println(ennyiminjo(hallgatok, 100));
    }
    private static void masodik() {
        int[] t2 = { 1, 2, 3, 4, 5, 6, 7, 9, 11, 13, 19, 32, 45, 0, 0, 0, 0, 0, 0, 0 };
        int db = 13;
        System.out.println(novekvo(t2, db));
        int q = (int)(Math.random() * 101) - 20;
        System.out.println("Keres: " + q + ", Index: " + keres(t2, db, q));
        System.out.println("Binkeres: " + -10 + ", Index: " + binkeres(t2, db, -10));
        System.out.println("Binkeres: " + 60 + ", Index: " + binkeres(t2, db, 60));
        System.out.println("Binkeres: " + 20 + ", Index: " + binkeres(t2, db, 20));
        System.out.println("Binkeres: " + 45 + ", Index: " + binkeres(t2, db, 45));
        System.out.println("Binkeres: " + 1 + ", Index: " + binkeres(t2, db, 1));

    }
    private static int lagmagindex(Ember[] t) {
        for (int i = 1, max = 0;;) {
            if (t[i].testmag > t[max].testmag) {
                max = i;
            }
            if (++i >= 28) {
                return max;
            }
        }
    }
    private static void harmadik() {
        Ember[] emberek = new Ember[28];
        String abc = "AÁBCDEÉFGHIJKLOÖŐPRSTUVZ";
        for (int i = 0; i < 28; ++i) {
            Ember ember = new Ember();
            ember.monogram = "";
            for (int j = 0, len = Math.random() < 0.5 ? 2 : 3; j < len; ++j) {
                ember.monogram += abc.charAt((int)(Math.random() * abc.length()));
            }
            ember.testmag = (int)(Math.random() * 101) + 100;
            emberek[i] = ember;
        }
        for (int i = 1, max = 0;;) {
            if (emberek[i].testmag > emberek[max].testmag) {
                max = i;
            }
            if (++i >= 28) {
                System.out.println("Legmagasabb (" + (emberek[max].testmag / 100.0) + " m) monogramja: " + emberek[max].monogram);
                break;
            }
        }
        for (int i = 0; i < 28; ++i) {
            Ember ember = emberek[i];
            if (ember.monogram.length() == 3) {
                System.out.println(ember.monogram + " testmagassága: " + ember.testmag);
            }
        }
        System.out.println("Legmagasabb indexe: " + lagmagindex(emberek));
    }
    private static double tav(Pont mettol, Pont meddig) {
        return Math.sqrt(Math.pow(mettol.x - meddig.x, 2) + Math.pow(mettol.y - meddig.y, 2));
    }
    private static void negyedik() {
        Pont origo = new Pont(0, 0);
        pontok = new Pont[10];
        for (int i = 0; i < 10; ++i) {
            Pont pont = new Pont(Math.random() * 12 - 4, Math.random() * 12 - 4);
            pont.tav = tav(pont, origo);
            pontok[i] = pont;
        }
        for (int i = 1, max = 0;;) {
            if (pontok[i].tav > pontok[max].tav) {
                max = i;
            }
            if (++i >= 10) {
                System.out.println("Legtávolabbi pont: " + pontok[max].tav + ", index: " + max);
                break;
            }
        }
        double atlagX = 0;
        double atlagY = 0;
        for (int i = 0; i < 10; ++i) {
            Pont pont = pontok[i];
            atlagX += pont.x;
            atlagY += pont.y;
        }
        Pont sp = new Pont(atlagX / 10, atlagY / 10);
        System.out.println("Súlypont - x: " + sp.x + ", y: " + sp.y);
        System.out.println("Súlypont " + tav(origo, sp) + " egységre van az origótól");
        for (int i = 0; i < 10; ++i) {
            Pont pont = pontok[i];
            pont.stav = tav(pont, sp);
        }
    }
    private static int haziElso() {
        for (int i = 0, len = pontok.length; i < len; ++i) {
            final double tav = pontok[i].tav;
            if (9 < tav && tav < 11) return i;
        }
        return -1;
    }
    private static int haziMasodik(int[] t1, int db1, int[] t2, int db2) {
        // feladat szövege elolvasása után: https://i.imgur.com/rDdgZTZ.jpg
        for (int i = 0, k = 0, j = db1; i < db1; ++i) {
            final int val = t1[i];
            if (val < 0) {
                t2[k++] = val;
            }
            else {
                t2[--j] = val;
            }
        }
        return db1;
    }
    private static int[] csakparos(int[] t, int db, int[] t2) {
        int db2 = 0;
        for (int i = 0; i < db; ++i) {
            int val = t[i];
            if (val % 2 > 0) continue;
            t2[db2++] = val;
        }
        return t2;
    }
    private static int takarekosCsakparos(int[] t, int db) {
        int db2 = 0;
        for (int i = 0; i < db; ++i) {
            int val = t[i];
            if (val % 2 > 0) continue;
            t[db2++] = val;
        }
        return db2;
    }
    private static void haziHarmadik() {
        int[] t = new int[10];
        int[] t2 = new int[10];
        for (int i = 0; i < 4; ++i) {
            t[i] = (int)(Math.random() * 101);
        }
        csakparos(t, 4, t2);
        takarekosCsakparos(t, 4);
    }
    public static void main(String[] args) {
        elso();
        masodik();
        harmadik();
        negyedik();

        System.out.println("1. házi - index: " + haziElso());
        int[] t1 = new int[20];
        int[] t2 = new int[20];
        for (int i = 0; i < 10; ++i) {
            t1[i] = (int)(Math.random() * 11);
        }
        for (int i = 0; i < 5; ++i) {
            t2[i] = (int)(Math.random() * 11);
        }
        haziMasodik(t1, 10, t2, 5);
        haziHarmadik();
    }
}
