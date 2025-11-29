package implementations;

import utilities.Iterator;
import java.util.NoSuchElementException;

public class MyStackIterator<E> implements Iterator<E> {

    private final Object[] copy;
    private int index;

    public MyStackIterator(Object[] snapshot) {
        this.copy = snapshot;
        this.index = snapshot.length - 1;
    }

    @Override
    public boolean hasNext() {
        return index >= 0;
    }

    @SuppressWarnings("unchecked")
    @Override
    public E next() {
        if (!hasNext()) {
            throw new NoSuchElementException("No more stack elements.");
        }
        return (E) copy[index--];
    }
}
