   /**
    * This class sets up a priority queue in the form of a heap. It implements
    * the PQueue interface, which defines several methods, such as add(), size(),
    * remove(), isEmpty(), and peek(). I also defined a couple other methods that
    * Heaps are supposed to have, and also some private methods to bubbleUp and
    * trickleDown. The heap is a MaxHeap, and the priority is highest value.
    *
    * @author Jeremy Kao cs12wch A09783668
    */
public class Heap12<E extends Comparable<? super E>> implements PQueue<E>
{
   //private instance variables for Heap12 class.
   private int size;
   private Object[] backingStore;
   
   /**
    * This constructor intializes the array to have an initial length of 5.
    *
    * @param       none
    * @return      void
    */
   public Heap12()
   {
      backingStore = new Object[5];
      
   }
   /**
    * This one argument constructor performs a deep copy on an array
    * that is passed in as a parameter. Size of the array is set here, 
    * and the backingStore array takes in all copied values from the 
    * passed in Heap. 
    *
    * @param       none
    * @return      void
    */
   public Heap12(Heap12<E> heap)
   {
      for (int i = 0; i < heap.backingStore.length; i++)
      {
         this.backingStore[i] = heap.backingStore[i];
         size++;
      }
   }
   
   /**
    * This method constructs a heap structure from an array initialized above. The
    * first element is the root, and all subsequent elements are descendents of the root.
    * A parent-child structure is formed by this method. When the size of the array reaches
    * the end, the array doubles its size. 
    *
    * @param   e   the element that needs to be added to the Heap.
    * @return      void
    */
   public void add(E e)
   {
      //Check for null pointer exceptions.
      if (e == null)
      {
         throw new NullPointerException();
      }
      if (size == backingStore.length)
      {
         //deep copying array and doubling size.
         Object[] tmpStore = new Object[backingStore.length * 2];
         for (int i = 0; i < backingStore.length; i++)
         {
            tmpStore[i] = backingStore[i];
         }
         backingStore = tmpStore;
      }
      backingStore[size] = e;
      size++;
      bubbleUp(size -1);
   }
   
   /**
    * This method compares two objects. If they are of teh same type, same size, 
    * and contain the same contents, then true is returned. If not, false is returned. 
    *
    * @param   o   Object o is a generic object since almost all objects
    *              inherit from object.
    * @return      void
    */
   public boolean equals(Object o)
   {
      //check size;
      int numElements = this.size();
      if (((Heap12<E>)o).size() != numElements) //check size of two elements
      {
         return false;
      }
      //checking for content
      for (int i = 0; i < numElements; i++)
      {
         if (((Heap12<E>)o).backingStore[i] != this.backingStore[i]) // check for same elements
         {
            return false;
         }
      }
      return true;
   }
   
   /**
    * This test sees if the heap contains any elements. If so, return true.
    * If not, then return false. 
    *
    * @param       none
    * @return      void
    */
   public boolean isEmpty()
   {
      if (size <= 0)
      {
         return true;
      }
      return false;
   }
   
   /**
    * This peek method takes the highest priority element and displays it. A user 
    * may easily correlate this with the remove method without having to call
    * the remove method. If size of Heap is 0, then this method should return null.
    *
    * @param       none
    * @return      void
    */
   public E peek()
   {
      //checking if size equals zero. if so, return null.
      if (size == 0)
      {
         return null;
      }
      return (E)backingStore[0];
   }
   
   /**
    * This remove method takes the highest priority item (the item with 
    * the greatest value, removes it, and returns what it removed. If the
    * size is zero, then the method should return null. TrickleDown method
    * is called here too so that the heap structure may be maintained.
    *
    * @param       none
    * @return      void
    */
   public E remove()
   {
      if (size == 0)
      {
         return null;
      }
      E element = (E)backingStore[0];
      backingStore[0] = backingStore[size-1];
      backingStore[size-1] = null;
      size--;
      this.trickleDown(0);
      return element;
   }
   
   /**
    * This size() method returns the number of elements contained within 
    * the heap structure. There is a private instance variable to keep
    * track of the size. This method just returns that. 
    *
    * @param       none
    * @return      void
    */
   public int size()
   {
      return size;
   }

   /**
    * This private constructor is for the sort method. Instead of
    * initialzing a new array, the backingStore array is set to the
    * array that is passed into the method. Size is the length of the
    * array. Then all you gotta do is keep calm and carry on.
    *
    * @param   Object[]   array of objects
    * @return      void
    */
   private Heap12(Object[] o)
   {
      backingStore = o;
      size = o.length;
   }
   
   /**
    * This static method sort() takes an array and performs a heapsort
    * on the array. This is all done within the array, without having to
    * create another array. If the passed in array is null, a null
    * ponter exception is thrown. 
    *
    * @param       none
    * @return      void
    */
   public static <T extends java.lang.Comparable<? super T>> void sort(T[] a)
   {
      //checking to see if input is null. if so, return NullPointerException.
      if (a == null)
      {
         throw new NullPointerException();
      }
      Heap12<T> needsSort = new Heap12(a);
      int lengthOfArr = needsSort.backingStore.length;
      int index = lengthOfArr;
      for (int i = 0; i < lengthOfArr; i++)
      {
         needsSort.bubbleUp(i);
      }
      while(index != 0)
      {
         needsSort.backingStore[index-1] = needsSort.remove();
         index--;
      }
      
   }
   /**
    * This private method reorganizes the heap structure
    * once something removed from the heap. This is a 
    * recursive method call that acts on both the right and left
    * of the root.
    *
    * @param   index    the starting location of the trickleDown method.
    * @return      void
    */
   private void trickleDown(int index)
   {
      int leftIndex = 2 * index + 1;
      int rightIndex = 2 * index + 2;
      if (size < 2)
      {
         return;
      }
      if (leftIndex < size && rightIndex < size && ((E)backingStore[index]).compareTo(((E)backingStore[leftIndex])) < 0
            && ((E)backingStore[leftIndex]).compareTo((E)backingStore[rightIndex]) > 0)
      {
         swap(backingStore, index, leftIndex);
         trickleDown(leftIndex);
      }
      else
      {
         if (rightIndex < size &&((E)backingStore[index]).compareTo(((E)backingStore[rightIndex])) < 0)
         {
            swap(backingStore, index, rightIndex);
            trickleDown(rightIndex);
         }
      }
      return;
   }
   
   /**
    * This method reorganizes the array every time somethign is added 
    * to the heap. This method calls the swap method. 
    *
    * @param   index    where to start comparison.
    * @return      void
    */
   private void bubbleUp(int index)
   {
      int parentIndex = (index - 1)/2;
      if (index == 0)
      {
         return;
      }
      if (((E)backingStore[index]).compareTo(((E)backingStore[parentIndex])) > 0)
      {
         swap(backingStore, index, parentIndex);
      }
      bubbleUp(parentIndex);
   }
   /**
    * This swap method takes the values of two cells in the array
    * structure for the heap and swaps them. A temporary value
    * is needed to accomplish this.
    *
    * @param   array   an array of objects, usually the backingStore
    * @param   firstVal the first item to compare and switch
    * @param   secondVal the second item to compare and switch.
    * @return      void
    */
   private void swap(Object[] array, int firstVal, int secondVal)
   {
      Object tmp = array[firstVal];
      array[firstVal] = array[secondVal];
      array[secondVal] = tmp;
   }
}
