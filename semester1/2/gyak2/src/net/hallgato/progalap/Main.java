package net.hallgato.progalap;

public class Main {

    public static void main(String[] args) /* throws InterruptedException */ {
        try (Lifetime l = new Lifetime()) {
            int elso = 4;
            int masodik = 5;
            int harmadik = 3;

            boolean pitagorasz1 = l.pitagorasz(elso, masodik, harmadik);
            boolean pitagorasz2 = l.pitagorasz(elso, harmadik, masodik);;
            boolean pitagorasz3 = l.pitagorasz(masodik, harmadik, elso);;

            l.log("Első, második = harmadik esetén ");
            if (!pitagorasz1) {
                l.log("NEM ");
            }
            l.log("pitagóraszi számhármas.\n");

            l.log("Első, harmadik = második esetén ");
            if (!pitagorasz2) {
                l.log("NEM ");
            }
            l.log("pitagóraszi számhármas.\n");

            l.log("Második, harmadik = első esetén ");
            if (!pitagorasz3) {
                l.log("NEM ");
            }
            l.log("pitagóraszi számhármas.\n");

            l.log("\nLegnagyobb ellenőrzésével: \n");
            if (elso < masodik) {
                if (masodik < harmadik) {
                    l.log("Első, második = harmadik esetén ");
                    if (!pitagorasz1) {
                        l.log("NEM ");
                    }
                    l.log("pitagóraszi számhármas.\n");
                }
                else {
                    l.log("Első, harmadik = második esetén ");
                    if (!pitagorasz2) {
                        l.log("NEM ");
                    }
                    l.log("pitagóraszi számhármas.\n");
                }
            }
            else {
                if (elso > harmadik) {
                    l.log("Második, harmadik = első esetén ");
                    if (!pitagorasz3) {
                        l.log("NEM ");
                    }
                    l.log("pitagóraszi számhármas.\n");
                }
                else {
                    l.log("Első, második = harmadik esetén ");
                    if (!pitagorasz1) {
                        l.log("NEM ");
                    }
                    l.log("pitagóraszi számhármas.\n");
                }
            }

            l.log("\n");
            elso = l.getInt("Add meg az első számot: ");
            masodik = l.getInt("Add meg a második számot: ");
            harmadik = l.getInt("Add meg a harmadik számot: ");
            pitagorasz1 = l.pitagorasz(elso, masodik, harmadik);
            pitagorasz2 = l.pitagorasz(elso, harmadik, masodik);
            pitagorasz3 = l.pitagorasz(masodik, harmadik, elso);
            if (elso < masodik) {
                if (masodik < harmadik) {
                    l.log("Első, második = harmadik esetén ");
                    if (!pitagorasz1) {
                        l.log("NEM ");
                    }
                    l.log("pitagóraszi számhármas.\n");
                }
                else {
                    l.log("Első, harmadik = második esetén ");
                    if (!pitagorasz2) {
                        l.log("NEM ");
                    }
                    l.log("pitagóraszi számhármas.\n");
                }
            }
            else {
                if (elso > harmadik) {
                    l.log("Második, harmadik = első esetén ");
                    if (!pitagorasz3) {
                        l.log("NEM ");
                    }
                    l.log("pitagóraszi számhármas.\n");
                }
                else {
                    l.log("Első, második = harmadik esetén ");
                    if (!pitagorasz1) {
                        l.log("NEM ");
                    }
                    l.log("pitagóraszi számhármas.\n");
                }
            }

            do {
                final int aa = 5;
                final int bb = 8;
                final int cc = 2;

                l.log("\naa (" + aa + "), bb (" + bb + "), cc (" + cc + ") ");
                if (aa == 0) {
                    l.log("NEM ");
                }
                l.log("másodfokú egyenlet tagjai.\n\n");
            } while(false);

            for (int i = 10; i >= 0; ) {
                l.log(i-- + ", ");
                // Thread.sleep(1000);
            }
            l.log("KILÖVÉS\n");

            for (double i = 1; true; i += 0.4) {
                l.log(i);
                if (i >= 5) { l.log("\n"); break; }
                else l.log(", ");
            }

            for (double i = 1; true; i += 0.3) {
                l.log(i);
                if (i >= 4) { l.log("\n\n"); break; }
                else l.log(", ");
            }

            final int[] t = new int[5];
            for (int i = 0, len = t.length; i < len; ++i) {
                t[i] = (int)(Math.random() * 90) + 1;
            }
            for (int szam : t) {
                l.log(szam + "\n");
            }
            l.log("\n");

            final long[] fib = new long[50];
            fib[0] = fib[1] = 1;
            for (int i = 2, len = fib.length; i < len; ++i) {
                fib[i] = fib[i - 2] + fib[i - 1];
            }

            for (int i = 0, len = fib.length; i < len; ) {
                l.log(fib[i++]);
                l.log(i % 10 == 0 ? "\n" : ", ");
            }

            l.log("\n");
            for (long szam : fib) {
                if ((szam % 13) > 0) continue;
                l.log(szam + " osztható 13-mal.");
                break;
            }

            l.log("\n\n");
            do {
                final int aa = 8;
                final int bb = 10;
                final int cc = 3;
                l.log("bb * bb - 4 * aa * cc >= 0\n");
                final long gyok = bb * bb - 4 * aa * cc;
                if (gyok < 0) {
                    l.log("Nincs valós gyök");
                    break;
                }
                l.log("Van valós gyök\n");

                final double x1 = (-bb + Math.sqrt(gyok)) / (2 * aa);
                l.log("x1 = " + x1 + "\n");
                final double x2 = (-bb - Math.sqrt(gyok)) / (2 * aa);
                l.log("x2 = " + x2);
            } while(false);

            l.log("\n\nKoordináta\n");
            final int x = l.getInt("Add meg az x értéket:");
            final int y = l.getInt("Add meg az y értéket:");
            final int negyed = ((x >= 0 ? 1 : 0) | ((y >= 0 ? 0 : 1) << 1)) + 1;
            l.log(negyed + ". negyed\n");
            l.log(((x * x + y * y > 400) ? "Nincs benne" : "Benne van") + " a körben.\n");

            do {
                final int fakt = l.getInt("\nFaktoriális\nAddj meg egy számot:");
                if (fakt > 18) l.log("Az érték nem biztos, hogy pontos lesz!\n");
                int temp = fakt;
                double total = 1;
                do {
                    total *= temp;
                } while(--temp > 0);
                l.log("Eredmény: " + total);
            } while(false);

            do {
                final long limit = l.getInt("\n\nPáratlan\nAddj meg egy számot:");
                long osszeg = 0;
                for (long i = 1; i <= limit; i += 2) {
                    osszeg += i;
                    l.log("szám: " + i + ", összeg: " + osszeg + "\n");
                }
            } while(false);
            // Fakt max double: 18
        } // try
    } // main
} // class
