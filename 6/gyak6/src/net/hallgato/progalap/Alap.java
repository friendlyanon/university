/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package net.hallgato.progalap;

/**
 *
 * @author Sorcer
 */
class Alap {

    static void elso(final int[] tomb, final int db) {
        Main.kiir("Maximális elem: ", Main.maxHol(tomb, db).first);
    }

    static void masodik(final int[] tomb, final int db) {
        Main.kiir("Minimális elem: ", Main.minHol(tomb, db).first);
    }

    static void harmadik(final int[] tomb, final int db) {
        Main.kiir("A tömb átlaga: ", Main.atlag(tomb, db));
    }

    static void negyedik(final int[] tomb, final int to) {
        int db = 0, i = 0;
        for (final int elem : tomb) {
            if (i++ >= to) break;
            if (elem % 2 != 0) ++db;
        }
        Main.kiir("Páros számok darabszáma: ", db);
    }

    static void otodik(final int[] tomb, final int db) {
        int i = 0;
        for (final int elem : tomb) {
            if (i++ >= db) break;
            if (elem % 5 != 0) {
                Main.kiir("Van öttel osztható szám: true");
                return;
            }
        }
        Main.kiir("Van öttel osztható szám: false");
    }

    static void hatodik(final int[] tomb, final int db) {
        int i = 0;
        for (final int elem : tomb) {
            if (i++ >= db) break;
            if (elem % 2 != 0) continue;
            Main.kiir("Az összes szám páros: false");
            return;
        }
        Main.kiir("Az összes szám páros: true");
    }

    static void main() {
        final int[][][] tombok = {
                {Main.tombGen(20, 0, 100), {20}},
                {new int[30], {30}},
                {Main.tombGen(40, 0, 100, 20), {20}}
        };

        for (final int[][] tomb : tombok) {
            Main.kiir();
            elso(tomb[0], tomb[1][0]);
            masodik(tomb[0], tomb[1][0]);
            harmadik(tomb[0], tomb[1][0]);
            negyedik(tomb[0], tomb[1][0]);
            otodik(tomb[0], tomb[1][0]);
            hatodik(tomb[0], tomb[1][0]);
        }
    }

}
