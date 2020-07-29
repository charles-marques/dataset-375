/**
   * Queue12<E>
   * @
   * Overview: This class defines the implementation of a Queue
   * which implements the LimCapQueue interface. A stack is FIFO structure
   * or First-in-First-Out. 
   * 
   * @author Jeremy Kao cs12wch
   */

public class Queue12<E> implements LimCapQueue<E>
{
   //intialize private instance variables for this class
   private int size, capacity, back, front;
   private Object[] queue;
   
   /**
    * Queue12(int sizeLimit)
    * 
    * This one argument constructor initalizes the array of objects and
    * the maximum number of elements it can hold, its capacity.
    * 
    */
   public Queue12(int sizeLimit)
   {
      back = sizeLimit - 1;
      front = back;
      queue = new Object[sizeLimit];
      capacity = sizeLimit;
   }
   
   /**
    * capacity()
    * 
    * This one argument constructor initalizes the array of objects and
    * the maximum number of elements it can hold, its capacity,
    * 
    * @param none
    * @return int the number of elements in the circular array
    */
   public int capacity()
   {
      return capacity;
   }
   
   /**
    * dequeue()
    * 
    * Removes outermost element from the front of the array.
    * 
    * @return E the object that is occupying the first spot in array and is removed.
    */
   public E dequeue()
   {
      if (size > 0)
      {
         size--;
         Object temp = peek();   
         queue[front] = null;
         front--;
         if (front < 0)
         {
            front = capacity - 1;
         }
         System.out.println("front: " + temp);
         return (E)temp;
      }
      return null;
   }
   
   /**
    * enqueue(E e)
    * 
    * Adds element to the back of Queue12; the front is where
    * dequeue acts upon, fulfilling the FIFO requirement
    * 
    * @return E the object that is to be added to the Queue.
    * @throws NullPointerException if the argument is null
    */
   public boolean enqueue(E e) throws NullPointerException
   {
      //Checks to see if the arguments are not null or the array
      // size does not exceed capacity; if so, return null. 
      if (e == null || size > capacity)
      {
         throw new NullPointerException(); 
      }  
      if (size < capacity)
      {
         queue[back] = e;
         back--;
         size++;
         if(back < 0)
         {
            back = capacity - 1;
         }
         return true;
      }
      return false;
   }
   
   /**
    * equals(java.lang.Object o)
    * 
    * Compares whether two objects are of the same type, of the same
    * size, and contain the same elements
    * 
    * @param E e the element to be inserted.
    * @return boolean if the two objects contain the same elements,
    * are of the same type, and have the same size
    */
   public boolean equals(java.lang.Object o)
   {
      int numElements = this.size();
      if (((Queue12<E>)o).size() != numElements)
      {
         return false;
      }
      for (int i = 0; i < numElements; i++)
      {
         if (((Queue12<E>)o).queue[i] != this.queue[i])
         {
            return false;
         }
      }
      return true;
   }
   
   /**
    * peek()
    * 
    * Checks and returns the first element of the array
    * 
    * @return E the object that is occupying the first spot in array.
    */
   public E peek()
   {
      return (E)queue[front];
   }
   
   /**
    * size()
    * 
    * computes the number of elements added within the Queue
    * 
    * @return int the total number of elements inside the Queue.
    */
   public int size()
   {
      return size;
   }
   
}
