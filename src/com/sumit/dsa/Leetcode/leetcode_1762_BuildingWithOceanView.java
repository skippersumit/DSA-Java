package com.sumit.dsa.Leetcode;

import java.util.Arrays;
import java.util.LinkedList;

public class leetcode_1762_BuildingWithOceanView {

    public static int[] findBuildings(int[] heights) {
        int len = heights.length;

        int max = 0;
        LinkedList<Integer> ans = new LinkedList<>();
        for (int i = len - 1; i >= 0; i--) {
            int curHeight = heights[i];
            if (max < curHeight) {
                ans.addFirst(i);
                max = curHeight;
            }
        }

        return ans.stream().mapToInt(i -> i).toArray();
    }

    public static void main(String[] args) {
        int[] heights = new int[]{4, 2, 3, 1};
        System.out.println("Ans is " + Arrays.toString(findBuildings(heights)));

        int[] heights2 = new int[]{4, 3, 2, 1};
        System.out.println("Ans is " + Arrays.toString(findBuildings(heights2)));

        int[] heights3 = new int[]{1, 3, 2, 4};
        System.out.println("Ans is " + Arrays.toString(findBuildings(heights3)));

        int[] heights4 = new int[]{2, 2, 2, 2};
        System.out.println("Ans is " + Arrays.toString(findBuildings(heights4)));
    }
}
