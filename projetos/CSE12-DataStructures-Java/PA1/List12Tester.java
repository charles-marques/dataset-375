import java.util.List;


public class List12Tester extends junit.framework.TestCase
{
   /* public boolean add(E element) */
   public void testAddContains()
   {
      List12<String> list = new List12<String>();
      list.add("foo");
      list.add("bar");
      assertTrue(list.contains("foo"));
      assertTrue(list.contains("bar"));
      assertFalse(list.contains("rick"));
   }
   public void testAddPosition()
   {
      List12<Integer> list = new List12<Integer>();
      for (int i = 0; i < 100; i++)
      {
         list.add(i);
      }
      assertTrue(list.get(99) == 99);
      assertFalse(list.get(10) == 11);
   }
   public void testAddSize()
   {
      List12<String> list = new List12<String>();
      list.add("abc");
      assertTrue(list.size() == 1);
   }
   /* public void add(int index, E element) */
   public void testAddParamContains()
   {
      List12<Integer> list = new List12<Integer>();
      list.add(0,100);
      list.add(1,101);
      list.add(2,102);
      assertTrue(list.contains(100) && list.get(0) == 100);
      assertTrue(list.contains(101) && list.get(1) == 101);
      assertTrue(list.contains(102) && list.get(2) == 102);
      assertFalse(list.contains(104) || list.get(2) == 100);
   }
   public void testAddParamPosition()
   {
      List12<Integer> list = new List12<Integer>();
      list.add(0,100);
      list.add(1,101);
      assertTrue(list.indexOf(100) == 0);
      assertTrue(list.indexOf(101) == 1);
      list.add(0,103);
      assertTrue(list.indexOf(103) == 0);
      assertTrue(list.indexOf(100) == 1);
      assertTrue(list.indexOf(101) == 2);
   }
   public void testAddParamSize()
   {
      List12<Integer> list = new List12<Integer>();
      list.add(0,100);
      list.add(1,101);
      list.add(2,102);
      assertTrue(list.size() == 3);
   }
   
   /* public void clear() */
   public void testClear()
   {
      List12<String> list = new List12<String>();
      list.add("hello");
      list.add("world");
      list.clear();
      assertTrue(list.size() == 0);
   }
   
   /* public boolean contains(Object o) */
   public void testContainsReturn()
   {
      List12<Integer> list = new List12<Integer>();
      list.add(10);
      list.add(11);
      list.add(12);
      assertTrue(list.contains(10));
      assertFalse(list.contains(14));
   }
   
   /* public boolean equals(Object o) */
   public void testEquals()
   {
      List12<String> list1 = new List12<String>();
      list1.add("peter");
      list1.add("hung");
      List12<String> list2 = new List12<String>();
      list2.add("peter");
      list2.add("hung");
      assertTrue(list1.equals(list2));
      assertTrue(list1 instanceof List && list2 instanceof List);
      assertTrue(list1.size() == list2.size());
      for (int i = 0; i < list1.size(); i++)
      {
         assertTrue(list1.get(i) == list2.get(i));
      }
      
   }
   
   /* public E get(int index) */
   public void testGet()
   {
      List12<Integer> listInt = new List12<Integer>();
      listInt.add(12);
      assertTrue(listInt.get(0) == 12);
      
      List12<String> listStr = new List12<String>();
      listStr.add("HelloWorld");
      assertTrue(listStr.get(0) == "HelloWorld");
   }
   
   /* public int indexOf(Object o) */
   public void testIndexOf()
   {
      List12<Integer> list = new List12<Integer>();
      list.add(50);
      list.add(51);
      list.add(50);
      assertTrue(list.indexOf(50) == 0);
      assertTrue(list.indexOf(52) == -1);
   }
   
   /* public Iterator<E> iterator() */
   public void testIterator()
   {
      List12<String> list = new List12<String>();
      list.add("cool");
      list.add("beans");
      list.add("mo");
      assertTrue((list.iterator()).hasNext());
      assertTrue((list.iterator()).next() == "beans");
      list.iterator().remove();
      assertTrue(list.size() == 2);
   }
   /* public boolean isEmpty() */
   public void testIsEmpty()
   {
      List12<Integer> list = new List12<Integer>();
      assertTrue(list.isEmpty());
      list.add(12);
      assertFalse(list.isEmpty());
   }
   
   /* public boolean remove(Object o) */
   public void testRemoveContains()
   {
      List12<String> list = new List12<String>();
      list.add("hello");
      list.add("world");
      list.remove("hello");
      assertTrue(!list.contains("hello"));
      assertTrue(list.contains("world"));
   }
   public void testRemoveSize()
   {
      List12<String> list = new List12<String>();
      list.add("lizard");
      list.add("please");
      assertTrue(list.size() == 2);
      list.remove("lizard");
      assertTrue(list.size() == 1);
   }
   public void testRemoveShift()
   {
      List12<String> list = new List12<String>();
      list.add("lizard");
      list.add("please");
      list.add("lol");
      assertTrue(list.indexOf("lizard") == 0);
      assertTrue(list.indexOf("please") == 1);
      assertTrue(list.indexOf("lol") == 2);
      
      list.remove("please");
      assertTrue(list.indexOf("lizard") == 0);
      assertTrue(list.indexOf("lol") == 1);
   }
   public void testRemoveReturn()
   {
      List12<String> list = new List12<String>();
      list.add("lizard");
      list.add("please");
      assertTrue(list.remove("please"));
   }
   
   /* public E set(int index, E element) */
   public void testSetPosition()
   {
      List12<Integer> list = new List12<Integer>();
      list.add(44);
      list.add(55);
      list.add(66);
      list.set(0,77);
      assertTrue(list.get(0) == 77);
      assertTrue(list.get(1) == 55);
   }
   public void testSetContains()
   {
      List12<Integer> list = new List12<Integer>();
      list.add(44);
      list.add(55);
      list.add(66);
      list.set(0,77);
      assertTrue(list.contains(77));
      assertTrue(list.contains(55));
      assertFalse(list.contains(44));
   }
   public void testSetSize()
   {
      List12<Integer> list = new List12<Integer>();
      list.add(44);
      list.add(55);
      list.add(66);
      assertTrue(list.size() == 3);
      
      list.set(0,77);
      assertTrue(list.size() == 3);
   }
   public void testSetReturn()
   {
      List12<Integer> list = new List12<Integer>();
      list.add(44);
      list.add(55);
      list.add(66);
      assertTrue(list.set(0,77) == 44);
      assertTrue(list.set(1,99) == 55);
   }
   
   /* public int size() */
   public void testSizeReturn()
   {
      List12<String> list = new List12<String>();
      list.add("This");
      list.add("is");
      list.add("foo");
      assertTrue(list.size() == 3);
   }
   /*public void testSizeExceed()
   {
      List12<Integer> list = new List12<Integer>();
      for (int i = 0; i < Integer.MAX_VALUE; i++)
      {
         list.add(i);
      }
      list.add(59);
      list.add(69);
      list.add(79);
      list.add(89);
      list.add(99);
      assertTrue(list.size() == Integer.MAX_VALUE);
      assertFalse(list.size() == (Integer.MAX_VALUE + 5));
   }*/
   public static void main(String[] args)
   {
      junit.swingui.TestRunner.main(new String[] {"List12Tester"});
   }
}

