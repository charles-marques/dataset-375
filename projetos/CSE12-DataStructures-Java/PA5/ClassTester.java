import java.util.HashMap;
/*
 *             ,'``.._   ,'``.
              :,--._:)\,:,._,.:       All Glory to
              :`--,''   :`...';\      the HYPNO TOAD!
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
public class ClassTester extends junit.framework.TestCase
{
   public HashMap<String, Double> symtab = new HashMap<String, Double>();
   public void testExprParse()
   {
      Expr e2 = Expr.parse("(1) + (2)");
      assertTrue(e2.eval(symtab) == 3);
      Expr e3 = Expr.parse("(1 + ((1) + (2))) * 5");
      assertTrue(e3.eval(symtab) == 20);
      Expr e4 = Expr.parse(" (1+2)*(3-4)/5 ");
      assertTrue(e4.eval(symtab) == -0.6);
      Expr e5 = Expr.parse("(((3+2)))");
      assertTrue(e5.eval(symtab) == 5);
      Expr e51 = Expr.parse("((1/(3+2)+1))");
      assertTrue(e51.eval(symtab) == 1.2);
      Expr e6 = Expr.parse("5*(3+2)+(4                          -3)");
      assertTrue(e6.eval(symtab) == 26);
      Expr e7 = Expr.parse(" (3+2) + (4-3) * 5 "); 
      assertTrue(e7.eval(symtab) == 10);
      Expr e8 = Expr.parse(" (4*(((3+2+1+3+5/4))/2)) "); //20.5 
      assertTrue(e8.eval(symtab) == 20.5);
      Expr e9 = Expr.parse(" (((3+2) + (3-1)) + 1) *5 " ); //40.0
      assertTrue(e9.eval(symtab) == 40.0);
      Expr e10 = Expr.parse(" 5 * (p=2) * (( (3+2) + (3-1) ) + 1) "); //80.0
      assertTrue(e10.eval(symtab) == 80.0);
      Expr e11 = Expr.parse("5 + (3+2)"); //10
      assertTrue(e11.eval(symtab) == 10.0);
      Expr e12 =  Expr.parse("1 / 10 - -3.0"); //3.1
      assertTrue(e12.eval(symtab) == 3.1);
      Expr e13 =  Expr.parse("(((((-1e3)))))"); // -1000.0
      assertTrue(e13.eval(symtab) == -1000.0);
      Expr e14 =  Expr.parse("x = y = z = 10"); // 10
      assertTrue(e14.eval(symtab) == 10.0);
      try
      {
         Expr e15 =  Expr.parse("2 + (a = 2) + y"); // RuntimeException
      }
      catch(RuntimeException e)
      {
         assertTrue(true);
      }
      try
      {
         Expr e16 =  Expr.parse("pi * r * r = area"); // RuntimeException
      }
      catch(RuntimeException e)
      {
         assertTrue(true);
      }
      Expr e18 =  Expr.parse("( (2+3/2 + ((3-2)/2) +2) ) / 2 * 2");   // 6    
      assertTrue(e18.eval(symtab) == 6.0);
   }
   public static void main(String[] args)
   {
      junit.swingui.TestRunner.main(new String[] {"ClassTester"});
   }

}
