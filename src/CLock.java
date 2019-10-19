import java.io.*;
import java.math.*;
import java.text.*;
import java.util.*;
import java.util.regex.*;

public class CLock {

    /*
     * Complete the timeConversion function below.
     */
    static String timeConversion(String s) {
        /*
         * Write your code here.
         */
        String result=s;
        Pattern p = Pattern.compile("(\\d\\d):(\\d\\d):(\\d\\d)(\\w\\w)");
        Matcher m = p.matcher(s);
        boolean pm=false;
        if(m.matches()){
            if(m.group(4).equals("PM")){
                pm=true;
            }else{
                pm=false;
            }
            int hour = Integer.parseInt(m.group(1));
            String min = m.group(2);
            String sec = m.group(3);

            if(pm){
                if(hour!=12)
                    hour +=12;
                else
                    hour = 12;

            }else{
                if(hour==12){
                    hour=0;
                }
            }
                result = String.format( "%02d" ,(hour)%24) +":"+min+":"+sec;

        }

        return result;
    }

    private static final Scanner scan = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
       String s="12:40:22AM";
        String result = timeConversion(s);
System.out.println(result);
    }
}
