public class Main {

    public static void main(String[] args) {
        int[] prices = new int[40];
        prices[0] = 1;
        prices[1] = 5;
        prices[2] = 8;
        prices[3] = 9;
        prices[4] = 10;
        prices[5] = 17;
        prices[6] = 17;
        prices[7] = 20;
        prices[8] = 24;
        prices[9] = 30;
        prices[10] = 32;
        prices[11] = 33;
        prices[12] = 37;
        prices[13] = 42;
        prices[14] = 48;
        prices[15] = 50;
        prices[16] = 51;
        prices[17] = 59;
        prices[18] = 65;
        prices[19] = 70;
        prices[20] = 71;
        prices[21] = 73;
        prices[22] = 74;
        prices[23] = 78;
        prices[24] = 79;
        prices[25] = 81;
        prices[26] = 85;
        prices[27] = 88;
        prices[28] = 89;
        prices[29] = 100;
        prices[30] = 101;
        prices[31] = 105;
        prices[32] = 110;
        prices[33] = 111;
        prices[34] = 112;
        prices[35] = 115;
        prices[36] = 116;
        prices[37] = 117;
        prices[38] = 118;
        prices[39] = 119;

        System.out.println("\nBottom-up approach");

        for(int i = 1; i <= prices.length; i++) {
            System.out.println("length " + i + ": " + BottomUp(prices, i));
        }
//        System.out.println("\nTop-Down approach");
//
//        for (int i = 1; i <= prices.length; i++) {
//            System.out.println("length " + i + ": " + CutRod(prices, i));
//        }
    }

    private static int CutRod(int[] p, int n) {
        if (n == 0) {
            return 0;
        }
        int result = -1;
        for (int i = 0; i < n; i++) {
            result = Math.max(result, p[i] + CutRod(p, n - i - 1));
        }
        return result;
    }

    private static int BottomUp(int[] p, int n) {
        int[] r = new int[n + 1];
        r[0] = 0;
        for (int j = 0; j < n; j++) {
            int q = -1;
            for (int i = 0; i <= j; i++) {
                q = Math.max(q, p[i] + r[j - i]);
            }
            r[j + 1] = q;
        }

        return r[n];
    }
}