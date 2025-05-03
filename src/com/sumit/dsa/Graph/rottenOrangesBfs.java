package com.sumit.dsa.Graph;

import java.util.*;

class RCTime {
    int first;
    int second;
    int third;

    public RCTime(int first, int second, int third) {
        this.first = first;
        this.second = second;
        this.third = third;
    }
}

public class rottenOrangesBfs {
    public static void main(String[] args) {
        List<List<Integer>> mat = new ArrayList<>();
        mat.add(new ArrayList<>(Arrays.asList(0, 1, 2)));
        mat.add(new ArrayList<>(Arrays.asList(0, 1, 2)));
        mat.add(new ArrayList<>(Arrays.asList(2, 1, 1)));
        int ans = orangesRotting(mat, mat.size(), mat.getFirst().size());

        System.out.println("No of rotten oranges = " + ans);
    }

    private static int orangesRotting(List<List<Integer>> mat, int n, int m) {
        Queue<RCTime> q = new LinkedList<>();
        int[][] vis = new int[n][m];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (mat.get(i).get(j) == 2) {
                    q.add(new RCTime(i, j, 0));
                    vis[i][j] = 2;
                } else {
                    vis[i][j] = 0;
                }
            }
        }

        int totalTimeTaken = 0;
        int[] dx = {-1, 0, 1, 0};
        int[] dy = {0, 1, 0, -1};

        while (!q.isEmpty()) {
            RCTime front = q.poll();
            int r = front.first;
            int c = front.second;
            int time = front.third;

            totalTimeTaken = Math.max(totalTimeTaken, time);

            for (int i = 0; i < 4; i++) {
                int newRow = r + dx[i];
                int newCol = c + dy[i];

                if (newRow >= 0 && newRow < n && newCol >= 0 && newCol < m && (vis[newRow][newCol] != 2) && (mat.get(newRow).get(newCol) == 1)) {
                    q.add(new RCTime(newRow, newCol, time + 1));
                    vis[newRow][newCol] = 2;
                }
            }
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if ((vis[i][j] != 2) && mat.get(i).get(j) == 1) return -1;
            }
        }

        return totalTimeTaken;
    }
}
