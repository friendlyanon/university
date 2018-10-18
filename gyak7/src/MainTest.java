import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class MainTest {

    @Test
    void nagyobb_42_69() {
        assertEquals(69, Main.nagyobb(42, 69));
    }

    @Test
    void nagyobb_abzsolutok() {
        assertEquals(Integer.MAX_VALUE, Main.nagyobb(Integer.MAX_VALUE, Integer.MIN_VALUE));
    }

    @Test
    void nagyobb_egyenlo() {
        assertEquals(0, Main.nagyobb(0, 0));
    }


    @Test
    void kozepso_harmadik() {
        Main.StringWrapper wrapper = new Main.StringWrapper();
        assertEquals(6, Main.kozepsoProxy(wrapper, 9, 5, 6));
        assertEquals("Min: 5 Max: 9", wrapper.str);
    }

    @Test
    void kozepso_elso() {
        Main.StringWrapper wrapper = new Main.StringWrapper();
        assertEquals(2, Main.kozepsoProxy(wrapper, 2, 1, 3));
        assertEquals("Min: 1 Max: 3", wrapper.str);
    }

    @Test
    void kozepso_masodik() {
        Main.StringWrapper wrapper = new Main.StringWrapper();
        assertEquals(8, Main.kozepsoProxy(wrapper, 9, 8, 1));
        assertEquals("Min: 1 Max: 9", wrapper.str);
    }

    @Test
    void vane7_ures() {
        final int[] tomb = {};
        assertFalse(Main.vane7(tomb));
    }

    @Test
    void vane7_csak7() {
        final int[] tomb = { 7 };
        assertTrue(Main.vane7(tomb));
    }

    @Test
    void vane7_nincs7() {
        final int[] tomb = { 5, 6, 8, 9 };
        assertFalse(Main.vane7(tomb));
    }

    @Test
    void lnko_egyenlo() {
        assertDoesNotThrow(() -> Main.lnko(4, 4));
        final int lnko = Main.lnko(4, 4);
        assertEquals(4, lnko);
    }

    @Test
    void lnko_minusz() {
        assertThrows(IllegalArgumentException.class, () -> Main.lnko(-4, 4));
    }

    @Test
    void tombfeltolto_20_hossz_utolso_elem_24() {
        final int[] tomb = new int[20];
        Main.tombfeltolto(tomb);
        assertEquals(24, tomb[tomb.length - 1]);
    }

    @Test
    void tombfeltolto_10_hossz_utolso_elem_11() {
        final int[] tomb = new int[10];
        Main.tombfeltolto(tomb);
        assertEquals(11, tomb[tomb.length - 1]);
    }

    @Test
    void randTombGen_ertektartomany() {
        final int min = -10;
        final int max = 10;
        final int hossz = 20;
        final double[] tomb = Main.randTombGen(hossz, min, max);
        assertEquals(hossz, tomb.length);
        Arrays.sort(tomb);
        assertTrue(tomb[0] >= min);
        assertTrue(tomb[tomb.length - 1] < max);
    }

    @Test
    void randTombGen_0_hossz() {
        final double[] tomb = Main.randTombGen(0, 0, 1);
        assertEquals(0, tomb.length);
    }

    @Test
    void pozitivAtlag_van_pozitiv_1db() {
        final double[] tomb = { -1, -2, 3 };
        double osszeg = Main.pozitivAtlag(tomb);
        assertEquals(3.0, osszeg);
    }

    @Test
    void pozitivAtlag_van_pozitiv_2db() {
        final double[] tomb = { -1, -2, 3, 4 };
        double osszeg = Main.pozitivAtlag(tomb);
        assertEquals(3.5, osszeg);
    }

    @Test
    void pozitivAtlag_van_pozitiv_tobb_db() {
        final double[] tomb = { -1, -2, 3, 4, 5 };
        double osszeg = Main.pozitivAtlag(tomb);
        assertEquals(4.0, osszeg);
    }

    @Test
    void pozitivAtlag_nincs_pozitiv() {
        final double[] tomb = { -1, -2, -3, -4 };
        double osszeg = Main.pozitivAtlag(tomb);
        assertEquals(0.0, osszeg);
    }

    @Test
    void diszkriminans_123() {
        final double diszkr = Main.diszkriminans(1, 2, 3);
        assertEquals(-8.0, diszkr);
    }

    @Test
    void egyenlet_0_1_2() {
        final Main.Pair<Double, Double> gyokok = Main.egyenletSzamito(0, 1, 2);
        assertEquals(gyokok.first.doubleValue(), gyokok.second.doubleValue());
    }

    @Test
    void egyenlet_minusz1_1_2() {
        assertThrows(IllegalArgumentException.class, () -> Main.egyenletSzamito(-1, 1, 2));
    }

    @Test
    void egyenlet_1_1_2() {
        final Main.Pair<Double, Double> gyokok = Main.egyenletSzamito(1, 1, 2);
        assertNotEquals(gyokok.first.doubleValue(), gyokok.second.doubleValue());
    }

    @Test
    void megegyezoVane_ures() {
        final int[] tomb = {};
        assertFalse(Main.megegyezoVane(tomb));
    }

    @Test
    void megegyezoVane_1() {
        final int[] tomb = { 1 };
        assertFalse(Main.megegyezoVane(tomb));
    }

    @Test
    void megegyezoVane_11() {
        final int[] tomb = { 1, 1 };
        assertTrue(Main.megegyezoVane(tomb));
    }

    @Test
    void megegyezoVane_121() {
        final int[] tomb = { 1, 2, 1 };
        assertTrue(Main.megegyezoVane(tomb));
    }

    @Test
    void egymasutanMegegyezoVane_ures() {
        final int[] tomb = {};
        assertFalse(Main.egymasutanMegegyezoVane(tomb));
    }

    @Test
    void egymasutanMegegyezoVane_1() {
        final int[] tomb = { 1 };
        assertFalse(Main.egymasutanMegegyezoVane(tomb));
    }

    @Test
    void egymasutanMegegyezoVane_11() {
        final int[] tomb = { 1, 1 };
        assertTrue(Main.egymasutanMegegyezoVane(tomb));
    }

    @Test
    void egymasutanMegegyezoVane_121() {
        final int[] tomb = { 1, 2, 1 };
        assertFalse(Main.egymasutanMegegyezoVane(tomb));
    }

    @Test
    void egymasutanMegegyezoVane_1211() {
        final int[] tomb = { 1, 2, 1, 1 };
        assertTrue(Main.egymasutanMegegyezoVane(tomb));
    }

    @Test
    void negyzetszamVane_ures() {
        final int[] tomb = {};
        assertFalse(Main.negyzetszamVane(tomb));
    }

    @Test
    void negyzetszamVane_8() {
        final int[] tomb = { 8 };
        assertFalse(Main.negyzetszamVane(tomb));
    }

    @Test
    void negyzetszamVane_89() {
        final int[] tomb = { 8, 9 };
        assertTrue(Main.negyzetszamVane(tomb));
    }

    @Test
    void negyzetszamVane_98() {
        final int[] tomb = { 9, 8 };
        assertTrue(Main.negyzetszamVane(tomb));
    }

}