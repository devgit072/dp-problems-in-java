package com.devraj.dp.knapsack_0_1;

// This can be solved using following recursion based method:
// 1) Select current index element, if current capacity doesn't exceed max and then recurse.
// 2) Don't select current element and then recurse.
// 3) Return max of two.
// Time will be exponential : O(2^n)
public class RecursionBased {

    static int knapsack0_1(int[] w, int[] profits, int max_cap, int curr_cap, int curr_index) {
        if (curr_cap >= max_cap) {
            return 0;
        }
        int len = w.length;
        if (curr_index == len) return 0;

        int p_with_curr_index = 0;
        // This check is vital to ensure that we haven't exceeded max cap.
        if (curr_cap + w[curr_index] <= max_cap) {
            p_with_curr_index = profits[curr_index] + knapsack0_1(w, profits, max_cap, curr_cap + w[curr_index], curr_index + 1);
        }
        int p_without_curr = knapsack0_1(w, profits, max_cap, curr_cap , curr_index + 1);
        return Math.max(p_with_curr_index, p_without_curr);
    }
    public static void main(String[] args) {
        int[] w = {2, 3, 1, 4};
        int[] p = { 4, 5, 3, 7 };
        int max_cap = 5;
        int max_profit = knapsack0_1(w,p, max_cap, 0, 0);
        System.out.println("Maximum profit: " + max_profit);
    }
}
