package net.hallgato.progalap;

import java.util.Iterator;
import java.util.NoSuchElementException;

class GetAllGeology implements Iterable<Geology>, Iterator<Geology> {
    private int cursor;
    private boolean foundLast = false;
    private Geology last;
    private final Geology.TYPE type;
    private final Geology[] array;
    private final int end;

    public GetAllGeology(final Geology[] array, final Geology.TYPE type, final int end) {
        this.cursor = 0;
        this.array = array;
        this.end = end;
        this.type = type;
        this.skipEnd(0);
    }

    public GetAllGeology(final Geology[] array, final Geology.TYPE type) {
        this.cursor = 0;
        this.array = array;
        this.end = array.length;
        this.type = type;
        this.skipEnd(0);
    }

    public boolean hasNext() {
        return last != null && !foundLast && cursor < end;
    }

    public Geology next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        final Geology current = array[cursor++];
        foundLast = current == last;
        return current.type == type ? current : next();
    }

    public GetAllGeology skip() {
        return this.skipBegin();
    }

    public GetAllGeology skip(final int amount) {
        return this.skipBegin(amount);
    }

    public GetAllGeology skipBegin() {
        if (this.hasNext()) this.next();
        return this;
    }

    public GetAllGeology skipBegin(int amount) {
        while (this.hasNext() && --amount >= 0) this.next();
        return this;
    }

    public GetAllGeology skipEnd() {
        return this.skipEnd(1);
    }

    public GetAllGeology skipEnd(int amount) {
        findLast: {
            for (int i = end; i > 0; ) {
                final Geology val = array[--i];
                if (val.type == type && amount <= 0) {
                    last = val;
                    break findLast;
                }
                --amount;
            }
            last = null;
        }
        return this;
    }

    public void remove() {
        throw new UnsupportedOperationException();
    }

    public Iterator<Geology> iterator() {
        return this;
    }
}
