package net.hallgato.progalap;

public class Main {
    public static void main(String[] args) {
        try (Lifetime l = new Lifetime()) {
            if (l.getInt("Ugrás a leckéhez? (1 = igen, más = nem)") != 1) {
                final int k[] = new int[20];
                for (int i = 0, len = k.length; i < len; ++i) {
                    k[i] = (int) (Math.random() * 6 + 1);
                }
                l.log("Második: " + k[1]);
                l.log("\nUtolsó előtti: " + k[k.length - 2] + "\n\n");

                for (int i = 0, len = k.length; i < len; ++i) {
                    l.log(i + ". rekesz: " + k[i] + "\n");
                }
                l.log("\n");

                for (int i = 0, len = k.length; i < len; ++i) {
                    final int elem = k[i];
                    l.log(elem + " négyzete: " + (elem * elem) + "\n");
                }
                l.log("\n");

                final int t[] = new int[6];
                for (final int i : k) {
                    if (i < 1 || 6 < i) {
                        l.log("Nem jó: " + i);
                        continue;
                    }
                    ++t[i - 1];
                }
                for (int i = 0, len = t.length; i < len; ) {
                    final int j = t[i++];
                    l.log(i + " dobások száma: " + j + "\n");
                }
                l.log("\n");

                final int ttLen = 10;
                final int[] tt = new int[ttLen];
                for (int i = 0; i < ttLen; ++i) {
                    tt[i] = l.minMax(13, 26);
                }
                int szam;
                do {
                    szam = l.getInt("Add meg a számod (13 - 26 között):");
                } while (szam < 13 || 26 < szam);
                int szerepel = 0;
                int hol = -1;
                for (int i = 0; i < ttLen; ++i) {
                    if (tt[i] == szam) {
                        ++szerepel;
                        if (hol < 0) hol = i;
                    }
                }
                if (szerepel > 0) {
                    l.log(szam + " szerepel ennyiszer: " + szerepel);
                } else {
                    l.log(szam + " nem szerepel egyszer sem");
                }

                double atlag = 0;
                for (final int i : tt) {
                    atlag += i;
                }
                atlag /= tt.length;
                l.log("\n\nÁtlag: " + atlag + "\n");

                if (szerepel > 0) {
                    if (hol != 0) l.log("Számod előtti szám:" + tt[hol - 1] + "\n");
                    l.log("Számod:" + tt[hol] + "\n");
                    if (hol != ttLen - 1) l.log("Számod utáni szám:" + tt[hol + 1] + "\n");
                }

                for (final int i : tt) {
                    if ((i % 13) == 0) {
                        l.log(i + " osztható 13-mal\n");
                        break;
                    }
                }

                final int szamod = l.getInt("\nAdd meg a konvertálni kívánt számot:");
                /* final String kettesRendszer = Integer.toString(szamod, 2).substring(0, 30); */
                final String kettesRendszer = l.kettes(szamod);
                l.log(kettesRendszer + "\n\n");

                double[][] gyokok = {new double[100], new double[100]};
                for (int i = 0; i < 100; ++i) {
                    final int j = i + 1;
                    l.log(j + " gyoke: " + (gyokok[0][i] = Math.sqrt(j)) + "; köbgyöke: " + (gyokok[1][i] = Math.cbrt(j)) + "\n");
                }
                l.log("\n");
            }

            final int totoLen = 14;
            final int[] toto = new int[totoLen];
            do {
                for (int i = 0; i < totoLen; ++i) {
                    toto[i] = l.minMax(1, 25);
                }
                int[] tipp = new int[totoLen];
                int talalat = 0;
                l.log("Addj meg " + totoLen + " tippet 1 és 25 között:\n");
                for (int i = 0; i < totoLen; ++i) {
                    if ((tipp[i] = l.getInt()) == toto[i]) ++talalat;
                }
                if (talalat >= 10) {
                    l.log("ÖN NYERT, találatszáma " + talalat);
                } else {
                    l.log("Nem nyert");
                }
            } while (l.getInt("\nÚjra? (1 = igen, 0 = nem)") == 1);

            // Házi feladat

            final int tagLen = 11;
            final int[] szerencse = new int[tagLen];
            int tizenharom = 0, tiz = 0;
            boolean tizenhat = false;
            for (int i = 0; i < tagLen; ++i) {
                final int randSzam = l.minMax(10, 20);
                szerencse[i] = randSzam;
                switch (randSzam) {
                    case 10: tiz++; break;
                    case 13: tizenharom++; break;
                    case 16: tizenhat = true; break;
                }
            }
            l.log("\n13: " + tizenharom + "\n10: " + tiz + "\nVolt-e 16? " + (tizenhat ? "igen" : "nem") + "\n");

            l.log("Hány pontot dobtak a csoport tagjai?\n");
            final int[] g = new int[tagLen];
            double tagAtlag = 0;
            for (int i = 0; i < tagLen; ++i) {
                tagAtlag += (g[i] = l.getInt());
            }
            tagAtlag /= tagLen;
            for (final int i : g) {
                if (Math.abs(tagAtlag - i) >= 1) continue;
                l.log("Van akinek a pontja az [atlag-1;atlag+1] halmazban van\n");
                break;
            }
            l.log("\n");

            double betet = l.getDouble("Mennyi a betéted?");
            for (int i = 0, evek = l.getInt("Hány évig kamatozott a betét?"); evek >= 0; ++i, --evek) {
                l.log(i + ". év: " + betet + "\n");
                betet *= 1.02;
            }
            l.log("\n");

            final int[] szazalekok = new int[1000];
            for (int i = 0; i < 1000; ++i) {
                int osszeg = 0;
                for (int j = 0; j < 5; ++j) {
                    osszeg += l.minMax(0, 20);
                }
                szazalekok[i] = osszeg;
            }
            final int[][] hatarok = { {91, 4}, {75, 3}, {60, 2}, {50, 1}, {0, 0} };
            final int[] jegyek = new int[5];
            for (final int i : szazalekok) {
                for (final int[] j : hatarok) {
                    if (i >= j[0]) {
                        ++jegyek[j[1]];
                        break;
                    }
                }
            }
            int legtobbIdx = 0;
            for (int i = 1; i < 5; ++i) {
                if (jegyek[i] >= jegyek[legtobbIdx]) legtobbIdx = i;
            }
            final double legtobb = jegyek[legtobbIdx];
            l.log("Jegyek:\n");
            for (int i = 4; i >= 0; --i) {
                StringBuilder sor = new StringBuilder(79);
                sor.append(" ");
                sor.append(i + 1);
                sor.append(" | ");
                for (double j = 74 * (jegyek[i] / legtobb); j > 0; --j) {
                    sor.append("*");
                }
                l.log(sor.toString() + "\n");
            }
        }
    }
}
