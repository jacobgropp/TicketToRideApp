package StringProcessor;

/**
 * Created by jakeg on 9/14/2018.
 */

public class StringProcessor implements IStringProcessor{

   public String toLowerCase(String s){
       return s.toLowerCase();
   }

   public String trim(String s){
       return s.trim();
   }

   public Double parseDouble(String s){
       return Double.parseDouble(s);
   }
}
