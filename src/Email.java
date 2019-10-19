import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Stream;

public class Email {


    public static List<List<Integer>> getEmailThreads(List<String> emails) throws Exception{

        final List<String> emailSet=new ArrayList<>();
        final List<Integer> emailCount = new ArrayList<>();
        final List<List<Integer>> output = new ArrayList<>();
        // Write your code here
        for (String e : emails){

                int p = e.indexOf(", ");
                String email1 = e.substring(0,p);
                String remain = e.substring(p+2);
                p=remain.indexOf(", ");
                String email2 = remain.substring(0,p);
                remain = remain.substring(p+2);
                p=remain.lastIndexOf("---");
                p=p;
                if(p==-1){
                    emailSet.add(remain);
                    emailCount.add(1);
                    List<Integer> o = new ArrayList<>();
                    o.add(emailSet.size());
                    o.add(1);
                    output.add(o);
                }else{
                    String originalEmail = remain.substring(p+3);
                    int address = emailSet.indexOf(originalEmail);
                    int cnt=emailCount.get(address);
                    cnt++;
                    emailCount.set(address, cnt);
                    List<Integer> o = new ArrayList<>();
                    o.add(address+1);
                    o.add(cnt);
                    output.add(o);
                }
        }
        //throw new RuntimeException();
        return output;
    }

    public static void main(String [] args)throws Exception{

        String[] emails = {
        "c@gmail.com, abc@gmail.com, did you take a look at the event?",
                "abc@gmail.com, x@gmail.com, hello x, how are you?",
        "x@gmail.com, abc@gmail.com, i am great. how are you?---hello x, how are you?"};

        Stream<String> s=Arrays.stream(emails).sorted((a, b)->a.compareTo(b));

        List<List<Integer>> ret = getEmailThreads(Arrays.asList(emails));
        System.out.println(ret);
    }
}
