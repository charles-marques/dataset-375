/**
   * Stack12<E>
   * @
   * Overview: This class defines the implementation of a Stack
   * which implements the LimCapStack interface. A stack is LIFO structure
   * or Last-in-First-Out. 
   * 
   * @author Jeremy Kao cs12wch
   */
public class Stack12<E> implements LimCapStack<E>
{
   //instance variables for Stack objects only
   private int size, capacity, front;
   private Object[] stack;
   
   /**
    * Stack12(int sizeLimit)
    * 
    * This one argument constructor initalizes the array of objects and
    * the maximum number of elements it can hold, its capacity.
    * 
    */
   public Stack12(int sizeLimit)
   {
      stack = new Object[sizeLimit];
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
    * 
    * 
    */
      
   public int capacity()
   {
      return capacity;
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
      if (((Stack12<E>)o).size() != numElements)
      {
         return false;
      }
      for (int i = 0; i < numElements; i++)
      {  
         //must type cast because of generic object class
         if (((Stack12<E>)o).stack[i] != this.stack[i])
         {
            return false;
         }
         
      }
      return true;
   }
   
   /**
    * peek()
    * 
    * Checks and returns the last element of the array
    * 
    * @return E the object that is occupying the first spot in array.
    */
   public E peek()
   {
      int first = 0;
      if (front - 1 < 0)
      {
         first = capacity - 1;
      }
      else
      {
         first = front - 1;
      }
      return (E)stack[first];
   }
   
   /**
    * pop()
    * 
    * Checks, removes, and returns the oldest element of an array.
    * 
    * @return E the object that is occupying the last spot in array and is removed.
    */
   public E pop()
   {
      if (size > 0)
      {
         size--;
         Object temp = peek();
         stack[front] = null;
         front--;
         if (front < 0)
         {
            front = capacity - 1;
         }
         return (E)temp;
      }
      return null;
      
   }
   
   /**
    * push(E e)
    * 
    * Adds element to the front of stack 12; the front is also where
    * pop acts upon.
    * 
    * @return E the object that is occupying the last spot in array.
    * @throws NullPointerException if the arguement is null
    */
   public boolean push(E e) throws NullPointerException
   {
      //Checks to see if the argument is not null
      if (e == null || size > capacity)
      {
         throw new NullPointerException(); 
      }
      if (size < capacity)
      {
         stack[front] = e;
         front++;
         size++;
         if (front > capacity - 1)
         {
            front = 0;
         }
         return true;
      }
      return false;
   }
   
   /**
    * size()
    * 
    * computes the number of elements added within the Stack
    * 
    * @return int the total number of elements inside the stack.
    */
   public int size()
   {
      return size;
   }
}
