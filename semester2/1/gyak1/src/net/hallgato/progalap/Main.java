package net.hallgato.progalap;

import java.util.Arrays;
import java.util.Comparator;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {

    private static MinMaxAverage minMaxAverage(int[] array) {
        if (array == null) return new MinMaxAverage(0, 0, 0);
        final int len = array.length;
        if (len == 0) return new MinMaxAverage(0, 0, 0);
        int min, max, sum;
        min = max = sum = array[0];
        for (int i = 1; i < len; ++i) {
            final int val = array[i];
            sum += val;
            if (val < min) min = val;
            if (val > max) max = val;
        }
        return new MinMaxAverage(min, max, (double)sum / (double)len);
    }

    public static int linearSearch(int[] array, int value) {
        if (array == null) return -1;
        for (int i = 0, len = array.length; i < len; ++i) {
            if (value == array[i]) return i;
        }
        return -1;
    }

    public static int countWithAttribute(int[] array, Parity attrib) {
        if (array == null) return 0;
        final var fn = attrib.getFunc();
        int count = 0;
        for (int i = 0, len = array.length; i < len; ++i) {
            count += fn.applyAsInt(array[i]);
        }
        return count;
    }

    public static int findWithAttribute(int[] array, Parity attrib, Method method) {
        if (array == null) return -1;
        final int len = array.length;
        if (len == 0) return -1;
        final var attribFn = attrib.getFunc();
        final var methodFn = method.getFunc();
        int idx = 0;
        for (int i = 1; i < len; ++i) {
            if (attribFn.applyAsInt(array[i]) == 0) continue;
            if (methodFn.applyAsInt(array[i], array[idx]) == 0) continue;
            idx = i;
        }
        return idx;
    }

    public static double countWithAttributes(int[] array, Operation op) {
        return op.getFunc().apply(array);
    }

    public static String twenty(String word) {
        final StringBuilder buf = new StringBuilder();
        for (int i = 0;;) {
            buf.append(word);
            if (++i == 20) return buf.toString();
            buf.append(' ');
        }
    }

    public static char charAt(String str, int i) {
        return str == null || str.length() <= i ? '\0' : str.charAt(i);
    }

    public static String replaceFrom(String str, int from) {
        if (str == null) return null;
        if (str.length() <= from) return str;
        final char[] buf = str.toCharArray();
        for (int i = from, len = buf.length; i < len; ++i) {
            switch (buf[i]) {
                case 'e':
                    buf[i] = 'a';
                    break;
                case 'a':
                    buf[i] = 'e';
                    break;
            }
        }
        return new String(buf);
    }

    public static void displayVwords(String str) {
        if (str == null) return;
        final Matcher matcher = Pattern.compile("\\bV\\w+\\b").matcher(str);
        while (matcher.find()) {
            System.out.println(matcher.group());
        }
    }

    public static String replaceInEverySecond(String str) {
        if (str == null) return null;
        final Matcher m = Pattern.compile("\\b\\w+\\b").matcher(str);
        final StringBuffer buf = new StringBuffer();
        for (boolean sentinel = true; m.find(); sentinel = !sentinel) {
            if (sentinel) continue;
            m.appendReplacement(buf, m.group().replaceAll("e", "a"));
        }
        m.appendTail(buf);
        return buf.toString();
    }

    public static boolean isSubstringOf(String substr, String str) {
        return str.contains(substr);
    }

    public static boolean isPalindrome(String str) {
        if (str == null) return false;
        final char[] buf = str.toCharArray();
        for (int start = 0, end = buf.length - 1; start < end; ++start, --end) {
            if (buf[start] != buf[end]) return false;
        }
        return true;
    }

    public static Car newestCar(Car[] array) {
        if (array == null) return null;
        final int len = array.length;
        switch (len) {
            case 0: return null;
            case 1: return array[0];
        }
        Car car = array[0];
        for (int i = 0; i < len; ++i) {
            Car current = array[i];
            if (car.yearOfManufacture < current.yearOfManufacture) car = current;
        }
        return car;
    }

    public static int olderThanSevenYears(Car[] array) {
        if (array == null) return 0;
        int count = 0;
        for (Car car : array) {
            if (2019 - car.yearOfManufacture > 7) ++count;
        }
        return count;
    }

    public static Car[] fiveSeats(Car[] array) {
        if (array == null) return null;
        return Arrays.stream(array)
            .filter(x -> x.numberOfSeats == 5)
            .toArray(Car[]::new);
    }

    public static Car[] orderedByColor(Car[] array) {
        if (array == null) return null;
        return Arrays.stream(array)
            .sorted(Comparator.comparing(x -> x.color))
            .toArray(Car[]::new);
    }

    public static King longestReign(King[] array) {
        if (array == null) return null;
        final int len = array.length;
        switch (len) {
            case 0: return null;
            case 1: return array[0];
        }
        King k = array[0];
        for (int i = 1; i < len; ++i) {
            King c = array[i];
            if (c.reignEnd - c.reignStart > k.reignEnd - k.reignStart) k = c;
        }
        return k;
    }

    public static boolean arpadHaziE(King king) {
        return king != null && king.reignStart >= 1000 && king.reignEnd <= 1301;
    }

    public static void main(String[] args) {
        final Car[] cars = {
            new Car("Kia", 1950, "blue", 5, 5),
            new Car("Suzuki", 2010, "black", 4, 4),
            new Car("BMW", 2005, "red", 2, 2),
            new Car("Mini", 2001, "white", 5, 2),
            new Car("Mercedes", 1999, "green", 10, 6),
        };
        final King[] kings = {
            new King("1", 1250, 1350),
            new King("2", 1350, 1450),
            new King("3", 1000, 1010),
        };
    }
}
