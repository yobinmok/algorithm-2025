import java.io.*;
import java.util.*;

public class Main{
	
	public static void main(String[] args)throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int n = Integer.parseInt(st.nextToken());
		int k = Integer.parseInt(st.nextToken());
		int[] coins = new int[n];
		int[] dp = new int[k+1];
		
		for(int i = 0; i<n; i++) {
			coins[i] = Integer.parseInt(br.readLine());
		}
		
		dp[0] = 1;
		for(int coin: coins) { // coin을 사용하는 경우
			for(int i = coin; i<=k; i++) {
				dp[i] += dp[i-coin];
			}
		}
		
		System.out.println(dp[k]);
	}
}