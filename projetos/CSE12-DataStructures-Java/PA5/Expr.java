/**
   * Expr.java
   * @
   * Overview: This class defines the class for Expression. A user types an
   * expression, which could either be an assignment or an operation.
   * This class extends ASTNode, which stands for Abstract Syntax Tree. The
   * AST is built while parsing. The parse and eval methods of the abstract
   * class ASTNode are written in this class.
   * 
   * @author Jeremy Kao cs12wch
   */

import java.util.Map;

public class Expr extends ASTNode
{
   /**
    * Expr(ASTNode x)
    * 
    * This one argument constructor is like a factory method, which
    * is used to build the Abstract Syntax Tree. Anything passed in
    * is passed to teh ASTNode constructor, which adds whatever
    * is passed in as a child.
    * 
    * @param ASTNode x - either Expr, Assignment, Operation, Term, Factor,
    *        Identifier, or Constant
    */
   public Expr(ASTNode x)
   {
      //Calls constructor of ASTNode
      super(x);
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
   public static Expr parse(String s)
   {
      //Since Expr could be Assignment
      Assignment ass = Assignment.parse(s);
      if (ass != null)
      {
         return new Expr(ass);
      }
      //Since Expr could be an Operation
      Operation o = Operation.parse(s);
      if (o != null)
      {
         return new Expr(o);
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
    * 
    * @throws IllegalStateException if Expr has more than 1 child.
    * @return double
    * 
    */
   public double eval(Map<String, Double> symtab)
   {
      //this.arity returns the number of children.
      if (this.arity() > 1)
      {
         throw new IllegalStateException();
      }
      return getChild(0).eval(symtab);
   }
}
