package com.sumit.dsa.Graph.dijkstra;

import java.util.*;

class queueEle {
    int dist;
    int element;

    public queueEle(int dist, int element) {
        this.dist = dist;
        this.element = element;
    }
}

public class leetcode_815_busRoutes {

    public static int numBusesToDestination(int[][] routes, int source, int target) {
        if (source == target) return 0;

        // Map each stop to the list of buses that pass through it
        Map<Integer, List<Integer>> stopToBuses = new HashMap<>();
        for (int bus = 0; bus < routes.length; bus++) {
            for (int stop : routes[bus]) {
                stopToBuses.putIfAbsent(stop, new ArrayList<>());
                stopToBuses.get(stop).add(bus);
            }
        }

        if (!stopToBuses.containsKey(source) || !stopToBuses.containsKey(target)) {
            return -1;
        }

        PriorityQueue<queueEle> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a.dist));
        Map<Integer, Integer> minBuses = new HashMap<>();
        pq.add(new queueEle(0, source));
        minBuses.put(source, 0);

        while (!pq.isEmpty()) {
            queueEle frontEle = pq.poll();
            int currentStop = frontEle.element;
            int currentBuses = frontEle.dist;

            if (currentStop == target) {
                return currentBuses;
            }

            if (currentBuses > minBuses.getOrDefault(currentStop, Integer.MAX_VALUE)) {
                continue;
            }

            for (int bus : stopToBuses.get(currentStop)) {
                for (int nextStop : routes[bus]) {
                    if (currentBuses + 1 < minBuses.getOrDefault(nextStop, Integer.MAX_VALUE)) {
                        minBuses.put(nextStop, currentBuses + 1);
                        pq.add(new queueEle(currentBuses + 1, nextStop));
                    }
                }
            }
        }

        return -1;
    }

    public static void main(String[] args) {
        int[][] routes = new int[][]{{1, 2, 7}, {3, 6, 7}};
        int source = 1, target = 6;
        int ans = numBusesToDestination(routes, source, target);
        System.out.println("Ans is " + ans);

        routes = new int[][]{{7, 12}, {4, 5, 15}, {6}, {15, 19}, {9, 12, 13}};
        source = 15;
        target = 12;
        ans = numBusesToDestination(routes, source, target);
        System.out.println("Ans is " + ans);
    }
}