import java.util.Map;

/**
 * Term.java
 * @
 * Overview: This class defines the class for Term. This term
 * is defined as a factor, a <term> * <factor> or an <term> / <factor>.
 * This class extends ASTNode, which stands for Abstract Syntax Tree. The
 * AST is built while parsing. The parse and eval methods of the abstract
 * class ASTNode are written in this class.
 * 
 * @author Jeremy Kao cs12wch
 */

public class Term extends ASTNode
{
   private char symbol;
   
   /**
    * Term(ASTNode n)
    * 
    * This one argument constructor is like a factory method, which
    * is used to build the Abstract Syntax Tree. Anything passed in
    * is passed to teh ASTNode constructor, which adds whatever
    * is passed in as a child.
    * 
    * @param ASTNode n - either Expr, Assignment, Operation, Term, Factor,
    *        Identifier, or Constant
    */
   private Term(ASTNode n)
   {
      super(n);
   }
   
   /**
    * Term(char c, Term t, Factor f)
    * 
    * This three argument constructor is like a factory method, which
    * is used to build the Abstract Syntax Tree. the identifier
    *  and the expression classes that are passed in
    * are passed to teh ASTNode constructor, which adds them
    * as children. This method also sets the symbol instance
    * variable to the character (an operator) passed in, which
    * is later used for eval.
    * 
    */
   private Term(char c, Term t, Factor f)
   {
      super(t,f);
      this.symbol = c;
   }
   
   /**
    * parse(String s)
    * 
    * This parse method is overwritten as required by the abstract class
    * ASTNode. Since Expr could be an assignment or operation, this
    * method calls both the parse methods of the operation and
    * assignment classes. 
    * 
    * @param String s - the string to be parsed.
    * @return Expr - a new expression object.
    * 
    */
   public static Term parse(String n)
   {
      String s = n.trim();
      Factor factor = Factor.parse(s);
      if (factor != null)
      {
         return new Term(factor);
      }
      for (int i = 0; i < s.length(); i++)
      {
         if (s.charAt(i) == '*')
         {
            String left = s.substring(0,i);
            String right = s.substring(i+1);
            
            Term term = Term.parse(left);
            if (term != null)
            {
               Factor f = Factor.parse(right);
               if (f != null)
               {
                  return new Term('*', term, f);
               }
            }
         }
         if (s.charAt(i) == '/')
         {
            String left = s.substring(0,i);
            String right = s.substring(i+1);
            
            Term term = Term.parse(left);
            if (term != null)
            {
               Factor f = Factor.parse(right);
               if (f != null)
               {
                  return new Term('/', term, f);
               }
            }
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
    * This method multiplies or divides the values get.
    * 
    * @return double, which is either a product, division, or constnat
    * 
    */
   public double eval(Map<String, Double> symtab)
   {
      double leftVal = getChild(0).eval(symtab);
      if (this.arity() == 1)
      {
         return leftVal;
      }
      double rightVal = getChild(1).eval(symtab);
      if (this.symbol == '*')
      {
         return leftVal * rightVal;
      }
      return leftVal / rightVal;
   }
}
