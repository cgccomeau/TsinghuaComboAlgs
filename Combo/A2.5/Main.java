import java.util.Scanner;
import java.util.ArrayList;

class Main {


    static int total = 0;

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int a = scan.nextInt();
        int b = scan.nextInt();
        int l = scan.nextInt();
        int count = 1;

        boolean[][] passwordMatrix = new boolean[3][3];
        passwordMatrix[Hposition(a)][Vposition(a)] = true;
        passwordMatrix[Hposition(b)][Vposition(b)] = true;
        dfs(a, b, l, count, passwordMatrix);
        System.out.println(total);
    }

    private static void dfs(int curr, int end, int l, int count, boolean[][] passwordMatrix) {
        ArrayList<Integer> neighbors = getNeighborsList(curr, passwordMatrix);
        boolean[][] newMatrix = new boolean[3][3];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                newMatrix[i][j] = passwordMatrix[i][j];
            }
        }

        //System.out.println(passwordMatrix);
        //System.out.println(newMatrix);
        //System.out.println("count: " + count);
        //System.out.println("l: " + l);
        //System.out.println("curr: " + curr);

        if (l > 9 || l < 1) {
            return;
        }

        if (count >= (l - 1)) {
            if (validMove(curr, end, newMatrix)){
                //System.out.println("curr: " + curr);
                //printMatrix(newMatrix);
                //System.out.println("aaaaa" + "\n");
                //System.out.println(curr);
                total++;
            }
            return;
        }

        for(int i = 0; i < neighbors.size(); i++) {
            //System.out.println("checking: " + neighbors.get(i));
            //System.out.println("neighbors: " + neighbors );
            if (validMove(curr, neighbors.get(i), passwordMatrix)) {
                //System.out.println("next!" + neighbors.get(i) + "\n");
                newMatrix[Hposition(neighbors.get(i))][Vposition(neighbors.get(i))] = true;
                dfs(neighbors.get(i), end, l, count + 1, newMatrix);
                newMatrix[Hposition(neighbors.get(i))][Vposition(neighbors.get(i))] = false;
            }
        }


        return;
    }

    private static ArrayList<Integer> getNeighborsList(int start, boolean[][] matrix) {
        ArrayList<Integer> neighborList = new ArrayList<>();
        for (int i = 1; i <= 9; i++) {
            if (start != i && !matrix[Hposition(i)][Vposition(i)] && validMove(start, i, matrix)) {
                neighborList.add(i);
            }
        }
        return neighborList;
    }

    private static boolean validMove(int start, int finish, boolean[][] passwordMatrix) {
        /*if (passwordMatrix[Hposition(finish)][Vposition(finish)]) {
            return false;
        }*/

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

    private static void printMatrix(boolean[][] passwordMatrix) {
        for(int i = 0; i < passwordMatrix.length; i++) {
            for(int j = 0; j < passwordMatrix[i].length; j++) {
                System.out.print(passwordMatrix[i][j]);
            }
            System.out.println();
        }
    }
}