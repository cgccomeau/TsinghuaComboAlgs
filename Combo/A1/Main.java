import java.util.Scanner;
/*
    Second Programming Assignment for Combo & Alg design
    Calculating Binomial Coefficients
    2019.9.20
    Charlie Comeau / 2019403371
*/
class Main {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int m = scan.nextInt();
        int n = scan.nextInt();
        System.out.println((combo(m, n)));
    }

    public static int combo(int m, int n) {
        int C[] = new int[n + 1];
        C[0] = 1;

        for (int i = 1; i <= m; i++) {
            for (int j = Math.min(i, n); j > 0; j--) {
                C[j] = (C[j] + C[j - 1]) % 1000000007;
            }
        }

        return C[n];
    }
}