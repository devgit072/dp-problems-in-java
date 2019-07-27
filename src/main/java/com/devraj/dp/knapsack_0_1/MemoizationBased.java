package com.devraj.dp.knapsack_0_1;

// This can be solved using following recursion based method:
// 1) Select current index element, if current capacity doesn't exceed max and then recurse.
// 2) Don't select current element and then recurse.
// 3) Return max of two.
// Time will be exponential : O(2^n)

public class MemoizationBased {
    static int knapsack0_1(Integer[][] dp_memo, int[] w, int[] profits, int max_cap, int curr_cap, int curr_index) {
        if (dp_memo[curr_index][curr_cap] != null) {
            return dp_memo[curr_index][curr_cap];
        }
        if (curr_cap == max_cap) {
            dp_memo[curr_index][curr_cap] = 0;
            return 0;
        }
        int len = w.length;
        if (curr_index == len){
            dp_memo[curr_index][curr_cap] = 0;
            return 0;
        }

        int p_with_curr_index = 0;
        // This check is vital to ensure that we haven't exceeded max cap.
        if (curr_cap + w[curr_index] <= max_cap) {
            int val = knapsack0_1(dp_memo, w, profits, max_cap, curr_cap + w[curr_index], curr_index + 1);
            p_with_curr_index = profits[curr_index] + val;
        }
        int p_without_curr = knapsack0_1(dp_memo, w, profits, max_cap, curr_cap , curr_index + 1);
        int max = Math.max(p_with_curr_index, p_without_curr);
        // Cache the results for curr_index and curr_cap, so that we dont have to compute again.
        dp_memo[curr_index][curr_cap] = max;
        return dp_memo[curr_index][curr_cap];
    }
    public static void main(String[] args) {
        int[] w = {2, 3, 1, 4};
        int[] p = { 4, 5, 3, 7 };
        int max_cap = 5;
        int len = w.length;
        Integer[][] dp_memo = new Integer[len+1][max_cap+1];
        int max_profit = knapsack0_1(dp_memo, w,p, max_cap, 0, 0);
        System.out.println("Maximum profit: " + max_profit);
        // memoization matrix will have all the results.
        // we started here will curr_index=0, curr_cap=0, so dp_memo will have final results.
        System.out.println("Maximum profit: " + dp_memo[0][0]);
    }
}
