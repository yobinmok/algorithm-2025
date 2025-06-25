import java.io.*;
import java.util.*;

public class Main{
	
	static int N, M, H, dp[][];
	static List<Integer>[] block;
	
	public static void main(String[] args)throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken()); // 학생 수
		M = Integer.parseInt(st.nextToken()); // 한 학생이 가질 수 있는 최대 블록 수
		H = Integer.parseInt(st.nextToken()); // 높이
		
		dp = new int[N+1][H+1]; // dp[h][n]: n명이 높이 h를 채울 수 잇는 경우의 수
		block = new ArrayList[N];
		
		for(int i = 0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			block[i] = new ArrayList<>();
			while(st.hasMoreTokens()) {
				block[i].add(Integer.parseInt(st.nextToken()));
			}
		}
		
		int MOD = 10007;
		dp[0][0] = 1;
		for(int s = 1; s<=N; s++) { // 학생 번호
			for (int h = 0; h <= H; h++) {
                dp[s][h] = dp[s - 1][h]; // 블록을 사용하지 않는 경우
            }
			
			for(int b: block[s-1]) {
				for(int h = b; h <= H; h++) {
					dp[s][h] += dp[s-1][h-b];
					dp[s][h] %= MOD;
				}
			}
		}
		
		System.out.println(dp[N][H]);
	}
}