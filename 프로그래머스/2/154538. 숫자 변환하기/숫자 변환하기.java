import java.util.*;

class Solution {
    /*
        x -> y를 위한 최소연산횟수
    */
    
    public int solution(int x, int y, int n) {
        int[] dp = new int[1000001];
        Arrays.fill(dp, Integer.MAX_VALUE-1);
        
        dp[x] = 0;
        for(int i = x+1; i<=y; i++){
            if(i - n > 0) {
                dp[i] = Math.min(dp[i-n]+1, dp[i]);
            }
            if(i % 2 == 0){
                dp[i] = Math.min(dp[i/2]+1, dp[i]);
            }
            if(i % 3 == 0){
                dp[i] = Math.min(dp[i/3]+1, dp[i]);
            }
        }
        
        return dp[y] == Integer.MAX_VALUE-1 ? -1 : dp[y];
    }
}