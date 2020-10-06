import java.util.Scanner; //division & mod
import java.util.ArrayList;

class Main {


    private static ArrayList<Integer> result = new ArrayList<>();

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        long n = (long) scan.nextLong();
        long k = (long) scan.nextLong() - 1;
        mobile(n, k);
        for(int num: result) {
            System.out.print(num + " ");
        }
        //System.out.println(result);
    }

    private static void specialCase(long n, long k) {
        if (n == 1) {
            result.add(1);
            return;
        } else {
            if (k % 2 == 0) {
                result.add(1);
                result.add(2);
            } else {
                result.add(2);
                result.add(1);
            }
        }
    }

    private static void mobile(long n, long k) {
        long pattern = k/n;
        if (n <= 2) {
            specialCase(n, k);
            return;
        }

        if (pattern % 2 == 0) {
            mobile(n - 1, pattern);
            result.add((int) (n - (k % n + 1)), (int) n);
        } else {
            mobile(n - 1, pattern);
            result.add((int) (k % n), (int) n);
        }
        return;
    }
}