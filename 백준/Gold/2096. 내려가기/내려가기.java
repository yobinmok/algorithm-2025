import java.io.*;
import java.util.*;
public class Main {

	/*
	 * 03:03
	 * 내려갈 시 바로 아래수로 넘어가거나 바로 아래의 수와 붙어있는 수로만 이동 가능
	 * 숫자표가 주어질 시 얻을 수 있는 최대, 최소 점수를 구해라.
	 * 최소 최대를 모두 구하려면 브루트포스?..
	 * 
	 * N < 100,000 -> 시간복잡도를 어케 구하지
	 * 3 * 2 ^ N..
	 * 브루트포스(재귀로) ㅋㅋ 시간초과 
	 * 역시나 dp구나
	 * */
	
	static int N, min, max, board[][];
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		board = new int[N][3];
		min = Integer.MAX_VALUE;
		max = 0;
		
		for(int i = 0; i<N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for(int j = 0; j<3; j++) {
				board[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		// 최솟값
		int[][] dp = new int[N][3];
		dp[0] = board[0].clone();
		for(int i = 1; i<N; i++) {
			dp[i][0] = Math.min(dp[i-1][0], dp[i-1][1]) + board[i][0];
			dp[i][1] = Math.min(Math.min(dp[i-1][0], dp[i-1][1]), dp[i-1][2]) + board[i][1];
			dp[i][2] = Math.min(dp[i-1][1], dp[i-1][2]) + board[i][2];
		}
		min = Math.min(dp[N-1][0], Math.min(dp[N-1][1], dp[N-1][2]));		
		
		// 최댓값
		dp = new int[N][3];
		dp[0] = board[0].clone();
		for(int i = 1; i<N; i++) {
			dp[i][0] = Math.max(dp[i-1][0], dp[i-1][1]) + board[i][0];
			dp[i][1] = Math.max(Math.max(dp[i-1][0], dp[i-1][1]), dp[i-1][2]) + board[i][1];
			dp[i][2] = Math.max(dp[i-1][1], dp[i-1][2]) + board[i][2];
		}
		max = Math.max(dp[N-1][0], Math.max(dp[N-1][1], dp[N-1][2]));
		
		System.out.println(max + " " + min);
	}
}
