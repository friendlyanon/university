package net.hallgato.progalap;

public class Main {
    /**
     * Első óra: Hello World, aritmetika
     * @param args -
     */
    public static void main(String[] args) {
        try (Lifetime l = new Lifetime()) {
            for (long i = 0; i < 3; ++i) {
                l.log("Hello World!");
            }
            l.log();
            l.log(2 + 3 * 4 - 6);
            l.log(14 / 7 * 2 + 30 / 5 + 1);
            l.log((12 + 3) / 4 * 2);
            l.log(2 + 19 % 5 - (11 * (5 / 2)));
            l.log(813 % 100 / 3 + 2.4);
            l.log();
            int a = 16;
            l.log("Szorzás előtt: " + a);
            a *= 4.5;
            l.log("Szorzás után: " + a);
            a += 15;
            l.log("\n4. feladat\ni) " + a);
            a /= 2;
            l.log("j) " + a);
            a *= 23;
            l.log("k) " + a);
            a %= 7;
            l.log("l) " + a);
            l.log("\n5. feladat");
            double terforgat = l.getDouble("Add meg a sugarat: ");
            terforgat *= terforgat;
            terforgat *= Math.PI;
            terforgat *= l.getDouble("Add meg a magasságot: ");
            l.log("Térfogat: " + (terforgat / 3));
        }
    }
}