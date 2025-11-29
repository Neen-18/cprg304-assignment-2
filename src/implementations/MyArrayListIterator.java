package implementations;
import utilities.ListADT;
import utilities.Iterator;

import java.util.NoSuchElementException;

public class MyArrayListIterator<E> implements Iterator<E> {

    final private Object[] copy;
    private int idx;

    public MyArrayListIterator(ListADT<E> list) {
        this.copy = list.toArray();
        this.idx = 0;
    }

    @Override
    public boolean hasNext() {
        return idx < copy.length;
    }

    @SuppressWarnings("unchecked")
    @Override
    public E next() {
        if (!hasNext()) throw new NoSuchElementException();
        return (E) copy[idx++];
    }
}
