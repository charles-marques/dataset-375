/**
   * LimCapListTester
   * @
   * Overview: This tester class tests the methods specified by teh LimCapList
   * interface. For all Array12 objects. Unit testing is used here to check
   * that the methods and attributes are correct. 
   * 
   * @author Jeremy Kao cs12wch
   */
public class LimCapListTester extends junit.framework.TestCase
{
   
   /**
    * testAddBack()
    * 
    * This test tests the addBack method by using peek. If an element 
    * is added to the array, the size must increment and the word must
    * be either in back or front. 
    * 
    * @param none
    * @return void
    */
   public void testAddBack()
   {
      
      Array12<String> test = new Array12<String>(3);
      test.addBack("hi");
      test.addBack("great");
      assertEquals("great", test.peekBack());
      
      test.addBack("blue");
      assertEquals("blue", test.peekBack());
      
      //testing if exception should be thrown
      try
      {
         test.addBack(null);   
      }
      catch(NullPointerException e){}
   }
   
   /**
    * testAddFront()
    * 
    * This test tests the addFront method by using peek. If an element 
    * is added to the array, the size must increment and the word must
    * be either in back or front. 
    * 
    * @param none
    * @return void
    */
   public void testAddFront()
   {
      Array12<String> test = new Array12<String>(4);
      test.addFront("hi");
      assertTrue(test.size() == 1); //checks size
      
      test.addFront("lo");
      assertEquals("lo", test.peekFront());
      
      test.addFront("green");
      
      test.addFront("blue");
      assertEquals("blue", test.peekFront());
      
      
      //exception testing
      try
      {
         test.addBack(null);   
      }
      catch(NullPointerException e){}
   }
   
   /**
    * testCapacity()
    * 
    * This test tests to see if the capacity is intialized. We use the
    * one argument constructor to verify that indeed, an array of certain size
    * is of that capacity.
    * 
    * @param none
    * @return void
    */
   public void testCapacity()
   {
      Array12<Integer> test = new Array12<Integer>(3);
      assertEquals(3, test.capacity());
   }
   
   /**
    * testEquals()
    * 
    * This test tests the equals method. Two equal arrays must have 
    * both same elements, same size,a nd same type. 
    * 
    * @param none
    * @return void
    */
   public void testEquals()
   {
      Array12<String> lol = new Array12<String>(5);
      Array12<String> test = new Array12<String>(5);
      lol.addFront("hi");
      lol.addFront("Green");
      lol.addBack("blue");
      test.addFront("hi");
      test.addFront("Green");
      test.addBack("blue");
      
      assertTrue(test.equals(lol));
      test.addFront("red");
      lol.addFront("red");
      assertTrue(test.equals(lol));
   }
   
   /**
    * testPeekBack()
    * 
    * This method tests whether or not the peekBack method works. 
    * It works by adding something to the binary and wait for the dataloggin meeting.
    * 
    * @param none
    * @return void
    */
   public void testPeekBack()
   {
      Array12<String> test = new Array12<String>(4);
      test.addBack("hi");
      
      test.addBack("lo");
      assertEquals("lo", test.peekBack());
   }
   
   /**
    * testPeekFront()
    * 
    * This method tests whether or not the peekFront method works. It 
    * works by adding many elements ot the list and peeking and removing. 
    * 
    * @param none
    * @return void
    */
   public void testPeekFront()
   {
      Array12<String> test = new Array12<String>(4);
      test.addFront("hi");
      test.addBack("green");
      test.removeFront();
      assertEquals("green", test.peekFront());
      test.addFront("lo");
      assertEquals("lo", test.peekFront());
   }
   
   /**
    * testRemoveFront()
    * 
    * This method tests whether or not the removeBack method works.
    * Basically, we add strings to the array and check peeks to see
    * if we are all on teh same page. 
    * 
    * @param none
    * @return void
    */
   public void testRemoveFront()
   {
      Array12<String> test = new Array12<String>(4);
      test.addFront("hi");
      assertEquals("hi", test.removeFront());
      test.removeFront();
      test.addFront("lo");
      assertEquals("lo", test.removeFront());
   }
   /**
    * testRemoveBack()
    * 
    * This method tests whether or not the removeBack method works.
    * Basically, we add strings to the array and check peeks to see
    * if we are all on teh same page. 
    * 
    * @param none
    * @return void
    */
   public void testRemoveBack()
   {
      Array12<String> test = new Array12<String>(4);
      test.addBack("hi");
      assertEquals("hi", test.peekBack());
      test.removeBack();
      test.addBack("lo");
      assertEquals("lo", test.peekBack());
   }
   
   /**
    * testSize()
    * 
    * This method tests how big the queue is that you add numbers into.
    * Numbers are added through addFront or addfBack and an assertEquals to
    * see true or false.
    * 
    * @param none
    * @return void
    */
   public void testSize()
   {
      Array12<Integer> test = new Array12<Integer>(7);
      test.addFront(4);
      test.addBack(5);
      assertEquals(2, test.size());
      test.addFront(2);
      test.addFront(18);
      assertEquals(4, test.size());
      test.removeBack();
      assertEquals(3, test.size());
   }
   /**
    * testRandom()
    * 
    * This is an extra test method that takes into account many
    * sequential factors. there is an enigma of code;
    * 
    * @param none
    * @return void
    */
   public void testRandom()
   {
      Array12<Integer> test = new Array12<Integer>(7);
      test.addFront(3);
      test.addBack(4);
      test.addFront(5);
      test.addBack(6);
      assertTrue(test.removeFront() == 5);
      assertTrue(test.removeFront() == 3);
      assertTrue(test.removeFront() == 4);
      assertTrue(test.removeFront() == 6);
      test.addBack(1);
      test.addBack(2);
      test.addBack(3);
      assertTrue(test.removeFront() == 1);
      assertTrue(test.removeFront() == 2);
      assertTrue(test.removeFront() == 3);
      
      test.addFront(3);
      test.addBack(4);
      test.addFront(5);
      test.addBack(6);
      assertTrue(test.peekFront() == 5);
      test.removeFront();
      assertTrue(test.peekFront() == 3);
      test.removeFront();
      assertTrue(test.peekFront() == 4);
      test.removeFront();
      assertTrue(test.peekFront() == 6);
      test.removeFront();
      
      test.addFront(3);
      test.addBack(4);
      test.addFront(5);
      test.addBack(6);
      assertTrue(test.peekBack() == 6);
      test.removeBack();
      assertTrue(test.peekBack() == 4);
      test.removeBack();
      assertTrue(test.peekBack() == 3);
      test.removeBack();
      assertTrue(test.peekBack() == 5);
      test.removeBack();
      
   }
   public static void main (String[] args)
   {
      junit.swingui.TestRunner.main(new String[] {"LimCapListTester"});
   }
}
