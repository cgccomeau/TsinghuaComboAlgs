import java.util.Scanner;

class Main {

    private static long[] p;

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int n = scan.nextInt();
        long lower = scan.nextLong();
        long upper = scan.nextLong();
        p = new long[n];
        p[0] = scan.nextInt();
        for (int i = 1; i < n; i++) {
            p[i] = scan.nextInt() + p[i - 1];
        }

        int total = range(0, p.length - 1, lower, upper);
        System.out.println(total);
    }

    private static int range(int start, int end, long lower, long upper) {
        int mid = (start + end) / 2;

        if (start == end) {
            if (p[start] <= upper && p[end] >= lower) {
                return 1;
            } else {
                return 0;
            }
        }

        int total = range(start, mid, lower, upper) + range(mid + 1, end, lower, upper);
        int lowerIndex = start;
        int upperIndex = start;
        for(int i = mid + 1; i <= end; i++) {
            while (lowerIndex < mid + 1 && p[i] - p[lowerIndex] > upper) {
                lowerIndex++;
            }
            while (upperIndex < mid + 1 && p[i] - p[upperIndex] >= lower) {
                upperIndex++;
            }
            total += (upperIndex - lowerIndex);
        }

        long[] leftP = new long[mid - start + 1];
        long[] rightP = new long[end - mid];
        for(int i = 0, left = start; i < leftP.length; i++, left++) {
            leftP[i] = p[left];
        }
        for(int i = 0, right = mid + 1; i < rightP.length; i++, right++) {
            rightP[i] = p[right];
        }

        int i = start;
        int leftIndex = 0;
        int rightIndex = 0;
        while(i < p.length && (leftIndex < leftP.length || rightIndex < rightP.length)) {
            if (rightIndex == rightP.length || leftIndex < leftP.length && leftP[leftIndex] <= rightP[rightIndex]) {
                p[i++] = leftP[leftIndex++];
            } else {
                p[i++] = rightP[rightIndex++];
            }
        }

        return total;
    }
}