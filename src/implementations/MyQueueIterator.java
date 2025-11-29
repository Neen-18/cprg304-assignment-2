package implementations;

import utilities.Iterator;
import java.util.NoSuchElementException;

public class MyQueueIterator<E> implements Iterator<E> {

    private final Object[] copy;
    private int index;

    public MyQueueIterator(Object[] copy) {
        this.copy = copy;
        this.index = 0;
    }

    @Override
    public boolean hasNext() {
        return index < copy.length;
    }

    @SuppressWarnings("unchecked")
    @Override
    public E next() {
        if (!hasNext())
            throw new NoSuchElementException("No more queue elements.");
        return (E) copy[index++];
    }
}
