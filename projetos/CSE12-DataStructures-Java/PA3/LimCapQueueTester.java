public class LimCapQueueTester extends junit.framework.TestCase
{
   public void testCapacity()
   {
      Queue12<String> test = new Queue12<String>(4);
      assertTrue(test.capacity() == 4);
   }
   public void testDequeue()
   {
      Queue12<String> test = new Queue12<String>(4);
      test.enqueue("lol");
      test.enqueue("LOLOLOL");
      assertTrue(test.dequeue() == "lol");
      assertTrue(test.peek() == "LOLOLOL");
      assertTrue(test.dequeue() == "LOLOLOL");
   }
   public void testEnqueue()
   {
      Queue12<String> test = new Queue12<String>(4);
      test.enqueue("lol");
      test.enqueue("green");
      assertTrue(test.size() == 2);
      assertTrue(test.peek() == "lol");
   }
   public void testEquals()
   {
      Queue12<String> test = new Queue12<String>(4);
      test.enqueue("hi");
      test.enqueue("green");
      Queue12<String> test2 = new Queue12<String>(4);
      test2.enqueue("hi");
      test2.enqueue("green");
      
      assertTrue(test.equals(test2));
   }
   public void testPeek()
   {
      Queue12<String> test = new Queue12<String>(4);
      test.enqueue("1023");
      assertTrue(test.peek() == "1023");
      test.enqueue("Hi");
      test.enqueue("lo");
      assertTrue(test.peek() == "1023");
   }
   public void testSize()
   {
      Queue12<String> test = new Queue12<String>(4);
      test.enqueue("hi");
      test.enqueue("bllop");
      test.enqueue("green");
      assertTrue(test.size() == 3);
   }
   public static void main (String[] args)
   {
      junit.swingui.TestRunner.main(new String[] {"LimCapQueueTester"});
   }
}
