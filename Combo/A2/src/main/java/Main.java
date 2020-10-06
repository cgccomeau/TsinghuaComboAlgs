import java.util.Scanner;
/*
    Third OJ Assignment for Combo & Algs
    Calculating number of possible Android Passwords
    2019.
    Charlie Comeau / 2019403371
 */

//enumerate all possible vals, take out nonlegal ones
class Main {

    static boolean[][] passwordMatrix = new boolean[3][3];
    static int[] guess;

    public static void main (String[] args) {
        Scanner scan = new Scanner(System.in);
        int a = scan.nextInt();
        int b = scan.nextInt();
        int l = scan.nextInt();
        guess = new int[l];
        System.out.println(calcPassword(a, b, l));
    }

    private static void printMatrix() {
        for(int i = 0; i < passwordMatrix.length; i++) {
            for(int j = 0; j < passwordMatrix[i].length; j++) {
                System.out.print(passwordMatrix[i][j]);
            }
            System.out.println();
        }
    }

    public static boolean validMove(int start, int finish) {
        if (passwordMatrix[Hposition(finish)][Vposition(finish)]) {
            return false;
        }

        if (start == finish) {
            return false;
        }

        if (start == 0) {
            return false;
        }

        if (start == 5 || finish == 5) {
            return true;
        } else if ((start == 1 || start == 3 || start == 7 || start == 9) && (finish == 2 || finish == 4 || finish == 5 || finish == 6 || finish == 8)) {
            return true;
        } else if ((finish == 1 || finish == 3 || finish == 7 || finish == 9) && (start == 2 || start == 4 || start == 5 || start == 6 || start == 8)) {
            return true;
        } else if (start == 2 && (finish == 4 || finish == 6)) {
            return true;
        } else if (start == 4 && (finish == 2 || finish == 8)) {
            return true;
        } else if (start == 8 && (finish == 4 || finish == 6)) {
            return true;
        } else if (start == 6 && (finish == 2 || finish == 8)) {
            return true;
        } else if ((start == 1 || start == 9) && (passwordMatrix[Hposition(5)][Vposition(5)]) && (finish == 1 || finish == 9)) {
            return true;
        } else if ((start == 3 || start == 7) && (passwordMatrix[Hposition(5)][Vposition(5)]) && (finish == 3 || finish == 7)) {
            return true;
        } else if ((start == 1 || start == 3) && (passwordMatrix[Hposition(2)][Vposition(2)]) && (finish == 1 || finish == 3)) {
            return true;
        } else if ((start == 4 || start == 6) && (passwordMatrix[Hposition(5)][Vposition(5)]) && (finish == 4 || finish == 6)) {
            return true;
        } else if ((start == 7 || start == 9) && (passwordMatrix[Hposition(8)][Vposition(8)]) && (finish == 7 || finish == 9)) {
            return true;
        } else if ((start == 1 || start == 7) && (passwordMatrix[Hposition(4)][Vposition(4)]) && (finish == 1 || finish == 7)) {
            return true;
        } else if ((start == 2 || start == 8) && (passwordMatrix[Hposition(5)][Vposition(5)]) && (finish == 2 || finish == 8)) {
            return true;
        } else if ((start == 3 || start == 9) && (passwordMatrix[Hposition(6)][Vposition(6)]) && (finish == 3 || finish == 9)) {
            return true;
        }


        return false;
    }

    public static int calcPassword(int a, int b, int l) {


        //int[] guess = new int[l];
        guess[0] = a;
        guess[guess.length - 1] = b;
        passwordMatrix[Hposition(a)][Vposition(a)] = true;
        int[] possibleNums = new int[7];
        for (int i = 1, j = 0; i <= 9 && j < possibleNums.length; i++) {
            if (i != a && i != b) {
                possibleNums[j++] = i;
            }
        }
        int possibilities = (int) Math.pow(7, (1 - 2));
        int count = 0;

        int mid = l - 2;
        int i = 0; //possible nums
        int j = 1; // guess

        for (int k = 0; k < mid; k++) {
            guess[j] = possibleNums[0];
        }

        while (j < guess.length - 1 && i < possibleNums.length) {
            if (validMove(guess[j - 1], possibleNums[i])) {
                guess[j] = possibleNums[i];
                if (j == guess.length - 2) {
                    int lastDigitCheck = 0;
                    while (lastDigitCheck < possibleNums.length) {
                        guess[j] = possibleNums[lastDigitCheck];
                        for(int num: guess){
                            System.out.println("a  " + num);
                        }
                        printMatrix();
                        System.out.println("comparing " + guess[j - 1] + " and " + guess[j]);
                        if (!passwordMatrix[Hposition(guess[j])][Vposition(guess[j])] &&
                                validMove(guess[j - 1], guess[j]) && validMove(guess[j], b)) {
                            count++;
                            System.out.println("success!");
                        }
                        lastDigitCheck++;
                    }
                    guess[j] = 0;
                } else {
                    passwordMatrix[Hposition(possibleNums[i])][Vposition(possibleNums[i])] = true;
                }
                j++;
            }
            i++;
        }
        resetMatrix(a);



        return count;
    }


    private static void resetMatrix(int a) {
        for (int i = 0; i < passwordMatrix.length; i++) {
            for (int j = 0; j < passwordMatrix[i].length; j++) {
                if (Hposition(a) != i || Vposition(a) != j) {
                    passwordMatrix[i][j] = false;
                }
            }
        }
        for (int i = 1; i < guess.length - 1; i++) {
            guess[i] = 0;
        }
    }

    private static int Hposition(int num) {
        if (num == 1 || num == 2 || num == 3) {
            return 0;
        } else if (num == 4 || num == 5 || num == 6) {
            return 1;
        } else {
            return 2;
        }
    }

    private static int Vposition(int num) {
        if (num == 1 || num == 4 || num == 7) {
            return 0;
        } else if (num == 2 || num == 5 || num == 8) {
            return 1;
        } else {
            return 2;
        }
    }

    private static boolean checkUsed(int num) {
        return passwordMatrix[Hposition(num)][Vposition(num)];
    }
    /*private boolean checkLegalMove(int start, int finish) {
        if (start == 1 && (finish == 2 || finish == 4)) {

        }
    }*/
}
