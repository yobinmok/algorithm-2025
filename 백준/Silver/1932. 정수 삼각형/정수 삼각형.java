import java.io.*;
import java.util.*;

public class Main{
	
	public static void main(String[] args)throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int n = Integer.parseInt(br.readLine());
		int[][] tri = new int[n][n];
		int[][] dp = new int[n][n];
		
		for(int i = 0; i<n; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for(int j = 0; j<i+1; j++) {
				tri[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		dp[0][0] = tri[0][0];
				
		for(int r = 1; r<n; r++) {
			for(int c = 0; c<n; c++) {
				if(c == n-1) { // 맨뒤
					dp[r][c] = dp[r-1][c-1] + tri[r][c];
				}else if(c == 0) { // 맨앞
					dp[r][c] = dp[r-1][c] + tri[r][c];
				}else { // 중간
					dp[r][c] = Math.max(dp[r-1][c-1], dp[r-1][c]) + tri[r][c];
				}
			}
		}
		
		int max = 0;
		for(int i = 0; i<n; i++) {
			max = Math.max(max, dp[n-1][i]);
		}
		
		System.out.println(max);
	}
}


