/**
   * Array12<E>
   * @
   * Overview: This class defines the implementation of a Circular Array
   * which implements the LimCapList interface. This class contains
   * the addFront(), addBack(), capacity(), size(), equals(), peekBack(),
   * peekFront(), removeBack(), and removeFront() as specified by the
   * LimCapList interface.
   * 
   * @author Jeremy Kao cs12wch
   */

public class Array12<E> implements LimCapList<E>
{
   //private instance variables for each Array12 object
   private int size, capacity, front, back;
   private Object[] array;
   
   /**
    * Array12(int sizeLim)
    * 
    * This one argument constructor initalizes the array of objects and
    * the maximum number of elements it can hold, its capacity.
    * 
    */
   public Array12(int sizeLim)
   {
      front = 0;
      back = sizeLim - 1;
      array = (E[]) new Object[sizeLim];
      this.setCapacity(sizeLim);
   }
   
   /**
    * setCapacity(int numPossible)
    * 
    * Mutator method for the capacity instance variable
    * @param int numPossible - the capacity of the array
    * @return void
    */
   public void setCapacity(int numPossible)
   {
      this.capacity = numPossible;
   }
   /**
    * addBack(E e)
    * 
    * Adds any object to the back of the array
    * 
    * @param E e the element to be inserted.
    * @return boolean if the addition was successful or not
    * @throws NullPointerException
    */
   public boolean addBack(E e) throws NullPointerException
   {
      //if the argument is null or the size exceeds the capacity
      //there will be a NullPointerException thrown.
      if (e == null || size > capacity)
      {
         throw new NullPointerException(); 
      }
      if (size <= capacity)
      {
         array[back] = e;
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
    * addFront(E e)
    * 
    * Adds any object to the front of the array
    * 
    * @param E e the element to be inserted.
    * @return boolean if the addition was successful or not
    * @throws NullPointerException
    */
   public boolean addFront(E e) throws NullPointerException
   {
      //if the argument is null or the size exceeds the capacity
      //there will be a NullPointerException thrown.
      if (e == null || size > capacity)
      {
         throw new NullPointerException(); 
      }
      if (size <= capacity)
      {
         array[front] = e;
         front++;
         size++;
         if (front > capacity - 1) //prevent ArrayIndexOutOfBounds 
         {
            front = 0;
         }
         return true;
      }
      return false;
   }
   
   /**
    * capacity
    * 
    * Computes the number of elements the circular array may hold.
    * 
    * @param none
    * @return int the number of elements in the circular array
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
      if (((Array12<E>)o).size() != numElements) //check size of two elements
      {
         return false;
      }
      for (int i = 0; i < numElements; i++)
      {
         if (((Array12<E>)o).array[i] != this.array[i]) // check for same elements
         {
            return false;
         }
      }
      return true;
   }
   
   /**
    * peekBack()
    * 
    * Checks and returns the last element of the array
    * 
    * @return E the object that is occupying the last spot in array.
    */
   public E peekBack()
   {
      int last = 0;
      if (back + 1 >= capacity)
      {
         last = 0;
      }
      else
      {
         last = back + 1;
      }
      return (E)array[last];
   }
   
   /**
    * peekFront()
    * 
    * Checks and returns the first element of the array
    * 
    * @return E the object that is occupying the first spot in array.
    */
   public E peekFront()
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
      return (E)array[first];
   }
   
   /**
    * removeBack()
    * 
    * Checks and removes the last element of the array and returns 
    * what was previously at the removed spot.
    * 
    * @return E the object that was occupying the last spot in array.
    */
   public E removeBack()
   {
      if (size > 0)
      {
         size--;
         Object temp = peekBack();
         array[back] = null;
         System.out.println(temp);
         back++;
         if (back > capacity - 1)
         {
            back = 0;
         }
         return (E)temp;
      }
      return null;
   }
   
   /**
    * removeFront()
    * 
    * Checks and removes the first element of the array and returns 
    * what was previously at the removed spot.
    * 
    * @return E the object that was occupying the first spot in array.
    */
   public E removeFront()
   {
      if (size > 0)
      {
         size--;
         Object temp = peekFront();
         array[front] = null;
         System.out.println("front: " + temp);
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
    * size()
    * 
    * Computes how many elements there are in the array.
    * 
    * @return int the number of elements in the array
    */
   public int size()
   {
      return size;
   }
}
