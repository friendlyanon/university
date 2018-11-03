package net.hallgato.progalap;

import java.util.Iterator;
import java.util.NoSuchElementException;

class GetAllGeology implements Iterable<Geology>, Iterator<Geology> {
    private int cursor;
    private boolean foundLast = false;
    private final Geology last;
    private final Geology.TYPE type;
    private final Geology[] array;
    private final int end;

    GetAllGeology(final Geology[] array, final Geology.TYPE type, final int end) {
        this.array = array;
        this.end = end;
        this.type = type;
        findLast: {
            for (int i = end; i > 0; ) {
                final Geology val = array[--i];
                if (val.type == type) {
                    last = val;
                    break findLast;
                }
            }
            last = null;
        }
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

    public void remove() {
        throw new UnsupportedOperationException();
    }

    public Iterator<Geology> iterator() {
        cursor = 0;
        return this;
    }
}
