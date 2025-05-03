package com.sumit.dsa.Graph;

import java.util.*;

public class bfs {
    public static void main(String[] args) {
        List<Integer>[] adj = new ArrayList[6];
        for (int i = 0; i < 6; i++) {
            adj[i] = new ArrayList<>();
        }

        addEdge(adj, 0, 1);
        addEdge(adj, 1, 2);
        addEdge(adj, 1, 3);
        addEdge(adj, 0, 4);

        List<Integer> ans = bfsOfGraph(adj, 5);
        System.out.println(ans);
    }

    private static List<Integer> bfsOfGraph(List<Integer>[] adj, int V) {
        List<Integer> bfs = new ArrayList<>();
        int[] vis = new int[V];
        Arrays.fill(vis, 0);

        Queue<Integer> q = new LinkedList<>();
        q.add(0);
        vis[0] = 1;

        while (!q.isEmpty()) {
            int node = q.poll();

            bfs.add(node);

            for (int neighbour : adj[node]) {
                if (vis[neighbour] == 0) {
                    q.add(neighbour);
                    vis[neighbour] = 1;
                }
            }
        }

        return bfs;
    }

    private static void addEdge(List<Integer>[] adj, int u, int v) {
        adj[u].add(v);
        adj[v].add(u);
    }
}
