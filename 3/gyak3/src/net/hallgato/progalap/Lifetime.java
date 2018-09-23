package net.hallgato.progalap;

import java.util.Scanner;

public final class Lifetime implements java.lang.AutoCloseable {
    private Scanner scanner;

    Lifetime() {
        scanner = new Scanner(System.in);
    }

    void log() {
        System.out.print("");
    }

    <T> void log(T input) {
        System.out.print(input);
    }

    String kettes(int szam) {
        StringBuilder buf = new StringBuilder(30);
        while (szam != 0) {
            buf.append(szam & 1);
            szam >>>= 1;
        }
        return buf.reverse().toString();
    }

    double getDouble(String message) {
        log(message + "\n");
        try { return scanner.nextDouble(); }
        catch (Exception e) { return 0; }
    }

    int getInt() {
        try { return scanner.nextInt(); }
        catch (Exception e) { return 0; }
    }

    int getInt(String message) {
        log(message + "\n");
        try { return scanner.nextInt(); }
        catch (Exception e) { return 0; }
    }

    int minMax(final int min, final int max) {
        return (int)(Math.random() * (max - min + 1)) + min;
    }

    public void close() {
        System.exit(0);
    }
}
