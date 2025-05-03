package com.sumit.dsa.Graph;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class alienDictionary_topoLogicalSort {
    public static void main(String[] args) {
        int N = 5, K = 4;
        String[] dict = {"baa", "abcd", "abca", "cab", "cad"};
        String ans = findOrder(dict, N, K);

        System.out.println("Order 1: " + ans);

        N = 3;
        K = 3;
        String[] dict2 = {"caa", "aaa", "aab"};

        ans = findOrder(dict2, N, K);
        System.out.println("Order 2: " + ans);
    }

    private static String findOrder(String[] dict, int n, int k) {
        List<Integer>[] adj = new ArrayList[k];
        for (int i = 0; i < k; i++) {
            adj[i] = new ArrayList<>();
        }
        for (int i = 0; i < n - 1; i++) {
            String s1 = dict[i];
            String s2 = dict[i + 1];

            int len = Math.min(s1.length(), s2.length());
            for (int ptr = 0; ptr < len; ptr++) {
                if (s1.charAt(ptr) != s2.charAt(ptr)) {
                    adj[s1.charAt(ptr) - 'a'].add(s2.charAt(ptr) - 'a');
                    break;
                }
            }
        }

        List<Integer> topo = topoSort(k, adj);
        StringBuilder ans = new StringBuilder();
        for (int node : topo) {
            ans.append((char) (node + 'a'));
        }

        return ans.toString();
    }

    private static List<Integer> topoSort(int V, List<Integer>[] adj) {
        int[] indegree = new int[V];

        for (int i = 0; i < V; i++) {
            for (int neighbour : adj[i]) {
                indegree[neighbour]++;
            }
        }

        Queue<Integer> q = new LinkedList<>();
        for (int i = 0; i < V; i++) {
            if (indegree[i] == 0) q.add(i);
        }

        List<Integer> topo = new ArrayList<>();
        while (!q.isEmpty()) {
            int node = q.poll();
            topo.add(node);

            for (int neighbour : adj[node]) {
                indegree[neighbour]--;
                if (indegree[neighbour] == 0) {
                    q.add(neighbour);
                }
            }
        }

        return topo;
    }
}
