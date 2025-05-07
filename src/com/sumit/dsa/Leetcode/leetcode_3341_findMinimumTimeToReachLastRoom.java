package com.sumit.dsa.Leetcode;

import java.util.*;

class XY{
    int time;
    int x;
    int y;

    public XY(int time, int x, int y) {
        this.time = time;
        this.x = x;
        this.y = y;
    }
}

public class leetcode_3341_findMinimumTimeToReachLastRoom {
    public static int minTimeToReach(int[][] moveTime) {
        int rows = moveTime.length;
        int cols = moveTime[0].length;

        PriorityQueue<XY> minHeap = new PriorityQueue<>(Comparator.comparingInt(xy -> xy.time));
        int[][] vis = new int[rows][cols];
        for (int i=0;i<rows;i++) Arrays.fill(vis[i],Integer.MAX_VALUE);

        minHeap.add(new XY(0,0,0));
        vis[0][0] = 0;

        int[][] directions = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};

        while (!minHeap.isEmpty())
        {
            XY cur = minHeap.poll();
            int currentTime = cur.time, x = cur.x, y = cur.y;

            if(x == rows-1 && y == cols-1) return currentTime;

            for(int[] dir : directions){
                int newx = x + dir[0];
                int newy = y + dir[1];

                if(newx >= 0 && newx < rows && newy >=0 && newy < cols ){
                    int waitTime = Math.max(moveTime[newx][newy] - currentTime, 0 );
                    int newTime = currentTime + 1 + waitTime;

                    if(newTime < vis[newx][newy]){
                        vis[newx][newy] = newTime;
                        minHeap.add(new XY(newTime,newx,newy));
                    }
                }
            }
        }
        return  -1;
    }
    public static void main(String[] args) {
        int[][] moveTime = new int[][]{{0,4},{4,4}};
        System.out.println("Ans 1 = "+ minTimeToReach(moveTime));

        int[][] moveTime2 = new int[][]{{0,0,0},{0,0,0}};
        System.out.println("Ans 2 = "+ minTimeToReach(moveTime2));
    }
}
