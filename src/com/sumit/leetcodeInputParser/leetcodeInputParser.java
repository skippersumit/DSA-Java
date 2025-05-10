package com.sumit.leetcodeInputParser;

public class leetcodeInputParser {
    public static int[][] convertBrackets2D(String str) {
        str = str.trim();
        if (str.startsWith("[")) str = str.substring(1);
        if (str.endsWith("]")) str = str.substring(0, str.length() - 1);

        // Split rows
        String[] rows = str.split("],\\[");
        int[][] result = new int[rows.length][];

        for (int i = 0; i < rows.length; i++) {
            // Clean brackets from row
            String row = rows[i].replaceAll("[\\[\\]]", "");
            String[] nums = row.split(",");

            result[i] = new int[nums.length];
            for (int j = 0; j < nums.length; j++) {
                result[i][j] = Integer.parseInt(nums[j].trim());
            }
        }
        return result;
    }

    public static int[] convertBrackets1D(String str) {
        str = str.trim();
        str = str.replaceAll("[\\[\\]]", "");
        String[] nums = str.split(",");
        int[] result = new int[nums.length];

        for (int i = 0; i < nums.length; i++) {
            result[i] = Integer.parseInt(nums[i].trim());
        }
        return result;
    }

    public static int[][][] convertBrackets3D(String str) {
        str = str.trim();

        if (str.startsWith("[")) str = str.substring(1);
        if (str.endsWith("]")) str = str.substring(0, str.length() - 1);

        String[] blocks = str.split("],\\s*\\[\\[");

        int[][][] result = new int[blocks.length][][];

        for (int i = 0; i < blocks.length; i++) {
            String block = blocks[i];

            // Ensure each 2D block has outer brackets removed properly
            if (!block.startsWith("[")) block = "[" + block;
            if (!block.endsWith("]")) block = block + "]";

            result[i] = convertBrackets2D(block);
        }

        return result;
    }
}
