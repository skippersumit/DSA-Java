package com.sumit.dsa.Graph.dijkstra;

import java.util.*;

import static com.sumit.leetcodeInputParser.leetcodeInputParser.convertBrackets2D;

class eleWt {
    int time;
    int element;

    public eleWt(int element, int dist) {
        this.time = dist;
        this.element = element;
    }
}
public class leetcode_743_networkTimeDelay {
    public static int networkDelayTime(int[][] times, int n, int k) {
        List<List<eleWt>> adj = new ArrayList<>();
        for(int i=0;i<n;i++) adj.add(new ArrayList<>());

        // Convert 1 based indexing to 0 based indexing
        for(int[] arr: times){
            int u = arr[0];
            int v = arr[1];
            int time = arr[2];
            adj.get(u-1).add(new eleWt(v-1,time));
        }

        // PQ for dijkstra
        PriorityQueue<eleWt> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a.time));

        int[] visTime = new int[n];
        Arrays.fill(visTime,Integer.MAX_VALUE);
        visTime[k-1] = 0; // Source node k se start hoga dijsktra, also 1 based to 0 based so vis[k-1]

        pq.add(new eleWt(k-1,0));
        while (!pq.isEmpty()){
            eleWt front = pq.poll();
            int node = front.element;
            int time = front.time;

            for(eleWt e: adj.get(node)){
                int adjNode = e.element;
                int edgeTime = e.time;

                if(time + edgeTime < visTime[adjNode] )
                {
                    visTime[adjNode] = time + edgeTime;
                    pq.add(new eleWt(adjNode,visTime[adjNode]));
                }
            }
        }

        int maxTime = 0;
        for(int time: visTime){
            if(time == Integer.MAX_VALUE) return -1;
            maxTime = Math.max(maxTime,time);
        }
        return maxTime;
    }

    public static void main(String[] args) {
        int[][] times = convertBrackets2D("[[2,1,1],[2,3,1],[3,4,1]]");
        int n = 4;
        int k = 2;
        System.out.println("Ans is " + networkDelayTime(times, n, k));

        times = convertBrackets2D("[[1,2,1]]");
        n = 2;
        k = 1;
        System.out.println("Ans1 is " + networkDelayTime(times, n, k));

        times = convertBrackets2D("[[1,2,1]]");
        k = 2;
        System.out.println("Ans2 is " + networkDelayTime(times, n, k));
    }
}
