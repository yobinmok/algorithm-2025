import java.io.*;
import java.util.*;

public class Main {

	static int N, M, max;
	static char[][] board;
	static boolean[] visited;
	static int[] dr = {1, -1, 0, 0}, dc = {0, 0, -1, 1};
	
	// 처음에 알파벳 중복 처리, 방문처리를 따로 해주었는데
	// 이 부분은 boolean[26]으로 한 번에 처리 가능
	// -> 같은 알파벳이 있는 위치는 다시 못가기 때문에 
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		board = new char[N][M];
		visited = new boolean[26];
		for (int i = 0; i < N; i++) {
			board[i] = br.readLine().toCharArray();
		}
		
		visited[board[0][0] - 'A'] = true;
		dfs(0, 0, 1);
		System.out.println(max);
	}
	
	// 지나갈 수 있는 최대칸
	static void dfs(int r, int c, int cnt) {
		max = Math.max(max, cnt);
		
		for (int d = 0; d < 4; d++) {
			int nr = r + dr[d];
			int nc = c + dc[d];
			
			if(nr < 0 || nr >= N || nc < 0 || nc >= M) continue;
			if(visited[board[nr][nc]-'A']) continue;
			
			visited[board[nr][nc]-'A'] = true;
			dfs(nr, nc, cnt+1);
			visited[board[nr][nc]-'A'] = false;
		}
	}
}
