import java.util.Scanner;
import java.awt.Point;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

class Main {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int x1 = scan.nextInt();
        int y1 = scan.nextInt();
        int x2 = scan.nextInt();
        int y2 = scan.nextInt();
        if (x1 > x2) {
            int tempx = x1;
            int tempy = y1;
            x1 = x2;
            y1 = y2;
            x2 = tempx;
            y2 = tempy;
        }
        int xDistance = Math.abs(x2 - x1);
        int yDistance = Math.abs(y2 - y1);

        if (x1 == y1 || x2 == y2 || (y1 > x1 && y2 < x2) || (y1 < x1 && y2 > x2)) {
            System.out.println(0);
        } else if (!(x1 > y1 && x1 > y2 && x2 > y1 && x2 > y2 || x1 < y1 && x1 < y2 && x2 < y1 && x2 < y2)) {
            if (xDistance == yDistance && Math.abs(x1 - y1) == 1 && Math.abs(x2 - y2) == 1) {
                System.out.println(combo(2 * xDistance, xDistance) - combo(2 * xDistance, xDistance - 1));
            } else {
                Map<Point, Integer> grid = new HashMap<>();
                Map<Point, Integer> close = new HashMap<>();
                grid.put(new Point(x1, y1), 1);
                boolean start = true;
                while (start || grid.size() > 0) {
                    start = false;
                    Set<Point> pointSet = grid.keySet();
                    for(Point key: pointSet) {
                        if (check(key.getX() + 1, key.getY(), x2, y2)) {
                            Point a = new Point((int) key.getX() + 1, (int) key.getY());
                            if (close.containsKey(a)) {
                                close.put(a, (close.get(a) + grid.get(key)) % 1000000007);
                            } else {
                                close.put(a, grid.get(key));
                            }
                        }
                        if (check(key.getX(), key.getY() + 1, x2, y2)) {
                            Point a = new Point((int) key.getX(), (int) key.getY() + 1);
                            if (close.containsKey(a)) {
                                close.put(a, (close.get(a) + grid.get(key)) % 1000000007);
                            } else {
                                close.put(a, grid.get(key));
                            }
                        }
                    }
                    if (close.size() == 1) {
                        for (Map.Entry<Point, Integer> entry : close.entrySet()) {
                            System.out.println(entry.getValue());
                        }
                    }
                    grid = close;
                    close = new HashMap<>();
                }
            }
        } else {
            System.out.println(combo(xDistance + yDistance, yDistance));
        }
    }


    private static boolean check(double a, double b, int x2, int y2) {
        return a != b && a <= x2 && b <= y2;
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