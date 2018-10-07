package net.hallgato.progalap;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MainTest {

    @Test
    void masodik() {
        // Main.masodikFeladat(new int[1]);
        final int mettol = 40;
        final int meddig = 100;
        final int szam = Main.randomGen(mettol, meddig);
        for (int i = 0; i < 10; i++) {
            assertTrue(mettol <= szam && szam <= meddig);
        }
    }

    @Test
    void generalas(final int hossz, final int mettol, final int meddig, final int db) {
        final int[] tomb = Main.tombGen(hossz, mettol, meddig, db);
        for (int i = 0; i < db; ++i) {
            final int elem = tomb[i];
            assertTrue(mettol <= elem && elem <= meddig);
        }
        assertTrue(db <= hossz, "A tömböt tovább akarod tölteni, mint lehetséges");
        assertTrue(mettol <= meddig, "Az intervallum eleje nagyobb, mint a vége");
        assertTrue(db == hossz || tomb[db] == 0, "Tovább generáltad mint a db");
    }

    @Test
    void minIndexTest() {
    }

    @Test
    void tombGenUj() {
        generalas(10, 20, 50, 5);
    }

    @Test
    void tombTorol() {
        final int[] tomb = { 2, 3, 4, 0, 0, 0 };
        int db = 3;
        final int minIndex = Main.minHol(tomb, db).first;
        Main.tombKiir(tomb);

        db = Main.tombTorol(tomb, db, minIndex);
        Main.tombKiir(tomb);
        assertEquals(2, db);
        assertEquals(3, tomb[0]);
        assertEquals(4, tomb[1]);
    }
}