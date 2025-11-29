package implementations;

import utilities.QueueADT;
import utilities.Iterator;
import exceptions.EmptyQueueException;

public class MyQueue<E> implements QueueADT<E> {

	private MyDLL<E> list;

    public MyQueue() {
        this.list = new MyDLL<E>();
    }

    @Override
    public void enqueue(E element) throws NullPointerException {
        if (element == null)
            throw new NullPointerException("Null elements not allowed.");

        list.add(element); // append to rear
    }

    @Override
    public E dequeue() throws EmptyQueueException {
        if (list.isEmpty())
            throw new EmptyQueueException("Queue is empty.");

        return list.remove(0); // remove from front
    }

    @Override
    public E peek() throws EmptyQueueException {
        if (list.isEmpty())
            throw new EmptyQueueException("Queue is empty.");

        return list.get(0); // front of queue
    }

    @Override
    public void dequeueAll() {
        list.clear();
    }

    @Override
    public boolean isEmpty() {
        return list.isEmpty();
    }


    @Override
    public boolean contains(E toFind) throws NullPointerException {
        return list.contains(toFind);
    }

    @Override
    public int search(E toFind) {
        if (toFind == null)
            throw new NullPointerException("Cannot search for null.");

        for (int i = 0; i < list.size(); i++) {
            if (toFind.equals(list.get(i))) {
                return i + 1;
            }
        }
        return -1; // not found
    }

    @Override
    public boolean equals(QueueADT<E> other) {
        if (other == null)
            return false;

        if (this.size() != other.size())
            return false;

        Iterator<E> it1 = this.iterator();
        Iterator<E> it2 = other.iterator();

        while (it1.hasNext() && it2.hasNext()) {
            E a = it1.next();
            E b = it2.next();
            if (!a.equals(b))
                return false;
        }
        return true;
    }


    @Override
    public Object[] toArray() {
        Object[] arr = new Object[list.size()];

        for (int i = 0; i < list.size(); i++) {
            arr[i] = list.get(i);
        }
        return arr;
    }

    @Override
    @SuppressWarnings("unchecked")
    public E[] toArray(E[] holder) throws NullPointerException {
        if (holder == null)
            throw new NullPointerException("Array cannot be null.");

        int size = list.size();

        if (holder.length < size) {
            Class<?> component = holder.getClass().getComponentType();
            holder = (E[]) java.lang.reflect.Array.newInstance(component, size);
        }

        for (int i = 0; i < size; i++) {
            holder[i] = list.get(i);
        }

        if (holder.length > size) {
            holder[size] = null;
        }

        return holder;
    }


    @Override
    public boolean isFull() {
        return false;
    }

    @Override
    public int size() {
        return list.size();
    }

    @Override
    public void clear() {
        list.clear();
    }

    @Override
    public Iterator<E> iterator() {
        Object[] copy = list.toArray();
        return new MyQueueIterator<E>(copy);
    }
}
