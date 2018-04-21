package io.moonnight.lintcode.dp;

import java.util.List;

public class Solution {

    /**
     *
     * 给一个整数数组，调整每个数的大小，使得相邻的两个数的差不大于一个给定的整数target，调整每个数的代价为调整前后的差的绝对值，求调整代价之和最小是多少。
     * 对于数组[1, 4, 2, 3]和target=1，最小的调整方案是调整为[2, 3, 2, 3]，调整代价之和是2。返回2。
     * @param A
     * @param target
     * @return
     */
    public int MinAdjustmentCost(List<Integer> A, int target) {
        // write your code here
        if(A.size()<2)  {
            return 0;
        }
        int m = A.size();
        long [][]dp = new long[m][101];
        int i = 0, j = 0;
        for (i = 0; i < 101; i++) {
            dp[0][i] = Math.abs(A.get(0) - i);
        }
        for (i = 1; i < m; i++) {
            for (j = 0; j < 101; j++) {
                dp[i][j] = Integer.MAX_VALUE;
                int dif = Math.abs(j - A.get(i));
                int max = Math.min(100, j + target);
                int min = Math.max(0, j - target);
                for (int k = min; k <= max; k++) {
                    dp[i][j] = Math.min(dp[i][j], dp[i - 1][k] + dif);
                }
            }
        }
        long ans = Integer.MAX_VALUE;
        for (j = 0; j < 101; j++) {
            ans = Math.min(ans, dp[m - 1][j]);
        }
        return(int) ans;


    }
}
