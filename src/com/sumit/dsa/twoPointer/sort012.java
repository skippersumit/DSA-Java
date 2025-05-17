package com.sumit.dsa.twoPointer;

import com.sumit.leetcodeInputParser.leetcodeInputParser;

import java.util.Arrays;

public class sort012 {

    public static void sortColors(int[] nums) {
        int low = 0, mid = 0, high = nums.length - 1;
        while (mid <= high) {
            if (nums[mid] == 0) {
                int tmp = nums[low];
                nums[low++] = nums[mid];
                nums[mid++] = tmp;
            } else if (nums[mid] == 1) {
                mid++;
            } else {
                int tmp = nums[mid];
                nums[mid] = nums[high];
                nums[high--] = tmp;
            }
        }
    }
    public static void main(String[] args) {
    int[] arr = leetcodeInputParser.convertBrackets1D("[2,0,2,1,1,0]");
        System.out.println("Before " + Arrays.toString(arr));
        sortColors(arr);
        System.out.println("after " + Arrays.toString(arr));
    }
}
