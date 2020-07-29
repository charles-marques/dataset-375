/**
 * Tester class to test the methods of the PQueue interface. Heap12 implements
 * this interface, so we must test this to ensure that Heap 12 is doing
 * exactly what we want. 
 * <p>
 *
 * @author Jeremy Kao cs12wch A09783668
 * 
 */
public class PQueueTester extends junit.framework.TestCase
{
   
   /**
    * This unit test assesses the add() method of the PQueue
    * interface. It checks whether or not size increases once something
    * is added, if the element added is within the backing store, and
    * whether or not a null pointer exception is thrown when null
    * is passed as an element. 
    *
    * @param       none
    * @return      void
    */
   public void testAdd()
   {
      Heap12<Integer> test = new Heap12();
      test.add(3);
      test.add(5);
      //testSizeIncreases
      assertTrue(test.size() == 2);
      //testContains
      assertTrue(test.peek() == 5);
      test.add(4);
      test.add(8);
      test.add(9);
      test.add(12);
      assertTrue(test.peek() == 12);
      //testing for nullpointerexception
      try
      {
         test.add(null);
      }
      catch(NullPointerException e){}
   }
   
   /**
    * This unit test assesses the isEmpty method of the PQueue
    * interface. It checks whether or not the correct boolean values
    * are outputted at certain adding points. I test before I have added
    * anything, after I have added something, and removed everything.
    *
    * @param       none
    * @return      void
    */
   public void testIsEmpty()
   {
      Heap12<Integer> test = new Heap12();
      assertTrue(test.isEmpty()); //should be empty
      test.add(3);
      test.add(5);
      assertFalse(test.isEmpty());
      test.remove();
      test.remove();
      assertTrue(test.isEmpty());
      
   }
   
   /**
    * This unit test assesses the peek method() of the PQUeue interface.
    * The method is tested when an element is added, when elements are removed,
    * and when there are no elements remaining. In that case, the output should
    * be null.
    *
    * @param       none
    * @return      void
    */
   public void testPeek()
   {
      Heap12<Integer> test = new Heap12();
      test.add(3);
      assertTrue(test.peek() == 3);
      test.add(5);
      //testWhenAdding
      assertTrue(test.peek() == 5);
      test.remove();
      //testWhenREmoving
      assertTrue(test.peek() == 3);
      test.add(8);
      assertTrue(test.peek() == 8);
      test.remove();
      test.remove();
      //test if peek returns null if there are no elements left
      assertTrue(test.peek() == null);
   }
   
   /**
    * This unit test assesses the remove() method of the PQueue
    * interface. It checks whether or not size decreases once something
    * is removed, if the queue decreases size when removing is within the 
    * backing store, and whether or not null is returned when nothing
    * is passed as an element. 
    *
    * @param       none
    * @return      void
    */
   public void testRemove()
   {
      Heap12<Integer> test = new Heap12();
      test.add(3);
      test.add(7);
      test.remove();
      //testSizeDecreases
      assertTrue(test.size() == 1);
      //testNewPeek
      assertTrue(test.peek() == 3);
      //testReturn
      assertTrue(test.remove() == 3);
      test.add(4);
      test.add(5);
      test.add(6);
      //assertTrue(test.remove() == 7);
      assertTrue(test.peek() == 6);
      test.remove();
      test.remove();
      test.remove();
      //test if remove returns null if there are no elements
      assertTrue(test.remove() == null);
      assertTrue(test.size() == 0);
      test.add(3);
      test.add(5);
      test.add(2);
      test.add(9);
      test.add(10);
      assertTrue(test.peek()== 10);
      test.add(7);
      test.add(4);
      test.add(8);
      assertTrue(test.size() == 8);
      System.out.println(test.peek());
      test.remove();
      System.out.println(test.peek());
      test.remove();
      System.out.println(test.peek());
      test.remove();
      System.out.println(test.peek());
      test.remove();
      System.out.println(test.peek());
      assertTrue(test.peek() == 5);
   }
   
   /**
    * This unit test assesses the size() method of the PQueue
    * interface. It checks whether or not size increases once something
    * is added, if the size decreases once somethign is removed.
    *
    * @param       none
    * @return      void
    */
   public void testSize()
   {
      Heap12<Integer> test = new Heap12();
      assertTrue(test.size() == 0);
      test.add(3);
      test.add(5);
      //testSizeIncreases
      assertTrue(test.size() == 2);
      //testSizeDecreases
      test.remove();
      assertTrue(test.size() == 1);
   }
   /**
    * This main method sets up everything to display JUnit. We set setup the
    * GUI and what we would need with this tester class, whcih is not much. 
    *
    * @param       none
    * @return      void
    */
   public static void main(String[] args)
   {
      junit.swingui.TestRunner.main(new String[]{"PQueueTester"});
   }
}
