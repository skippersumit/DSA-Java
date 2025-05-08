package com.sumit.dsa.Leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class leetcode_986_IntervalListIntersections {
    public static int[][] intervalIntersection(int[][] firstList, int[][] secondList) {
        List<int[]> ans = new ArrayList<>();
        int i=0,j=0;

        while (i<firstList.length && j<secondList.length)
        {
            int start = Math.max(firstList[i][0],secondList[j][0]);
            int end = Math.min(firstList[i][1],secondList[j][1]);

            if(start <= end) ans.add(new int[]{start,end});

            if(firstList[i][1] < secondList[j][1]) i++;
            else j++;
        }
        return ans.toArray(new int[ans.size()][]);
    }

    public static void main(String[] args) {
        int[][] first = new int[][] {{0,2},{5,10},{13,23},{24,25}};
        int[][] second = new int[][] {{1,5},{8,12},{15,24},{25,26}};

        int[][] mergedList = intervalIntersection(first,second);

        for(int[] arr: mergedList){
            System.out.println(Arrays.toString(arr));
        }
    }
}
