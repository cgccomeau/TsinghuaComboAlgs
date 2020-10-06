import java.util.Scanner;

class Main {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int n = scan.nextInt();
        int m = scan.nextInt();
        String[] arr = new String[n + 1];
        int[] cumlen = new int[n + 1];
        for (int i = 1; i < arr.length; i++) {
            arr[i] = scan.next();
            if (i == 1) {
                cumlen[i] = arr[i].length();
            } else if (i > 1) {
                cumlen[i] += cumlen[i - 1] + arr[i].length();
            }
        }
        int[] c = new int[n + 1]; // cost array
        int[] karr = new int[n + 1]; // optimal subproblem array
        for (int i = n; i >= 1; i--) {
            if (n - i + cumlen[n] - cumlen[i - 1] <= m) {
                c[i] = 0;
                karr[i] = i;
            } else {
                int min = Integer.MAX_VALUE;
                int tempk = -1;
                for (int k = i + 1; k <= n; k++) {
                    int a = m - (k - 1 - i) - cumlen[k - 1] + cumlen[i - 1];
                    if (a >= 0) {
                        min = Math.min(min, a * a * a + c[k]);
                        tempk = k;
                    }
                }
                karr[i] = tempk;
                c[i] = min;
            }
        }
        System.out.println(c[1]);

        for (int i = 0; i <= n; i++) {
            System.out.println("karr " + i + " " + karr[n]);
        }
    }
}