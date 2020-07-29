import java.util.Map;
/**
 * Identifier.java
 * @
 * Overview: This class defines the class for Identifier. This Identifier
 * is defined as a java-valid string that is to be put in the hashmap.
 * This class extends ASTNode, which stands for Abstract Syntax Tree. The
 * AST is built while parsing. The parse and eval methods of the abstract
 * class ASTNode are written in this class.
 * 
 * @author Jeremy Kao cs12wch
 */

public class Identifier extends ASTNode
{
   private String idName;
   
   /**
    * Identifier(String s)
    * 
    * This one argument constructor is sets the string parameter
    * as the key value to be put into the hashtable. 
    * 
    * @param String s - the name of the key to be used as variable name
    */
   private Identifier(String s)
   {
      this.idName = s;
   }
   
   /**
    * parse(String n)
    * 
    * This parse method is overwritten as required by the abstract class
    * ASTNode. Identifier is a leaf of the abstract syntax tree, so 
    * it does not call the parse method of any other class. In this
    * method, we check if all the characters of the identifier are
    * valid in java.
    * 
    * @param String s - the string to be parsed.
    * @return Expr - a new expression object.
    * 
    */
   public static Identifier parse(String n)
   {
      String s = n.trim();
      if (Character.isJavaIdentifierStart(s.charAt(0)))
      {
         for (int i = 0; i < s.length(); i++)
         {
            if (!Character.isJavaIdentifierPart(s.charAt(i)))
            {
               return null;
            }
         }
         return new Identifier(s);
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
    * This method gets an identifier and returns it.
    * 
    * @throws RuntimeException - if the identifier is a invalid in java.
    * @return double
    * 
    */
   public double eval(Map<String, Double> symtab)
   {
      Double val = symtab.get(this.idName);
      if (val == null)
      {
         throw new RuntimeException("Unidentified variable: " + this.idName);
      }
      return val;
   }
   
   /**
    * toString()
    * 
    * This toString method overides that provided by the object class.
    * It basically returns the identification name that the parent needs
    * 
    * @return String - the identification name
    * 
    */
   public String toString()
   {
      return this.idName;
   }
}
