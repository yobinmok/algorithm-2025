import java.io.*;
import java.util.*;

public class Main {

	/*
	 * 유의
	 * 1. 가로, 세로의 경우 현재 칸에만 벽이 있는지 확인하면 되지만
	 * 대각선의 경우 좌측과 상위칸 모두 벽이 있는지 확인해야 함
	 * 
	 * 2. dp[i][j][d]: d 방향으로 (i, j)에 파이프의 끝부분이 위치하는 경우
	 * dp 배열에 대한 정의 확실히!
	 * */
	
	static int N, cnt, board[][], dp[][][];
	static int[] dr = {0, 1, 1}, dc = {1, 1, 0};
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		board = new int[N][N];
		dp = new int[N][N][3];
		for(int i = 0; i<N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for(int j = 0; j<N; j++) {
				board[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		// dp[i][j][d]: d 방향으로 (i, j)에 파이프의 끝부분이 위치하는 경우
		// d(방향) 0: 가로, 1: 대각선, 2: 세로
		dp[0][1][0] = 1;
		for(int i = 0; i<N; i++) { // 행
			for(int j = 2; j<N; j++) { // 열
				// (i, j)에 가로 방향으로 파이프를 놓으려면 이전칸이 가로/대각선으로 놓아져있어야 함.
				if(board[i][j] == 1) continue;
				dp[i][j][0] = dp[i][j-1][0] + dp[i][j-1][1]; 
				if(i == 0) continue;
				dp[i][j][2] = dp[i-1][j][1] + dp[i-1][j][2];
				if(board[i-1][j] == 1 || board[i][j-1] == 1) continue; // 대각선의 경우 벽 유무 추가 확인 
				dp[i][j][1] = dp[i-1][j-1][0] + dp[i-1][j-1][1] + dp[i-1][j-1][2];
			}
		}
		
		System.out.println(dp[N-1][N-1][0] + dp[N-1][N-1][1] + dp[N-1][N-1][2]);
	}
}
