package com.sumit.dsa.Graph.dijkstra;

import com.sumit.leetcodeInputParser.Pair;
import com.sumit.leetcodeInputParser.Tuple;
import com.sumit.leetcodeInputParser.leetcodeInputParser;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class leetcode_787_CheapestFlightsWithinKStops {
    public static int findCheapestPrice(int n, int[][] flights, int src, int dst, int k) {
        ArrayList<ArrayList<Pair>> adj = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            adj.add(new ArrayList<>());
        }

        int m = flights.length;
        for (int[] flight : flights) {
            adj.get(flight[0]).add(new Pair(flight[1], flight[2]));
        }

        Queue<Tuple> q = new LinkedList<>();
        q.add(new Tuple(0, src, 0));
        int[] dist = new int[n];
        Arrays.fill(dist, Integer.MAX_VALUE);

        dist[src] = 0;
        while (!q.isEmpty()) {
            Tuple it = q.peek();
            q.remove();
            int stops = it.first;
            int node = it.second;
            int cost = it.third;

            if (stops > k) continue;
            for (Pair iter : adj.get(node)) {
                int adjNode = iter.first;
                int adjWt = iter.second;

                if (cost + adjWt < dist[adjNode]) {
                    dist[adjNode] = cost + adjWt;
                    q.add(new Tuple(stops + 1, adjNode, dist[adjNode]));
                }
            }
        }

        if (dist[dst] == Integer.MAX_VALUE) return -1;
        return dist[dst];
    }

    public static void main(String[] args) {
        int n = 4;
        int[][] flights = leetcodeInputParser.convertBrackets2D("[[0,1,100],[1,2,100],[2,0,100],[1,3,600],[2,3,200]]");
        int src = 0;
        int dest = 3;
        int k = 1; // stops
        System.out.println("Ans1 is " + findCheapestPrice(n, flights, src, dest, k));

        n = 3;
        flights = leetcodeInputParser.convertBrackets2D("[[0,1,100],[1,2,100],[0,2,500]]");
        src = 0;
        dest = 2;
        k = 1;
        System.out.println("Ans2 is " + findCheapestPrice(n, flights, src, dest, k));

        n = 3;
        flights = leetcodeInputParser.convertBrackets2D("[[0,1,100],[1,2,100],[0,2,500]]");
        src = 0;
        dest = 2;
        k = 0;
        System.out.println("Ans3 is " + findCheapestPrice(n, flights, src, dest, k));
    }
}
