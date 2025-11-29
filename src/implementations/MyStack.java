package implementations;

import utilities.StackADT;
import utilities.Iterator;

import java.util.EmptyStackException;
import java.util.NoSuchElementException;


public class MyStack<E> implements StackADT<E> {

    private MyArrayList<E> list;

    public MyStack() {
        list = new MyArrayList<E>();
    }
    
    @Override
    public boolean contains(E element) throws NullPointerException {
        if (element == null) {
            throw new NullPointerException("Cannot search for null.");
        }
        return list.contains(element);
    }

    @Override
    public void push(E element) throws NullPointerException {
        if (element == null) {
            throw new NullPointerException("This stack implementation does not support null elements.");
        }
        list.add(element); // add at end = top of stack
    }
    
    @Override
    public int search(E toFind) throws NullPointerException {
        if (toFind == null)
            throw new NullPointerException("Cannot search for null.");

        int position = 1; // top = 1
        for (int i = list.size() - 1; i >= 0; i--) {
            if (toFind.equals(list.get(i))) {
                return position;
            }
            position++;
        }

        return -1;
    }
    
    @Override
    @SuppressWarnings("unchecked")
    public E[] toArray(E[] dest) throws NullPointerException {
        if (dest == null) throw new NullPointerException("Array cannot be null.");

        int size = list.size();

        if (dest.length < size) {
            Class<?> componentType = dest.getClass().getComponentType();
            dest = (E[]) java.lang.reflect.Array.newInstance(componentType, size);
        }

        int idx = 0;
        for (int i = size - 1; i >= 0; i--) {
            dest[idx++] = list.get(i);
        }

        if (dest.length > size) {
            dest[size] = null;
        }

        return dest;
    }
    
    @Override
    public Object[] toArray() {
        Object[] arr = new Object[list.size()];

        int index = 0;
        // Fill TOP → BOTTOM
        for (int i = list.size() - 1; i >= 0; i--) {
            arr[index++] = list.get(i);
        }

        return arr;
    }
    
    @Override
    public boolean equals(StackADT<E> other) {
        if (other == null) return false;

        if (this.size() != other.size()) return false;

        MyStack<E> otherStack = (MyStack<E>) other;

        Iterator<E> thisIt = this.iterator();
        Iterator<E> thatIt = otherStack.iterator();

        while (thisIt.hasNext() && thatIt.hasNext()) {
            E a = thisIt.next();
            E b = thatIt.next();

            if (!a.equals(b)) return false;
        }

        return true;
    }


    @Override
    public E pop() throws EmptyStackException {
        if (list.isEmpty()) {
        	throw new EmptyStackException();
        }
        return list.remove(list.size() - 1);
    }

    @Override
    public E peek() throws EmptyStackException {
        if (list.isEmpty()) {
        	throw new EmptyStackException();
        }
        return list.get(list.size() - 1);
    }

    @Override
    public boolean isEmpty() {
        return list.isEmpty();
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
    public boolean stackOverflow() {
        return false;
    }
    
    @Override
    public Iterator<E> iterator() {
    	Object[] copy = list.toArray();
    	return new MyStackIterator<E>(copy);
    }
}
