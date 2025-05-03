package com.sumit.dsa.Graph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class dfs {
    public static void main(String[] args) {
        List<Integer>[] adj = new ArrayList[6];
        for (int i = 0; i < 6; i++) {
            adj[i] = new ArrayList<>();
        }

        addEdge(adj, 0, 2);
        addEdge(adj, 2, 4);
        addEdge(adj, 0, 1);
        addEdge(adj, 0, 3);

        List<Integer> ans = dfsOfGraph(adj, 5);
        System.out.println(ans);
    }

    private static List<Integer> dfsOfGraph(List<Integer>[] adj, int V) {
        List<Integer> dfsList = new ArrayList<>();
        int[] vis = new int[V];
        Arrays.fill(vis, 0);
        int start = 0;

        dfsUtil(start, adj, V, vis, dfsList);

        return dfsList;
    }

    private static void dfsUtil(int node, List<Integer>[] adj, int v, int[] vis, List<Integer> dfsList) {
        vis[node] = 1;
        dfsList.add(node);

        for (int neighbour : adj[node]) {
            if (vis[neighbour] == 0) {
                dfsUtil(neighbour, adj, v, vis, dfsList);
            }
        }
    }


    private static void addEdge(List<Integer>[] adj, int u, int v) {
        adj[u].add(v);
        adj[v].add(u);
    }
}
