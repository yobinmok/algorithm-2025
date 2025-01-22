import java.io.*;
import java.util.*;

public class Main {

	static int N, cnt, board[][];
	static int[] dr = {0, 1, 1}, dc = {1, 1, 0};
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		board = new int[N][N];
		for(int i = 0; i<N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for(int j = 0; j<N; j++) {
				board[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		dfs(0, 1, 0);
		System.out.println(cnt);
	}
	
	// d(방향) 0: 가로, 1: 대각선, 2: 세로
	static void dfs(int r, int c, int d) {
		if(r == N-1 && c == N-1) {
			cnt += 1;
			return;
		}
		
		// 대각선
		boolean flag = true;
		for(int i = 0; i<3; i++) {
			int nr = r + dr[i];
			int nc = c + dc[i];
			if(nr < 0 || nr >= N || nc < 0 || nc >= N || 
					board[nr][nc] == 1) {
				flag = false;
				break;
			}
		}
		if(flag) dfs(r+1, c+1, 1);
		
		// 가로
		if(d != 2) { // 세로가 아니면 -> 가로, 대각선
			int nr = r + dr[0];
			int nc = c + dc[0];
			if(nr >= 0 && nr < N && nc >= 0 && nc < N && 
					board[nr][nc] != 1) {
				dfs(r, c+1, 0);
			}
		}
		
		// 세로
		if(d != 0) { // 가로가 아니면 -> 세로, 대각선
			int nr = r + dr[2];
			int nc = c + dc[2];
			if(nr >= 0 && nr < N && nc >= 0 && nc < N && 
					board[nr][nc] != 1) {
				dfs(r+1, c, 2);
			}
		}
	}	
}
