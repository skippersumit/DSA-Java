package com.sumit.dsa.Graph.dijkstra;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

import static com.sumit.leetcodeInputParser.leetcodeInputParser.convertBrackets2D;

class xyWt {
    int x;
    int y;
    int wt;

    public xyWt(int x, int y, int wt) {
        this.x = x;
        this.y = y;
        this.wt = wt;
    }
}

public class leetcode_1631_PathWithMinimumEffort {
    public static int minimumEffortPath(int[][] heights) {
        int[] dx = {-1, 0, 1, 0};
        int[] dy = {0, 1, 0, -1};
        int n = heights.length;
        int m = heights[0].length;

        PriorityQueue<xyWt> pq = new PriorityQueue<>(Comparator.comparingInt(ele -> ele.wt));
        int[][] vis = new int[n][m];
        for (int i = 0; i < n; i++) Arrays.fill(vis[i], Integer.MAX_VALUE);

        vis[0][0] = 0;
        pq.add(new xyWt(0, 0, 0));
        while (!pq.isEmpty()) {
            xyWt front = pq.poll();
            int curX = front.x;
            int curY = front.y;
            int curWt = front.wt;

            if (curX == n - 1 && curY == m - 1) return curWt;
            if (vis[curX][curY] < curWt) continue;
            for (int i = 0; i < 4; i++) {
                int newX = curX + dx[i];
                int newY = curY + dy[i];

                if (newX >= 0 && newX < n && newY >= 0 && newY < m) {
                    int newEffort = Math.max(curWt, Math.abs(heights[curX][curY] - heights[newX][newY]));
                    if (newEffort < vis[newX][newY]) {
                        vis[newX][newY] = newEffort;
                        pq.add(new xyWt(newX, newY, newEffort));
                    }
                }
            }
        }
        return -1;
    }

    public static void main() {
        int[][] heights = convertBrackets2D("[[1,2,2],[3,8,2],[5,3,5]]");
        System.out.println("Ans is " + minimumEffortPath(heights));

        heights = convertBrackets2D("[[1,2,3],[3,8,4],[5,3,5]]");
        System.out.println("Ans2 is " + minimumEffortPath(heights));

        heights = convertBrackets2D("[[1,2,1,1,1],[1,2,1,2,1],[1,2,1,2,1],[1,2,1,2,1],[1,1,1,2,1]]");
        System.out.println("Ans3 is " + minimumEffortPath(heights));
    }
}
