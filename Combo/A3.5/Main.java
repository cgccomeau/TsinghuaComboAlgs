import java.util.Scanner;
import java.util.ArrayList;
//import java.util.SortedSet;
//import java.util.TreeSet;


class Main {

    private static ArrayList<Integer> initial = new ArrayList<>();
    private static ArrayList<Integer> result = new ArrayList<>();
    private static ArrayList<Integer> remainingSet = new ArrayList<>();

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        long n = (long) scan.nextLong();
        long a = (long) scan.nextLong();
        for (int i = 0; i < n; i++) {
            initial.add(scan.nextInt());
            remainingSet.add(i + 1);
        }
        long newK = (long) findK() + a;
        generateK(newK, n);
        for(int num: result) {
            System.out.print(num + " ");
        }
    }

    private static int specialCase() {
        if (initial.get(0) == 1) {
            return 0;
        } else {
            return 1;
        }
    }

    private static long findK() {
        if (initial.size() <= 2) {
            return specialCase();
        } else {
            long firstPosition = (long) initial.get(0);
            long firstPositionRemainder = (long) initial.get(0) - 1;
            initial.remove(0);
            long postLeftover = (long) initial.size();
            for(int i = 0; i < postLeftover; i++) {
                if (initial.get(i) > firstPosition) {
                    initial.set(i, initial.get(i) - 1);
                }
            }

            return Factorial(postLeftover) * firstPositionRemainder + findK();
        }
    }

    private static long Factorial(long n) {
        if (n <= 1) {
            return 1;
        } else {
            return n * Factorial(n - 1);
        }
    }

    private static void generateK(long newk, long n) {
        if (n == 0) {
            return;
        }
        long position = (long) newk / Factorial(n - 1);
        newk = newk % Factorial(n - 1);
        result.add(remainingSet.get((int)position));
        remainingSet.remove((int)position);
        generateK(newk, n - 1);
    }
}