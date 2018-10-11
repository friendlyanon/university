package net.hallgato.progalap;


import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class MainTest {

    @Test
    public  void lnkoDifferent() {
        final int a = 10;
        final int b = 15;
        assertEquals(5, Main.lnko(a, b));
    }

    @Test
    public  void lnkoSame() {
        final int a = 15;
        final int b = 15;
        assertEquals(15, Main.lnko(a, b));
    }

    @Test
    public  void egyszerusitett() {
        final int a = 10;
        final int b = 15;
        final int lnko = Main.lnko(a, b);
        assertEquals(5, lnko);

        final Main.Pair<Integer, Integer> tort = Main.masodik(a, b);
        final int ae = tort.first;
        final int be = tort.second;
        assertEquals(2, ae);
        assertEquals(3, be);
    }

    @Test
    public  void samu() {
        Main.Samu samu = new Main.Samu(10, 1);
        while (!samu.leesett) {
            Main.kiir("Hol van Samu? ", samu.kp);
            samu.lepj();
        }
        Main.kiir("Samu leesett ", samu.lepett, " lépés után");
    }

}