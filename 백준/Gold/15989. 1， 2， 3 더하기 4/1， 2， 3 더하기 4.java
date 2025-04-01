import java.io.*;

public class Main{
	
	/*
	 * 숫자 N을 1, 2, 3의 합으로 나타내는 방법의 수를 구해라..
	 * DP 가트네..
	 * */
	
	public static void main(String[] args)throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		
		for(int t = 0; t<T; t++) {
			int n = Integer.parseInt(br.readLine()); // < 10,000
			int[][] dp = new int[10001][4]; 
			
			// dp 초기화
			for(int i = 1; i<=3; i++) {
				for(int j = 1; j<=i; j++) {
					dp[i][j] = 1;
				}
			}			
			
			for(int i = 4; i<=n; i++) {
				dp[i][1] = 1;
				dp[i][2] = dp[i-2][1] + dp[i-2][2];
				dp[i][3] = dp[i-3][1] + dp[i-3][2] + dp[i-3][3];
			}
			
			sb.append(dp[n][1] + dp[n][2] + dp[n][3]).append("\n");
		}
		System.out.println(sb);
	}
}