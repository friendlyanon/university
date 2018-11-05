package net.hallgato.progalap;

import java.util.function.*;

class Mandatory extends Main {
    private static void count(final Geology.TYPE type, final String name) {
        final long count = asStream(getAll(type)).count();
        System.out.println(count + " darab " + name +" van");
    }

    static ToDoubleFunction<Geology> toDbl = x -> x.extreme;
    static Function<? super Geology, Double> toBiDbl = x -> x.extreme;
    static ToIntFunction<Geology> toInt = x -> x.width;
    static Function<? super Geology, Integer> toBiInt = x -> x.width;
    static DoubleBinaryOperator minDbl = (a, b) -> a < b ? a : b;
    static IntBinaryOperator minInt = (a, b) -> a < b ? a : b;
    static DoubleBinaryOperator maxDbl = (a, b) -> a > b ? a : b;
    static IntBinaryOperator maxInt = (a, b) -> a > b ? a : b;

    // Hány sziget van?
    private static void first() {
        count(Geology.TYPE.ISLAND, "sziget");
    }

    // Hány árok van?
    private static void second() {
        count(Geology.TYPE.WATER, "árok");
    }

    // Legmélyebb árok mélysége?
    private static void third() {
        final double extreme = asStream(getAll(Geology.TYPE.WATER))
            .mapToDouble(toDbl)
            .reduce(minDbl)
            .orElse(0.0);
        System.out.println("A legmélyebb árok " + extreme + " méter mély");
    }

    // Legmagasabb sziget magassága?
    private static void fourth() {
        final double extreme = asStream(getAll(Geology.TYPE.ISLAND))
            .mapToDouble(toDbl)
            .reduce(maxDbl)
            .orElse(0.0);
        System.out.println("A legmagasabb sziget " + extreme + " méter magas");
    }

    // Milyen hosszú a leghosszabb sziget?
    private static void fifth() {
        final int width = asStream(getAll(Geology.TYPE.ISLAND))
            .mapToInt(toInt)
            .reduce((a, b) -> a > b ? a : b)
            .orElse(0);
        System.out.println("A leghosszabb sziget " + width + " km hosszú");
    }

    // Milyen rövid a legrövidebb sziget?
    private static void sixth() {
        final int width = asStream(getAll(Geology.TYPE.ISLAND))
            .mapToInt(toInt)
            .reduce(minInt)
            .orElse(0);
        System.out.println("A legrövidebb sziget " + width + " km hosszú");
    }

    // Milyen hosszú a leghosszabb árok?
    private static void seventh() {
        final int width = asStream(getAll(Geology.TYPE.WATER))
            .mapToInt(toInt)
            .reduce(maxInt)
            .orElse(0);
        System.out.println("A leghosszabb árok " + width + " km hosszú");
    }

    // Milyen rövid a legrövidebb árok?
    private static void eighth() {
        final int width = asStream(getAll(Geology.TYPE.WATER))
            .mapToInt(toInt)
            .reduce(minInt)
            .orElse(0);
        System.out.println("A legrövidebb árok " + width + " km hosszú");
    }

    // Hány km hosszú a legalacsonyabb sziget?
    private static void ninth() {
        final int width = asStream(getAll(Geology.TYPE.ISLAND))
            .reduce((a, b) -> a.extreme < b.extreme ? a : b)
            .map(toBiInt)
            .orElse(0);
        System.out.println("A legalacsonyabb sziget " + width + " km hosszú");
    }

    // Milyen magas a leghosszabb sziget?
    private static void tenth() {
        final double extreme = asStream(getAll(Geology.TYPE.ISLAND))
            .reduce((a, b) -> a.width > b.width ? a : b)
            .map(toBiDbl)
            .orElse(0.0);
        System.out.println("A leghosszabb sziget " + extreme + " méter magas");
    }

    // Milyen mély a legrovidebb árok?
    private static void eleventh() {
        final double extreme = asStream(getAll(Geology.TYPE.WATER))
            .reduce((a, b) -> a.width < b.width ? a : b)
            .map(toBiDbl)
            .orElse(0.0);
        System.out.println("A legrovidebb árok " + extreme + " méter mély");
    }

    static void all() {
        System.out.println("\nKötelező kérdések:");
        first();
        second();
        third();
        fourth();
        fifth();
        sixth();
        seventh();
        eighth();
        ninth();
        tenth();
        eleventh();
    }
}
