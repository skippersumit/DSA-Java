package com.sumit.dsa.Leetcode;

public class leetcode_1011_CapacityToShipPackagesWithinDDays {
    private static int calculateDays(int[] weights, int wt) {
        int weightSum = 0, days = 1;
        for (int weight : weights) {
            if (weight + weightSum > wt) {
                days++;
                weightSum = weight;
            } else {
                weightSum += weight;
            }
        }
        return days;
    }

    private static int maxEle(int[] weights) {
        int largest = Integer.MIN_VALUE;
        for (int wt : weights) {
            largest = Math.max(largest, wt);
        }
        return largest;
    }

    private static int sumEle(int[] weights) {
        int sum = 0;
        for (int wt : weights) {
            sum += wt;
        }
        return sum;
    }

    public static int shipWithinDays(int[] weights, int days) {
        int minCap = maxEle(weights), maxCap = sumEle(weights);
        int ans = Integer.MAX_VALUE;
        while (minCap <= maxCap) {
            int midCap = (minCap + maxCap) / 2;
            int day = calculateDays(weights, midCap);
            if (day <= days) {
                ans = midCap;
                maxCap = midCap - 1;
            } else {
                minCap = midCap + 1;
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        int[] weights = new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        int days = 5;
        System.out.println("Ans is " + shipWithinDays(weights, days));

        weights = new int[]{3, 2, 2, 4, 1, 4};
        days = 3;
        System.out.println("Ans is " + shipWithinDays(weights, days));

        weights = new int[]{1, 2, 3, 1, 1};
        days = 4;
        System.out.println("Ans is " + shipWithinDays(weights, days));
    }
}
