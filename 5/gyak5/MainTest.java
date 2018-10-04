import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MainTest {

    @Test
    public void randomSzamTeszt() {
        Main main = new Main();
        int mettol = 40;
        int meddig = 100;
        int szam = main.randomGen(mettol, meddig);
        for (int i = 0; i < 100000000; i++) {
            assertTrue(szam >= mettol && szam <= meddig, "Intervallumon kívüli szám generálódott");
        }
    }

    @Test
    public void tombGeneralasTeszt(int hossz, int mettol, int meddig, int db) {
        Main main = new Main();
        int[] tomb = main.tombGeneral(hossz, mettol, meddig, db);
        for (int i = 0; i < db; i++) {
            assertTrue(tomb[i] >= mettol && tomb[i] <= meddig, "Intevallumon kívüli tömbelem generálódott");
        }
        assertTrue(db <= hossz, "A tömböt tovább akarod tölteni, mint lehetséges");
        assertTrue(mettol <= meddig, "Az intervallum eleje nagyobb, mint a vége");
        assertTrue((db == hossz) || (db != hossz && tomb[db] == 0), "Tovább generálódott a szám");
    }

    @Test
    public void tombGenUj() {
        tombGeneralasTeszt(20,40,100,10);
        tombGeneralasTeszt(20,40,100,20);
        tombGeneralasTeszt(20,0,100,20);
    }
}