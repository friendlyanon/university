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

    boolean pitagorasz(int x, int y, int z) {
        return x * x + y * y == z * z;
    }

    double getDouble(String message) {
        log(message + "\n");
        return Double.parseDouble(scanner.nextLine());
    }

    int getInt(String message) {
        log(message + "\n");
        return Integer.parseInt(scanner.nextLine());
    }

    public void close() {
        System.exit(0);
    }
}
