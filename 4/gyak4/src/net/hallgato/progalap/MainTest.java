package net.hallgato.progalap;

import groovy.transform.ASTTest;
import org.junit.Test;

import static org.junit.Assert.*;

public class MainTest {

    @Test
    public void max() {
        final Integer[] tomb1 = { 1, 2, 3, 4, 5 };
        assertEquals(5, (long)Main.max(tomb1).first);
        final Integer[] tomb2 = { 14, 0, 5, 6, 9 };
        assertEquals(14, (long)Main.max(tomb2).first);
    }

    @Test
    public void masodikFeladat() {
        final int[] tomb1 = { 2, 2, 2 };
        final int[] tomb2 = { 3, 3, 3 };
        final int[] tomb3 = { 3, 2, 2 };
        final int[] tomb4 = { 2, 3, 2 };
        final int[] tomb5 = { 2, 2, 3 };
        final int[] tomb6 = { 2, 3, 3 };
        final int[] tomb7 = { 3, 3, 2 };
        final int[] tomb8 = { 2, 3, 2, 2 };
        final int[] tomb9 = { 2, 2, 3, 2 };
        final int[] tomb10 = { 2, 2, 3, 2, 2 };
        assertEquals("Mind páros", Main.parosok(tomb1));
        assertEquals("Nem mind páros", Main.parosok(tomb2));
        assertEquals("Nem mind páros", Main.parosok(tomb3));
        assertEquals("Középső páratlan", Main.parosok(tomb4));
        assertEquals("Nem mind páros", Main.parosok(tomb5));
        assertEquals("Középső páratlan", Main.parosok(tomb6));
        assertEquals("Nem mind páros", Main.parosok(tomb7));
        assertEquals("Nem mind páros", Main.parosok(tomb8));
        assertEquals("Nem mind páros", Main.parosok(tomb9));
        assertEquals("Középső páratlan", Main.parosok(tomb10));
    }

    @Test
    public void harmadikFeladat() {
        // TODO: implement
    }

}