package net.hallgato.progalap;

public enum Operation {
    Sum(new Op() {
        double apply(int[] array) {
            if (array == null) return 0;
            int sum = 0;
            for (int val : array) sum += val;
            return sum;
        }
    }),
    Multiply(new Op() {
        double apply(int[] array) {
            if (array == null) return 0;
            final int len = array.length;
            switch (len) {
                case 0: return 0;
                case 1: return array[0];
            }
            int sum = array[0];
            for (int i = 0; i < len; ++i) {
                sum *= array[i];
            }
            return sum;
        }
    }),
    Average(new Op() {
        double apply(int[] array) {
            if (array == null) return 0;
            int sum = 0;
            for (int val : array) sum += val;
            return (double)sum / (double)array.length;
        }
    }),
    PositiveCount(new Op() {
        double apply(int[] array) {
            if (array == null) return 0;
            int count = 0;
            for (int val : array) if (val > 0) ++count;
            return count;
        }
    }),
    AboveAverage(new Op() {
        double apply(int[] array) {
            if (array == null) return 0;
            final double avg = Operation.Average.getFunc().apply(array);
            int count = 0;
            for (int val : array) if (val > avg) ++count;
            return count;
        }
    });

    abstract static class Op {
        abstract double apply(int[] array);
    }

    private final Op func;

    Operation(Op fn) {
        func = fn;
    }

    public Op getFunc() {
        return func;
    }
}
