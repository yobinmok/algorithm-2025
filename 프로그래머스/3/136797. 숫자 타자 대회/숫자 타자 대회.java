import java.util.*;

class Solution {
    
    String numbers;
    int size, dp[][][];
    int[][] cost = {
        { 1, 7, 6, 7, 5, 4, 5, 3, 2, 3 },
        { 7, 1, 2, 4, 2, 3, 5, 4, 5, 6 },
        { 6, 2, 1, 2, 3, 2, 3, 5, 4, 5 },
        { 7, 4, 2, 1, 5, 3, 2, 6, 5, 4 },
        { 5, 2, 3, 5, 1, 2, 4, 2, 3, 5 },
        { 4, 3, 2, 3, 2, 1, 2, 3, 2, 3 },
        { 5, 5, 3, 2, 4, 2, 1, 5, 3, 2 },
        { 3, 4, 5, 6, 2, 3, 5, 1, 2, 4 },
        { 2, 5, 4, 5, 3, 2, 3, 2, 1, 2 },
        { 3, 6, 5, 4, 5, 3, 2, 4, 2, 1 }
    };
    
    public int solution(String numbers) {
        this.numbers = numbers;
        this.size = numbers.length();
        
        dp = new int[size+1][10][10]; // dp[n][l][r]: n번째 숫자까지 l, r 위치에서의 최솟값
        for (int i = 0; i < size + 1; i++) {
            for (int j = 0; j < 10; j++)
                Arrays.fill(dp[i][j], -1);
        }
        
        return dfs(0, 4, 6);        
    }
    
    int dfs(int n, int left, int right){
        if(n == size){
            return 0;
        }
        if(dp[n][left][right] != -1) return dp[n][left][right];
        
        int cur = numbers.charAt(n) - '0';
        int min = Integer.MAX_VALUE;
        
        // 왼손 이동
        if(cur != right){
            min = Math.min(dfs(n+1, cur, right) + cost[left][cur], min);
        }
        
        // 오른손 이동
        if(cur != left){
            min = Math.min(dfs(n+1, left, cur) + cost[right][cur], min);
        }
        
        return dp[n][left][right] = min;
    }
}