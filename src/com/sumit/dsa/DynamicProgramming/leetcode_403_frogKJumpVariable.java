package com.sumit.dsa.DynamicProgramming;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import static com.sumit.leetcodeInputParser.leetcodeInputParser.convertBrackets1D;

public class leetcode_403_frogKJumpVariable {
    public static boolean canCross(int[] stones) {
        int n = stones.length;
        if (stones[1] > 1) {
            return false;
        }
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < n; i++) {
            map.put(stones[i], i);
        }
        return solve(stones, 1, n, 1, map);
    }

    private static boolean solve(int[] stones, int index, int n, int k, Map<Integer, Integer> map) {
        if (index == n - 1) {
            return true;
        }
        boolean x = false, y = false, z = false;
        if (k > 1 && map.containsKey(stones[index] + k - 1)) {
            x = solve(stones, map.get(stones[index] + k - 1), n, k - 1, map);
        }
        if (map.containsKey(stones[index] + k)) {
            y = solve(stones, map.get(stones[index] + k), n, k, map);
        }
        if (map.containsKey(stones[index] + k + 1)) {
            z = solve(stones, map.get(stones[index] + k + 1), n, k + 1, map);
        }
        return x || y || z;
    }

    private static void run(int[] arr) {
        System.out.println("Array = " + Arrays.toString(arr));

        System.out.println("Ans is " + canCross(arr));

        System.out.println("\n\n");
    }

    public static void main() {
        int[] arr = convertBrackets1D("[0,1,3,5,6,8,12,17]");
        run(arr);

        arr = convertBrackets1D("[0,1,2,3,4,8,9,11]");
        run(arr);
    }
}
