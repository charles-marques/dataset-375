import java.util.ArrayList;

public class TimeList
{
   private static List12<Integer> linkedList = new List12<Integer>();
   private static ArrayList<Integer> arrList = new ArrayList<Integer>();
   private static double[] timeArr = new double[48];
   private static int k = 0;
  public static void main(String[] args)
  {
     
    if(args.length !=4)
    {
      System.out.println("Usage: java TimeList [linked OR array] [front OR back]");
      System.exit(1);
    }
 
    String type = args[0];
    String orientation = args[1];

    
    for (int i = 2000; i <= 50000; i += 1000)
    {
       System.gc();
       long start = System.nanoTime();
       
       if (orientation == "front" && type == "list")
       {
          linkedList.add(0,i);
       }
       if (orientation == "back" && type == "list")
       {
          linkedList.add(i);
       }
       if (orientation == "front" && type == "array")
       {
          arrList.add(0,i);
       }
       if (orientation == "back" && type == "array")
       {
          arrList.add(i);
       }
       
       long end = System.nanoTime();
       double seconds = (end - start) / 1.0e9;
       timeArr[k] = seconds;
       k++;
       computeAndDisplay(timeArr, i);
    }
  }
  public static void computeAndDisplay(double arr[], int num)
  {
    double sum = 0;
    for (double sec : arr)
    {
      sum += sec;
    }
    
    double mean = sum/arr.length;
    
    double sumSquared = 0;
    for (double sec: arr)
    {
      sumSquared += sec * sec;
    }
    double sd = Math.sqrt((sumSquared/arr.length) - (mean * mean));
    System.out.format("%d  %.6f\t %.6f\n", num, mean, sd);
  }
}

