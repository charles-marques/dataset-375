import java.util.Map;

/**
 * Operation.java
 * @
 * Overview: This class defines the class for Operation. This operation
 * is defined as a term, an <operation> + <term> or an <operation> - <term>.
 * This class extends ASTNode, which stands for Abstract Syntax Tree. The
 * AST is built while parsing. The parse and eval methods of the abstract
 * class ASTNode are written in this class.
 * 
 * @author Jeremy Kao cs12wch
 */

public class Operation extends ASTNode
{
   private char symbol;
   
   /**
    * Operation(ASTNode n)
    * 
    * This one argument constructor is like a factory method, which
    * is used to build the Abstract Syntax Tree. Anything passed in
    * is passed to teh ASTNode constructor, which adds whatever
    * is passed in as a child.
    * 
    * @param ASTNode n - either Expr, Assignment, Operation, Term, Factor,
    *        Identifier, or Constant
    */
   private Operation(ASTNode n)
   {
      super(n);
      
   }
   
   /**
    * Operation(char s, Operation o, Term t)
    * 
    * This three argument constructor is like a factory method, which
    * is used to build the Abstract Syntax Tree. the identifier
    *  and the expression classes that are passed in
    * are passed to teh ASTNode constructor, which adds them
    * as children. It also sets the symbol (an operator), which
    * is later used for eval method.
    * 
    */
   private Operation(char s, Operation o, Term t)
   {
      super(o,t);
      this.symbol = s;
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
   public static Operation parse(String n)
   {
      //clears whitespace from both sides of word
      String s = n.trim();
      Term term = Term.parse(s);
      if (term != null)
      {
         return new Operation(term);
      }
      for (int i = 0; i < s.length(); i++)
      {
         //checks for addition
         if (s.charAt(i) == '+')
         {
            //if there is a positively signed number, the left side
            // should parse incorrectly and throw StringIndexOutOfBounds
            //therefore skip parsing when this incident is found.
            try
            {
               String left = s.substring(0, i);
               String right = s.substring(i+1);
               Operation oper = Operation.parse(left);
               if (oper != null)
               {
                  Term t = Term.parse(right);
                  if (t != null)
                  {
                     return new Operation('+',oper,t);
                  }
                  
               }
            }
            catch(StringIndexOutOfBoundsException e)
            {
               continue;
            }
         }
         //checks for subtraction
         if (s.charAt(i) == '-')
         {
            //if there is a negatively signed number, the left side
            // should parse incorrectly and throw StringIndexOutOfBounds
            // therefore, skip the parsing if this incident is found.
            try
            {
               String left = s.substring(0, i);
               String right = s.substring(i+1);
               
               Operation oper = Operation.parse(left);
               if (oper != null)
               {
                  Term t = Term.parse(right);
                  if (t != null)
                  {
                     return new Operation('-',oper,t);
                  }
                  
               }
            }
            catch (StringIndexOutOfBoundsException e)
            {
               continue;
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
    * This method adds or subtracts the values get.
    * 
    * @return double, which is either a sum, difference or constnat
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
      if (this.symbol == '+') //addition.
      {
         return leftVal + rightVal;
      }
      return leftVal - rightVal;
   }
}
