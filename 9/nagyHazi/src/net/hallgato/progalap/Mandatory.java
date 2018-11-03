package net.hallgato.progalap;

class Mandatory {
    private static Geology[] geologies;
    private static int validLength;

    private static GetAllGeology getAll(final Geology.TYPE type) {
        return new GetAllGeology(geologies, type, validLength);
    }

    private static void count(final Geology.TYPE type, final String name) {
        final int firstIsland = Main.findFirst(type, geologies, validLength);
        final int lastIsland = Main.findLast(type, geologies, validLength);
        final int count = (lastIsland - firstIsland) / 2 + 1;
        System.out.println(count + " darab " + name +" van");
    }

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
        double extreme = 0;
        for (final Geology water : getAll(Geology.TYPE.WATER)) {
            if (water.extreme < extreme) extreme = water.extreme;
        }
        System.out.println("A legmélyebb árok " + extreme + " méter mély");
    }

    // Legmagasabb sziget magassága?
    private static void fourth() {
        final int firstIsland = Main.findFirst(Geology.TYPE.ISLAND, geologies, validLength);
        final int lastIsland = Main.findLast(Geology.TYPE.ISLAND, geologies, validLength);
        double extreme = 0;
        for (int i = firstIsland; i <= lastIsland; i += 2) {
            final double current = geologies[i].extreme;
            if (current > extreme) extreme = current;
        }
        System.out.println("A legmagasabb sziget " + extreme + " méter magas");
    }

    // Milyen hosszú a leghosszabb sziget?
    private static void fifth() {
        final int firstIsland = Main.findFirst(Geology.TYPE.ISLAND, geologies, validLength);
        final int lastIsland = Main.findLast(Geology.TYPE.ISLAND, geologies, validLength);
        int width = 0;
        for (int i = firstIsland; i <= lastIsland; i += 2) {
            final int current = geologies[i].width;
            if (current > width) width = current;
        }
        System.out.println("A leghosszabb sziget " + width + " km hosszú");
    }

    // Milyen rövid a legrövidebb sziget?
    private static void sixth() {
        final int firstIsland = Main.findFirst(Geology.TYPE.ISLAND, geologies, validLength);
        final int lastIsland = Main.findLast(Geology.TYPE.ISLAND, geologies, validLength);
        int width = Integer.MAX_VALUE;
        for (int i = firstIsland; i <= lastIsland; i += 2) {
            final int current = geologies[i].width;
            if (current < width) width = current;
        }
        System.out.println("A legrövidebb sziget " + width + " km rövid");
    }

    // Milyen hosszú a leghosszabb árok?
    private static void seventh() {
        final int firstWater = Main.findFirst(Geology.TYPE.WATER, geologies, validLength);
        int width = 0;
        for (int i = firstWater; i < validLength; i += 2) {
            final int current = geologies[i].width;
            if (current > width) width = current;
        }
        System.out.println("A leghosszabb árok " + width + " km hosszú");
    }

    // Milyen rövid a legrövidebb árok?
    private static void eighth() {
        final int firstWater = Main.findFirst(Geology.TYPE.WATER, geologies, validLength);
        int width = Integer.MAX_VALUE;
        for (int i = firstWater; i < validLength; i += 2) {
            final int current = geologies[i].width;
            if (current < width) width = current;
        }
        System.out.println("A legrövidebb árok " + width + " km rövid");
    }

    // Hány km hosszú a legalacsonyabb sziget?
    private static void ninth() {
        final int firstIsland = Main.findFirst(Geology.TYPE.ISLAND, geologies, validLength);
        final int lastIsland = Main.findLast(Geology.TYPE.ISLAND, geologies, validLength);
        double extreme = Integer.MAX_VALUE;
        int width = 0;
        for (int i = firstIsland; i <= lastIsland; i += 2) {
            final Geology current = geologies[i];
            if (current.extreme < extreme) {
                extreme = current.extreme;
                width = current.width;
            }
        }
        System.out.println("A legalacsonyabb sziget " + width + " km hosszú");
    }

    // Milyen magas a leghosszabb sziget?
    private static void tenth() {
        final int firstIsland = Main.findFirst(Geology.TYPE.ISLAND, geologies, validLength);
        final int lastIsland = Main.findLast(Geology.TYPE.ISLAND, geologies, validLength);
        double extreme = 0;
        int width = 0;
        for (int i = firstIsland; i <= lastIsland; i += 2) {
            final Geology current = geologies[i];
            if (current.width > width) {
                extreme = current.extreme;
                width = current.width;
            }
        }
        System.out.println("A leghosszabb sziget " + extreme + " méter magas");
    }

    // Milyen mély a legrovidebb árok?
    private static void eleventh() {
        final int firstWater = Main.findFirst(Geology.TYPE.WATER, geologies, validLength);
        double extreme = 0;
        int width = Integer.MAX_VALUE;
        for (int i = firstWater; i < validLength; i += 2) {
            final Geology current = geologies[i];
            if (current.width < width) {
                extreme = current.extreme;
                width = current.width;
            }
        }
        System.out.println("A legrovidebb árok " + extreme + " méter mély");
    }

    static void all() {
        geologies = Main.geologies;
        validLength = Main.validLength;
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
