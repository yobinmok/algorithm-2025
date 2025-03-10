import java.util.*;

class Solution {
    
    public int solution(int[][] info, int n, int m) {
        int INF = 100000;
        int min = INF;
        
        int[][] dp = new int[info.length+1][m]; // dp[x][y] = z -> dp[현재훔친물건수][B흔적] = A의 최소흔적
        for(int[] t: dp){
            Arrays.fill(t, INF);
        }
        dp[0][0] = 0;
        for(int i = 1; i<=info.length; i++){
            int a = info[i-1][0];
            int b = info[i-1][1];
            for(int j = 0; j<m; j++){
                dp[i][j] = Math.min(dp[i][j], dp[i-1][j] + a);
                if(j + b < m){
                    dp[i][j+b] = Math.min(dp[i][j+b], dp[i-1][j]);
                }
            }
        }
        
        System.out.println(Arrays.toString( dp[info.length]));
        for(int t: dp[info.length]){
            min = Math.min(t, min);
        }
        return min >= n ? -1 : min;
    }
}