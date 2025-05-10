package com.sumit.dsa.Leetcode;

import java.util.ArrayList;
import java.util.Collections;

public class leetcode_2551_putMarblesInABag {
    public static long putMarbles(int[] weights, int k) {
        ArrayList<Long> list = new ArrayList<>();
        for (int i = 1; i < weights.length; i++) {
            list.add((long) weights[i] + (long) weights[i - 1]);
        }
        Collections.sort(list);
        int n = list.size();
        long mini = 0;
        for (int i = 0; i < k - 1; i++) {
            mini += list.get(i);
        }

        long maxi = 0;
        for (int i = 0; i < k - 1; i++) {
            maxi += list.get(n - i - 1);
        }
        return maxi - mini;
    }

    public static void main(String[] args) {
        int[] arr = new int[]{1, 3, 5, 1};
        int k = 2;
        System.out.println("Ans is  " + putMarbles(arr, k));

        arr = new int[]{1, 3};
        k = 1;
        System.out.println("Ans is  " + putMarbles(arr, k));
    }
}
