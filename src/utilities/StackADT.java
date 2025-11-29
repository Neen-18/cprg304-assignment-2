package utilities;

import java.util.EmptyStackException;

/**
 * <p>
 * The <code>StackADT</code> interface defines the abstract data type for a Stack.
 * </p>
 * 
 * @param <E> The type of elements this list holds.
 */

public interface StackADT<E> {

    /**
     * Adds an element to the top of the stack.
     * 
     * @param element the element to be added
     * @precondition element != null
     * @postcondition The element is added to the top of the stack and
     *       the size of the stack increases by one.
     * @throws NullPointerException if the element is null
     */
    public void push(E element) throws NullPointerException;

    /**
     * Removes and returns the element at the top of the stack.
     * 
     * @return the element removed from the top of the stack
     * @precondition The stack is not empty
     * @postcondition The top element is removed and the size of the stack
     *       decreases by one.
     * @throws EmptyStackException if the stack is empty
     */
    public E pop() throws EmptyStackException;

    /**
     * Returns the element at the top of the stack without removing it.
     * 
     * @return the element at the top of the stack
     * @precondition The stack is not empty
     * @postcondition The stack remains unchanged
     * @throws EmptyStackException if the stack is empty
     */
    public E peek() throws EmptyStackException;
    
	/**
	 * Returns true if this list contains the specified element. More formally,
	 * returns true if and only if this list contains at least one element e such
	 * that (o==null ? e==null : o.equals(e)).
	 * 
	 * @param toFind element whose presence in this list is to be tested.
	 * @return true if this list contains the specified element.
	 * @throws NullPointerException if the specified element is null and this list
	 *                              does not support null elements.
	 */
	public boolean contains( E toFind ) throws NullPointerException;
	
	/**
	 * Returns the 1-based position where an object is on this stack. If the object
	 * o occurs as an item in this stack, this method returns the distance from the
	 * top of the stack of the occurrence nearest the top of the stack; the topmost
	 * item on the stack is considered to be at distance 1. The equals method is
	 * used to compare o to the items in this stack.
	 * 
	 * @param toFind the desired object.
	 * @return the 1-based position from the top of the stack where the object is
	 *         located; the return value -1 indicates that the object is not on the
	 *         stack.
	 */
	public int search( E toFind );
	
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
	 * sequence. Obeys the general contract of the Collection.toArray method.
	 * 
	 * @return an array containing all of the elements in this list in proper
	 *         sequence.
	 */
	public Object[] toArray(E[] dest) throws NullPointerException;
	
    /**
     * Determines whether the stack is empty.
     * 
     * @return true if the stack contains no elements, false otherwise
     * @precondition None
     * @postcondition None
     */
    public boolean isEmpty();
    
	/**
	 * Used to compare two Stack ADT's. To be equal two stacks must contain equal
	 * items appearing in the same order.
	 * 
	 * @param that the Stack ADT to be compared to this stack.
	 * @return <code>true</code> if the stacks are equal.
	 */
	public boolean equals( StackADT<E> other);
    
    /**
     * Returns the number of elements in the stack.
     * 
     * @return the number of elements currently in the stack
     * @precondition None
     * @postcondition None
     */
    public int size();

    /**
     * Removes all elements from the stack.
     * 
     * @precondition None
     * @postcondition The stack is empty
     */
    public void clear();
    
	/**
	 * Returns an iterator over the elements in this stack in proper sequence.
	 * 
	 * @return an iterator over the elements in this stack in proper sequence.
	 */
	public Iterator<E> iterator();
    
	/**
	 * Returns true if the number of items in the stack equals the length.  
	 * This operation is only implement when a fixed size stack is required.
	 * @return <code>true</code> if stack is at capacity.
	 */
	public boolean stackOverflow();

	
}
