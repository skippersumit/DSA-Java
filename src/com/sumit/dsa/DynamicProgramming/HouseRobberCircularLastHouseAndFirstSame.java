package com.sumit.dsa.DynamicProgramming;

import java.util.ArrayList;
import java.util.List;

public class HouseRobberCircularLastHouseAndFirstSame {
    /*
     * Since first and last elements are adjacent
     * means cannot be taken together
     * ans = leave last and leave first
     * 0 1 ... n-1 -> max of these (0 ... n-2) or (1 .. n-1)
     * */

    public static int rob(int[] arr) {
        int n = arr.length;
        List<Integer> tmp1 = new ArrayList<>();
        List<Integer> tmp2 = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            if (i != 0) tmp1.add(arr[i]);
            if (i != n - 1) tmp2.add(arr[i]);
        }
        int leaveLastEle = maxSumOfNonAdjacentElements.maximumNonAdjacentElementsDpTabulation(tmp1.stream().mapToInt(i -> i).toArray());
        int leaveFirstEle = maxSumOfNonAdjacentElements.maximumNonAdjacentElementsDpTabulation(tmp2.stream().mapToInt(i -> i).toArray());

        return Math.max(leaveLastEle, leaveFirstEle);
    }

    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 1};

        System.out.println("Ans is " + rob(arr));
    }
}
