import java.io.*;
import java.util.*;

public class Main{
	
	public static void main(String[] args)throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		int[] dp = new int[10001]; 
		
		dp[1] = 1;
		dp[2] = 2;
		dp[3] = 4;
		
		for(int t = 0; t<T; t++) {
			int n = Integer.parseInt(br.readLine()); // < 10,000
			
			for(int i = 4; i<=n; i++) {
				dp[i] = dp[i-1] + dp[i-2] + dp[i-3];
			}
			
			sb.append(dp[n]).append("\n");
		}
		System.out.println(sb);
	}
}