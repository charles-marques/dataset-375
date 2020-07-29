import java.util.Map;

/**
 * Assignment.java
 * @
 * Overview: This class defines the class for Assignment. This assignment
 * is defined as an identifier + "=" + an expression.
 * This class extends ASTNode, which stands for Abstract Syntax Tree. The
 * AST is built while parsing. The parse and eval methods of the abstract
 * class ASTNode are written in this class.
 * 
 * @author Jeremy Kao cs12wch
 */

public class Assignment extends ASTNode
{
   private char symbol;
   
   /**
    * Assignment(char c, identifier i, expr e)
    * 
    * This three argument constructor is like a factory method, which
    * is used to build the Abstract Syntax Tree. the identifier
    *  and the expression classes that are passed in
    * are passed to teh ASTNode constructor, which adds them
    * as children.
    * 
    */
   private Assignment(char c, Identifier i, Expr e)
   {
      //calls the constructor of ASTNode
      super(i,e);
      //sets character, which is an operater sign to symbol
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
   public static Assignment parse(String n)
   {
      String s = n.trim();
      for (int i = 0; i < s.length(); i++)
      {
         if (s.charAt(i) == '=')
         {
            String left = s.substring(0, i);
            String right = s.substring(i+1);
            Identifier ident = Identifier.parse(left);
            if (ident != null)
            {
              Expr expr = Expr.parse(right);
              if (expr != null)
              {
                 return new Assignment('=', ident, expr);
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
    * get and put values and compute correct values. It uses
    * a HashMap as a backing store for key, value pairs.
    * 
    * @return double, which is the value put into the hashmap.
    * 
    */
   public double eval(Map<String,Double> symtab)
   {
      String key = getChild(0).toString();
      Double val = getChild(1).eval(symtab);
      symtab.put(key, val);
      return val;
   }
}
