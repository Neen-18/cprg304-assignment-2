package utilities;

import exceptions.EmptyQueueException;

/**
 * <p>
 * The <code>QueueADT</code> interface defines the abstract data type for a Queue.
 * </p>
 * 
 * @param <E> The type of elements this list holds.
 */

public interface QueueADT<E> {

    /**
     * Adds (enqueues) an element to the rear of the queue.
     * 
     * @param element the element to be added
     * @precondition element != null
     * @postcondition The queue size increases by one and 
     *                the element becomes the last item.
     * @throws NullPointerException if element is null
     */
    public void enqueue(E element) throws NullPointerException;

    /**
     * Removes and returns the element at the front of the queue.
     * 
     * @return the element removed from the front
     * @precondition The queue is not empty.
     * @postcondition The queue size decreases by one.
     * @throws EmptyQueueException if the queue is empty
     */
    public E dequeue() throws EmptyQueueException;

    /**
     * Retrieves, but does not remove, the element at the front of the queue.
     * 
     * @return the element currently at the front
     * @precondition The queue is not empty.
     * @postcondition The queue remains unchanged.
     * @throws EmptyQueueException if the queue is empty
     */
    public E peek() throws EmptyQueueException;
    
	/**
	 * dequeueAll removes all items in the queue.
	 */
	public void dequeueAll();
	
    /**
     * Determines whether the queue contains any elements.
     * 
     * @return true if the queue is empty; false otherwise
     * @precondition None
     * @postcondition None
     */
    public boolean isEmpty();
    
	/**
	 * Returns true if this list contains the specified element. More formally,
	 * returns true if and only if this list contains at least one element e
	 * such that (o==null ? e==null : o.equals(e)).
	 * 
	 * @param toFind
	 *            element whose presence in this list is to be tested.
	 * @return true if this list contains the specified element.
	 * @throws NullPointerException
	 *             if the specified element is null and this list does not
	 *             support null elements.
	 */
	public boolean contains( E toFind ) throws NullPointerException;
	
	/**
	 * Returns the 1-based position where an object is on this queue. If the
	 * object o occurs as an item in this queue, this method returns the
	 * distance from the front of the queue of the occurrence nearest the front of
	 * the queue; the first item on the stack is considered to be at distance
	 * 1. The equals method is used to compare o to the items in this queue.
	 * 
	 * @param toFind
	 *            the desired object.
	 * @return the 1-based position from the top of the queue where the object
	 *         is located; the return value -1 indicates that the object is not
	 *         on the queue.
	 */
	public int search( E toFind );
	
	/**
	 * Returns an iterator over the elements in this queue in proper sequence.
	 * 
	 * @return an iterator over the elements in this queue in proper sequence.
	 */
	public Iterator<E> iterator();
	
	/**
	 * Used to compare two Queue ADT's. To be equal two queues must contain equal
	 * items appearing in the same order.
	 * 
	 * @param other the Queue ADT to be compared to this queue.
	 * @return <code>true</code> if the queues are equal.
	 */
	public boolean equals( QueueADT<E> other );
	
	/**
	 * Returns an array containing all of the elements in this list in proper
	 * sequence. Obeys the general contract of the Collection.toArray method.
	 * 
	 * @return an array containing all of the elements in this list in proper
	 *         sequence.
	 */
	public Object[] toArray();

	/**
	 * Returns an array containing all of the elements in this list in proper
	 * sequence; the runtime type of the returned array is that of the specified
	 * array. Obeys the general contract of the Collection.toArray(Object[]) method.
	 * 
	 * @param toHold the array into which the elements of this queue are to be
	 *               stored, if it is big enough; otherwise, a new array of the same
	 *               runtime type is allocated for this purpose.
	 * @return an array containing the elements of this queue.
	 * @throws NullPointerException if the specified array is null.
	 */
	public E[] toArray( E[] holder ) throws NullPointerException;
	
	/**
	 * Returns true if the number of items in the queue equals the
	 * length. This operation is only implement when a fixed length queue is
	 * required.
	 * 
	 * @return <code>true</code> if queue is at capacity.
	 */
	public boolean isFull();
	
    /**
     * Returns the number of elements in the queue.
     * 
     * @return the current number of elements
     * @precondition None
     * @postcondition None
     */
    public int size();

    /**
     * Removes all elements from the queue.
     * 
     * @precondition None
     * @postcondition The queue is empty (size == 0).
     */
    public void clear();
}
