import java.util.Map;
/**
 * Constant.java
 * @
 * Overview: This class defines the class for Factor. This constant is
 * any number that can  be parsed without throwing NumberFormatException().
 * This class extends ASTNode, which stands for Abstract Syntax Tree. The
 * AST is built while parsing. The parse and eval methods of the abstract
 * class ASTNode are written in this class.
 * 
 * @author Jeremy Kao cs12wch
 */

public class Constant extends ASTNode
{
   private double value;
   
   /**
    * Constant(double val)
    * 
    * This one argument constructor is sets the double parameter
    * as the value (corresponding to a key) to be put into the hashtable. 
    * 
    * @param double val - the value of the double to be used as value in hashmap
    */
   private Constant(double val)
   {
      this.value = val;
   }
   
   /**
    * parse(String n)
    * 
    * This parse method is overwritten as required by the abstract class
    * ASTNode. Identifier is a leaf of the abstract syntax tree, so 
    * it does not call the parse method of any other class. In this
    * method, we take a string and call parseDouble on it and catch
    * a NumberFormatException if possible
    * 
    * @param String s - the string to be parsed.
    * @return Constnat - a new constant object.
    * 
    */
   public static Constant parse(String n)
   {
      String s = n.trim();
      if (s == null)
      {
         s = s.trim();
      }
      if (s.length() == 0)
      {
         return null;
      }
      try
      {
         double d = Double.parseDouble(s);
         return new Constant(d);
      }
      catch(NumberFormatException e)
      {
         return null;
      }
   }
   
   /**
    * eval(Map<String, Double> symtab)
    * 
    * This eval method is required as part of the abstract
    * class ASTNode. Eval uses the abstract syntax tree to
    * get values and compute correct values. It uses
    * a HashMap as a backing store for key, value pairs.
    * This method returns the value that is to be added to 
    * the hashtable.
    * 
    * @return double - the value of the constant
    * 
    */
   public double eval(Map<String, Double> symtab)
   {
      return this.value;
   }
   
   
}
/*

               ,'``.._   ,'``.
              :,--._:)\,:,._,.:       Thanks for grading the Programming
              :`--,''   :`...';\      assignments! This is the last one!
               `,'       `---'  `.
               /                 :
              /                   \
            ,'                     :\.___,-.
           `...,---'``````-..._    |:       \
             (                 )   ;:    )   \  _,-.
              `.              (   //          `'    \
               :               `.//  )      )     , ;
             ,-|`.            _,'/       )    ) ,' ,'
            (  :`.`-..____..=:.-':     .     _,' ,'
             `,'\ ``--....-)='    `._,  \  ,') _ '``._
          _.-/ _ `.       (_)      /     )' ; / \ \`-.'
         `--(   `-:`.     `' ___..'  _,-'   |/   `.)
             `-. `.`.``-----``--,  .'
               |/`.\`'        ,','); SSt
                   `         (/  (/

*/