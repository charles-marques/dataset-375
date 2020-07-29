import java.util.Map;

/**
 * Factor.java
 * @
 * Overview: This class defines the class for Factor. This Factor
 * is defined as a constant, an identifer or an expression in parentheses.
 * This class extends ASTNode, which stands for Abstract Syntax Tree. The
 * AST is built while parsing. The parse and eval methods of the abstract
 * class ASTNode are written in this class.
 * 
 * @author Jeremy Kao cs12wch
 */

public class Factor extends ASTNode
{
   /**
    * Factor(ASTNode a)
    * 
    * This one argument constructor is like a factory method, which
    * is used to build the Abstract Syntax Tree. Anything passed in
    * is passed to the ASTNode constructor, which adds whatever
    * is passed in as a child.
    * 
    * @param ASTNode a - either Expr, Assignment, Operation, Term, Factor,
    *        Identifier, or Constant
    */
   private Factor(ASTNode a)
   {
      super(a);
   }
   
   /**
    * parse(String s)
    * 
    * This parse method is overwritten as required by the abstract class
    * ASTNode. Since Factor could be a constant, identifier, or experession
    * surrounded by parenthesis, this
    * method calls both the parse methods of the constant class, the
    * identifier class, and the Expr class. 
    * 
    * @param String s - the string to be parsed.
    * @return Factor - a new factor object.
    * 
    */
   public static Factor parse(String n)
   {
      String s = n.trim();
      Constant c = Constant.parse(s);
      if (c != null)
      {
         return new Factor(c);
      }
      Identifier i = Identifier.parse(s);
      if (i != null)
      {
         return new Factor(i);
      }
      //checking for expression surrounded by parentheses.
      if (s.charAt(0) == '(' && s.charAt(s.length() - 1) == ')')
      {
         System.out.println(s.substring(1,s.length() - 1));
         Expr e = Expr.parse(s.substring(1,s.length() - 1));
         if (e != null)
         {
            return new Factor(e);
         }
      }
      return null;
   }
   /**
    * eval(Map<String, Double> symtab)
    * 
    * This eval method is required as part of the abstract
    * class ASTNode. Eval uses the abstract syntax tree to
    * get values and compute correct values. It uses
    * a HashMap as a backing store for key, value pairs.
    * This method passes the hashmap to the child.
    * 
    * @return double
    * 
    */
   public double eval(Map<String, Double> symtab)
   {
      return getChild(0).eval(symtab);
   }
}
