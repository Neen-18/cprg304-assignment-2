package implementations;

import utilities.ListADT;
import utilities.Iterator;

public class MyDLL<E> implements ListADT<E> {
    private MyDLLNode<E> head;
    private MyDLLNode<E> tail;
    private int size;

    public MyDLL() {
        head = null;
        tail = null;
        size = 0;
    }


    private MyDLLNode<E> getNode(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException(
                "Index: " + index + ", Size: " + size);
        }

        MyDLLNode<E> current;
        if (index < size / 2) {
            current = head;
            for (int i = 0; i < index; i++) {
                current = current.next;
            }
        } else {
            current = tail;
            for (int i = size - 1; i > index; i--) {
                current = current.prev;
            }
        }
        return current;
    }

    private void insertBefore(MyDLLNode<E> node, E data) {
    	MyDLLNode<E> newNode = new MyDLLNode<>(data);

        // Insert at end
        if (node == null) {
            if (tail == null) {
                // empty list
                head = newNode;
                tail = newNode;
            } else {
                newNode.prev = tail;
                tail.next = newNode;
                tail = newNode;
            }
        } else if (node.prev == null) {
            // Insert before head
            newNode.next = node;
            node.prev = newNode;
            head = newNode;
        } else {
            // Insert in the middle
        	MyDLLNode<E> prevNode = node.prev;
            newNode.prev = prevNode;
            newNode.next = node;
            prevNode.next = newNode;
            node.prev = newNode;
        }

        size++;
    }

    /**
     * Unlinks (removes) the given node from the list.
     *
     * @param node node to remove
     * @return the data that was stored in the node
     */
    private E unlink(MyDLLNode<E> node) {
        E element = node.data;
        MyDLLNode<E> prev = node.prev;
        MyDLLNode<E> next = node.next;

        if (prev == null) {
            head = next;
        } else {
            prev.next = next;
        }

        if (next == null) {
            tail = prev;
        } else {
            next.prev = prev;
        }

        node.data = null;
        node.prev = null;
        node.next = null;

        size--;
        return element;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void clear() {
    	MyDLLNode<E> current = head;
        while (current != null) {
        	MyDLLNode<E> next = current.next;
            current.data = null;
            current.prev = null;
            current.next = null;
            current = next;
        }
        head = null;
        tail = null;
        size = 0;
    }

    @Override
    public boolean add(int index, E toAdd)
            throws NullPointerException, IndexOutOfBoundsException {

        if (toAdd == null) throw new NullPointerException("No null elements.");
        if (index < 0 || index > size) throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);

        if (index == size) {
            // add at end
            insertBefore(null, toAdd);
        } else {
        	MyDLLNode<E> nodeAtIndex = getNode(index);
            insertBefore(nodeAtIndex, toAdd);
        }

        return true;
    }

    @Override
    public boolean add(E toAdd) throws NullPointerException {
        if (toAdd == null) {
            throw new NullPointerException("No null elements.");
        }

        insertBefore(null, toAdd); // insert at end
        return true;
    }

    @Override
    public boolean addAll(ListADT<? extends E> toAdd)
            throws NullPointerException {

        if (toAdd == null) {
            throw new NullPointerException("The list cannot be null.");
        }

        Iterator<? extends E> it = toAdd.iterator();
        boolean modified = false;

        while (it.hasNext()) {
            E element = it.next();
            if (element == null) {
                throw new NullPointerException("No null elements.");
            }
            add(element);
            modified = true;
        }

        return modified;
    }

    @Override
    public E get(int index) throws IndexOutOfBoundsException {
    	MyDLLNode<E> node = getNode(index);
        return node.data;
    }

    @Override
    public E remove(int index) throws IndexOutOfBoundsException {
    	MyDLLNode<E> node = getNode(index);
        return unlink(node);
    }

    @Override
    public E remove(E toRemove) throws NullPointerException {
        if (toRemove == null) {
            throw new NullPointerException("Cannot remove null.");
        }

        MyDLLNode<E> current = head;
        while (current != null) {
            if (toRemove.equals(current.data)) {
                return unlink(current);
            }
            current = current.next;
        }

        return null;
    }

    @Override
    public E set(int index, E toChange)
            throws NullPointerException, IndexOutOfBoundsException {

        if (toChange == null) {
            throw new NullPointerException("Cannot set null elements.");
        }

        MyDLLNode<E> node = getNode(index);
        E old = node.data;
        node.data = toChange;
        return old;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public boolean contains(E toFind) throws NullPointerException {
        if (toFind == null) {
            throw new NullPointerException("Cannot search for null elements.");
        }

        MyDLLNode<E> current = head;
        while (current != null) {
            if (toFind.equals(current.data)) {
                return true;
            }
            current = current.next;
        }
        return false;
    }

    @Override
    public Object[] toArray() {
        Object[] arr = new Object[size];
        MyDLLNode<E> current = head;
        int i = 0;
        while (current != null) {
            arr[i++] = current.data;
            current = current.next;
        }
        return arr;
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

            MyDLLNode<E> current = head;
            int i = 0;
            while (current != null) {
                newArr[i++] = current.data;
                current = current.next;
            }

            return newArr;
        }


        MyDLLNode<E> current = head;
        int i = 0;
        while (current != null) {
        	destArray[i++] = current.data;
            current = current.next;
        }


        if (destArray.length > size) {
        	destArray[size] = null;
        }

        return destArray;
    }

    @Override
    public Iterator<E> iterator() {
        return new MyArrayListIterator<E>(this);
    }
}
