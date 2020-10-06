import java.util.Scanner;

class Main {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        int n = scan.nextInt();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = scan.nextInt();
        }

        //mergeSort(arr);
//
//        //for (int num: arr) {
//        //    System.out.print(num + " ");
        //}

        int i = 1;
        while (i < n) {
            for (int j = 0; j < n;) {
                int[] arrLeft = new int[i];
                int[] arrRight = new int[i];
                for (int k = 0; k < i; k++) {
                    arrLeft[k] = arr[j + k];
                }
                for (int k = i; k < k + i; k++) {
                    arrRight[k] = arr[j + k];
                }

                upMerge(arr, arrLeft, arrRight);
            }
            i *= 2;
        }


    }

    private static void mergeSort(int[] arr) {
        if (arr.length == 1) {
            return;
        }

        int midArrLength = arr.length / 2;
        int[] arrLeft = new int[midArrLength];
        for (int i = 0; i < arrLeft.length; i++) {
            arrLeft[i] = arr[i];
        }
        int[] arrRight = new int[arr.length - arrLeft.length];
        for (int i = 0, j = midArrLength; i < arrRight.length; i++, j++) {
            arrRight[i] = arr[j];
        }

        mergeSort(arrLeft);
        mergeSort(arrRight);

        int i = 0;
        int leftIndex = 0;
        int rightIndex = 0;
        while(i < arr.length) {
            if (rightIndex == arrRight.length || leftIndex < arrLeft.length && arrLeft[leftIndex] <= arrRight[rightIndex]) {
                arr[i++] = arrLeft[leftIndex++];
            } else {
                arr[i++] = arrRight[rightIndex++];
            }
        }
    }

    private static void upMerge(int[] arrLeft, int[] arrRight) {

    }
}