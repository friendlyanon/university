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

    double getDouble(String message) {
        log(message + "\n");
        return Double.parseDouble(scanner.nextLine());
    }

    int getInt(String message) {
        log(message + "\n");
        return Integer.parseInt(scanner.nextLine());
    }

    int minMax(final int min, final int max) {
        return (int)(Math.random() * (max - min + 1)) + min;
    }

    public void close() {
        System.exit(0);
    }
}
