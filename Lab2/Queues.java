package Lab2;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

class Cell {
    int x, y;

    Cell(int x, int y) {
        this.x = x;
        this.y = y;
    }
}

class State {
    int x;
    int y;

    State(int x, int y) {
        this.x = x;
        this.y = y;
    }
}

public class Queues {

    static void main() {
//        int[][] grid = {{0, 0, 0, 1}, {0, 1, 0, 0}, {0, 0, 0, 1}, {1, 0, 0, 0}};
//        System.out.println(alee(grid, 0, 0, 3, 3));
        System.out.println(vase(3, 4, 4));
    }

    static boolean vase(int a, int b, int t) {
        if(t > a && t > b)
            return false;

        Queue<State> q = new LinkedList<>();
        boolean[][] visited = new boolean[a + 1][b + 1];

        q.add(new State(0, 0));
        visited[0][0] = true;

        while (!q.isEmpty()) {

            State cur = q.poll();

            if (cur.x == t || cur.y == t) return true;

            List<State> next = new ArrayList<>();

            next.add(new State(a, cur.y));
            next.add(new State(cur.x, b));

            next.add(new State(0, cur.y));
            next.add(new State(cur.x, 0));

            int pour12 = Math.min(cur.x, b - cur.y);
            next.add(new State(cur.x - pour12, cur.y + pour12));

            int pour21 = Math.min(cur.y, a - cur.x);
            next.add(new State(cur.x + pour21, cur.y - pour21));

            for (State s : next) {

                if (!visited[s.x][s.y]) {
                    visited[s.x][s.y] = true;
                    q.add(s);
                }
            }
        }

        return false;
    }

    static int alee(int[][] grid, int startX, int startY, int endX, int endY) {

        int n = grid.length;
        int m = grid[0].length;

        boolean[][] visited = new boolean[n][m];
        int[][] dist = new int[n][m];

        Queue<Cell> q = new LinkedList<>();

        q.add(new Cell(startX, startY));
        visited[startX][startY] = true;

        int[] dx = {-1, 1, 0, 0};
        int[] dy = {0, 0, -1, 1};

        while (!q.isEmpty()) {

            Cell curent = q.poll();

            if (curent.x == endX && curent.y == endY) {
                return dist[curent.x][curent.y];
            }

            for (int k = 0; k < 4; k++) {

                int nx = curent.x + dx[k];
                int ny = curent.y + dy[k];

                if (nx < 0 || nx >= n || ny < 0 || ny >= m) continue;

                if (visited[nx][ny]) continue;

                if (grid[nx][ny] == 1) // blocat
                    continue;

                visited[nx][ny] = true;
                dist[nx][ny] = dist[curent.x][curent.y] + 1;

                q.add(new Cell(nx, ny));
            }
        }

        return -1; // nu exista drum
    }

}
