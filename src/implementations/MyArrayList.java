package implementations;

import utilities.ListADT;
import utilities.Iterator;

public class MyArrayList<E> implements ListADT<E> {

    private static final int DEFAULT_CAPACITY = 10;

    private E[] elements;
    private int size;

    @SuppressWarnings("unchecked")
    public MyArrayList() {
        elements = (E[]) new Object[DEFAULT_CAPACITY];
        size = 0;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    @SuppressWarnings("unchecked")
    public void clear() {
        elements = (E[]) new Object[DEFAULT_CAPACITY];
        size = 0;
    }

    @Override
    public boolean add(int index, E toAdd) throws NullPointerException, IndexOutOfBoundsException {
        if (toAdd == null)
            throw new NullPointerException("Element cannot be null.");

        if (index < 0 || index > size)
            throw new IndexOutOfBoundsException("Index: " + index);

        checkListSize(size + 1);

        // shift right
        if (size - index >= 0) {
            System.arraycopy(elements, index, elements, index + 1, size - index);
        }
        elements[index] = toAdd;
        size++;
        return true;
    }

    @Override
    public boolean add(E toAdd) throws NullPointerException {
        if (toAdd == null)
            throw new NullPointerException("Element cannot be null.");
        checkListSize(size + 1);
        elements[size++] = toAdd;
        return true;
    }

    @Override
    public boolean addAll(ListADT<? extends E> toAdd) throws NullPointerException {
        if (toAdd == null)
            throw new NullPointerException("List cannot be null.");
        Iterator<? extends E> it = toAdd.iterator();
        while (it.hasNext()) {
            add(it.next());
        }
        return true;
    }

    @Override
    public E get(int index) throws IndexOutOfBoundsException {
        checkIndex(index);
        return elements[index];
    }

    @Override
    public E remove(int index) throws IndexOutOfBoundsException {
        checkIndex(index);
        E removed = elements[index];
        int numMoved = size - index - 1;
        if (numMoved > 0) {
            System.arraycopy(elements, index + 1, elements, index, numMoved);
        }
        elements[--size] = null;
        return removed;
    }

    @Override
    public E remove(E toRemove) throws NullPointerException {
        if (toRemove == null)
            throw new NullPointerException("Cannot remove null.");

        for (int i = 0; i < size; i++) {
            if (toRemove.equals(elements[i])) {
                return remove(i);
            }
        }
        return null;
    }

    @Override
    public E set(int index, E toChange) throws NullPointerException, IndexOutOfBoundsException {
        if (toChange == null)
            throw new NullPointerException("Cannot set null.");
        checkIndex(index);
        E old = elements[index];
        elements[index] = toChange;
        return old;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public boolean contains(E toFind) throws NullPointerException {
        if (toFind == null)
            throw new NullPointerException("Cannot search for null.");
        for (int i = 0; i < size; i++) {
            if (toFind.equals(elements[i]))
                return true;
        }
        return false;
    }

    @Override
    @SuppressWarnings("unchecked")
    public E[] toArray(E[] destArray) throws NullPointerException {
        if (destArray == null) {
            throw new NullPointerException("Array cannot be null.");
        }

        if (destArray.length < size) {
            Class<?> componentType = destArray.getClass().getComponentType();

            E[] newArr = (E[]) java.lang.reflect.Array.newInstance(componentType, size);

            System.arraycopy(elements, 0, newArr, 0, size);
            return newArr;
        }

        System.arraycopy(elements, 0, destArray, 0, size);

        // If there is extra space, set the element after the last to null
        if (destArray.length > size) {
        	destArray[size] = null;
        }

        return destArray;
    }

    @Override
    public Object[] toArray() {
        Object[] arr = new Object[size];
        System.arraycopy(elements, 0, arr, 0, size);
        return arr;
    }

    @Override
    public Iterator<E> iterator() {
        return new MyArrayListIterator<E>(this);
    }

    private void checkListSize(int minCapacity) {
        if (elements.length >= minCapacity) return;
        int newCapacity = elements.length * 2;
        if (newCapacity < minCapacity) newCapacity = minCapacity;
        @SuppressWarnings("unchecked")
        E[] newArr = (E[]) new Object[newCapacity];
        System.arraycopy(elements, 0, newArr, 0, size);
        elements = newArr;
    }

    private void checkIndex(int index) {
        if (index < 0 || index >= size)
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
    }

}
