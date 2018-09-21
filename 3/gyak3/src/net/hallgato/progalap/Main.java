package net.hallgato.progalap;

public class Main {
    public static void main(String[] args) {
        try (Lifetime l = new Lifetime()) {
            final int k[] = new int[20];
            for (int i = 0, len = k.length; i < len; ++i) {
                k[i] = (int)(Math.random() * 6 + 1);
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
            l.log("\n\n");

            final int ttLen = 10;
            final int[] tt = new int[ttLen];
            for (int i = 0; i < ttLen; ++i) {
                tt[i] = l.minMax(13, 26);
            }
            int szam;
            do {
                szam = l.getInt("Add meg a számod (13 - 26 között):");
            } while(szam < 13 || 26 < szam);
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
            }
            else {
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

            final int mLen = 32;
            final int[] m = new int[mLen];
            int szamod = l.getInt("\nAdd meg a konvertálni kívánt számot:");
            if (szamod < 0) {
                szamod = ~szamod;
            }
            int start = mLen;
            while (szamod != 0) {
                --start;
                if ((szamod & 1) == 1) {
                    m[start] = 1;
                }
                szamod >>= 1;
            }
            boolean met1 = false;
            for (final int i : m) {
                if (!met1) {
                    if (i == 0) continue;
                    met1 = true;
                }
                l.log(i);
            }
            l.log("\n");


        }
    }
}
