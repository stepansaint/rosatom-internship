package com.test_task;


public class Second {
    
    public static void main(String[] args) {
        /*
            we can't pass the pointers (like in C)
        */
        int a = 103;
        int b = 91;
        System.out.printf("%n%-10s \t 'a' before = %d \t 'b' before = %d%n", "Without array:", a, b);
        swapValues(a, b);
        System.out.printf("%-10s \t 'a' after  = %d \t 'b' after  = %d%n", "Without array:", a, b);
        
        /*
            but we can use an array to change the values in the calling code, too
        */
        long[] twoNums = {103, 91};
        System.out.printf("%n%-10s \t 'a' before = %d \t 'b' before = %d%n", "Using array:", twoNums[0], twoNums[1]);
        swapValues(twoNums);
        System.out.printf("%-10s \t 'a' after  = %d \t 'b' after  = %d%n", "Using array:", twoNums[0], twoNums[1]);
    }
    
    /**
     * Swaps the values only inside this method.
     *
     * @param a first number
     * @param b second number
     */
    private static void swapValues(long a, long b) {
        a ^= b;
        b ^= a;
        a ^= b;
    }
    
    /**
     * Swaps the values not only inside this method, but also in the calling code.
     *
     * @param twoNums two numbers, wrapped in the array
     */
    private static void swapValues(long[] twoNums) {
        if (twoNums.length != 2) {
            throw new IllegalArgumentException("Array should contain two numbers");
        }
        
        twoNums[0] ^= twoNums[1];
        twoNums[1] ^= twoNums[0];
        twoNums[0] ^= twoNums[1];
    }
}
