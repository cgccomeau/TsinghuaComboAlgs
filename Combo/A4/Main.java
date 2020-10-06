import java.util.Scanner;

class Main {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int n = scan.nextInt();
        int k = scan.nextInt();
        int result = 0;
        int[][] oof = new int[n][k];
        for (int i = 0; i < n; i ++) {
            for (int j = 0; j < k; j++) {
                if (j == 0 || i == j) {
                    oof[i][j] = 1;
                } else if (i > 0 && j > 0 && j < i) {
                    int addend = 0;
                    if (i - j - 1 >= 0) {
                        addend = oof[i - j - 1][j];
                    }
                    oof[i][j] = (oof[i - 1][j - 1] + addend) % 1000000007;
                }

                if (i == n - 1) {
                    result = (result + oof[i][j]) % 1000000007;
                }
                System.out.print(oof[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println(result);
    }
}