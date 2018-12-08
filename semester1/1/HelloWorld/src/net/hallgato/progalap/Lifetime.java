package net.hallgato.progalap;

import java.util.Scanner;

public final class Lifetime implements java.lang.AutoCloseable {
    private Scanner scanner;

    Lifetime() {
        scanner = new Scanner(System.in);
    }

    void log() {
        System.out.println();
    }

    <T> void log(T input) {
        System.out.println(input);
    }

    double getDouble(String message) {
        log(message);
        return Double.parseDouble(scanner.nextLine());
    }

    public void close() {
        System.exit(0);
    }
}
