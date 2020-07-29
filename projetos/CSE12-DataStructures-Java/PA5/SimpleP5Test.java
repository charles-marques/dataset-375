/**
 * Do some simple P5 tests
 */
public class SimpleP5Test {

  public static void main(String args[]) {

    try {
      Expr e =  Expr.parse("2.0");
      Identifier i = Identifier.parse("xyz");
      Assignment a = Assignment.parse("a = 2.0");
      Operation c = Operation.parse("2.0");
      Constant x =  Constant.parse("3.0");
      Factor f =  Factor.parse("4.0");
      Term t =  Term.parse("5.0");
      double val = e.eval(new java.util.HashMap<String,Double>());
      if(val == 2.0) {
	System.exit(0);
      }
    } catch (Throwable t) {}

    System.out.println("Problem parsing or evaluating...");
    System.exit(-1);

  }

}
