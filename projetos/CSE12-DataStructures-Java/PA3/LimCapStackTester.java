public class LimCapStackTester extends junit.framework.TestCase
{
   public void testCapacity()
   {
      Stack12<String> test = new Stack12<String>(4);
      assertTrue(test.capacity() == 4);
   }
   public void testEquals()
   {
      Stack12<String> test = new Stack12<String>(4);
      test.push("hi");
      test.push("green");
      Stack12<String> test2 = new Stack12<String>(4);
      test2.push("hi");
      test2.push("green");
      
      assertTrue(test.equals(test2));
   }
   public void testPeek()
   {
      Stack12<String> test = new Stack12<String>(4);
      test.push("1023");
      assertTrue(test.peek() == "1023");
      test.push("Hi");
      test.push("lo");
      assertTrue(test.peek() == "lo");
      
   }
   public void testPop()
   {
      Stack12<String> test = new Stack12<String>(4);
      test.push("103");
      test.push("kdow");
      assertTrue(test.pop() == "kdow");
      test.push("hi");
      assertTrue(test.pop() == "hi");
   }
   public void testPush()
   {
      Stack12<String> test = new Stack12<String>(4);
      test.push("hi");
      assertTrue(test.pop() == "hi");
      assertTrue(test.size() == 0);
      test.push("hi");
      test.push("23");
      assertTrue(test.size() == 2);
   }
   public void testSize()
   {
      Stack12<String> test = new Stack12<String>(4);
      test.push("hi");
      test.push("bllop");
      test.push("green");
      assertTrue(test.size() == 3);
   }
   public static void main (String[] args)
   {
      junit.swingui.TestRunner.main(new String[] {"LimCapStackTester"});
   }
}
