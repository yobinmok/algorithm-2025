import java.io.*;
import java.util.*;

public class Main{
	
	/*
	 * D 키로를 채우는 경우 중 최단거리
	 * */
	
	public static void main(String[] args)throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int D = Integer.parseInt(st.nextToken());
		int[][] highway = new int[N][3];
		int[] dp = new int[D+1];
		for(int i = 0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			highway[i][0] = Integer.parseInt(st.nextToken());
			highway[i][1] = Integer.parseInt(st.nextToken());
			highway[i][2] = Integer.parseInt(st.nextToken());
		}
		
		Arrays.fill(dp, Integer.MAX_VALUE);
		Arrays.sort(highway, (int[] o1, int[] o2) -> {
			return o1[0] == o2[0] ? o1[1] - o2[1] : o1[0] - o2[0];
		});
		
		int h = 0;
		for(int i = 0; i<=D; i++) {
			// 그냥 길 vs 고속도로 이용 중 최단거리 저장
			if(i == 0) dp[i] = 0;
			else dp[i] = Math.min(dp[i-1] + 1, dp[i]);
			
			while(h < N && highway[h][0] == i) {
				if(highway[h][1] <= D) {
					int e = highway[h][1];
					int c = highway[h][2];
					dp[e] = Math.min(dp[i] + c, dp[e]);
				}
				h += 1;
			}
		}
		
		System.out.println(dp[D]);
	}
}