/**
 * Do a couple of simple tests on Array12, Stack12, Queue12
 */
public class SimpleP3Test {

  public static void main(String args[]) {
    LimCapList<Integer> a =  new Array12<Integer>(30);
    if(a.capacity() != 30) {
      System.err.println("Array12: problem with capacity().");
      System.exit(-1);
    }

    LimCapQueue<Integer> q =  new Queue12<Integer>(30);
    if(q.capacity() != 30) {
      System.err.println("Queue12: problem with capacity().");
      System.exit(-1);
    }

    LimCapStack<Integer> s =  new Stack12<Integer>(30);
    if(s.capacity() != 30) {
      System.err.println("Stack12: problem with capacity().");
      System.exit(-1);
    }

    System.exit(0);

  }

}
