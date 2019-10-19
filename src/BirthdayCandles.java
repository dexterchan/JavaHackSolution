import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.function.Supplier;
import java.util.regex.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class BirthdayCandles {

    // Complete the birthdayCakeCandles function below.
    static int birthdayCakeCandles(int[] ar) {
        Supplier<Stream<Integer>> streamSupplier = () -> Arrays.stream(ar).boxed();

        final int m=streamSupplier.get().max((a,b)->a-b).get();

        Stream<Integer> fl = streamSupplier.get().filter(l -> l==m);
        return fl.toArray(Integer[]::new).length;
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        int[] ar = new int[]{3,2,1,3};

        int result = birthdayCakeCandles(ar);
        System.out.println(result);

    }
}
