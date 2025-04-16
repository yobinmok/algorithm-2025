import java.io.*;
import java.util.*;

public class Main{
	
	static int N, M, dis[][], board[][], target[];
	static int[] dr = {0, 0, 1, -1}, dc = {1, -1, 0, 0};
	
	public static void main(String[] args)throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		board = new int[N][M];
		dis = new int[N][M];
		target = new int[2];
		
		for(int i = 0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j<M; j++) {
				board[i][j] = Integer.parseInt(st.nextToken());
				if(board[i][j] == 2) {
					target[0] = i;
					target[1] = j;
				}else if(board[i][j] == 0) {
					dis[i][j] = -1;
				}
			}
		}
		
		bfs(target);
		
		for(int i = 0; i<N; i++) {
			for(int j = 0; j<M; j++) {
				int n = dis[i][j];
				if(n == 0) n = -1;
				else if(n == -1) n = 0;
				sb.append(n + " ");
			}
			sb.append("\n");
		}

		System.out.println(sb);
	}
	
	static void bfs(int[] start) {
		Queue<int[]> que = new ArrayDeque<>();
		
		int depth = 0;
		
		que.add(start);
		dis[start[0]][start[1]] = -1; // 임시로 0을 -1로 사용
		
		while(!que.isEmpty()) {
			int size = que.size();
			for(int i = 0; i<size; i++) {
				int[] cur = que.poll();
				
				for(int d = 0; d<4; d++) {
					int nr = cur[0] + dr[d];
					int nc = cur[1] + dc[d];
					
					if(nr < 0 || nr >= N || nc < 0 || nc >= M || 
							dis[nr][nc] != 0) continue;
					
					dis[nr][nc] = depth+1;
					que.add(new int[] {nr, nc});
				}
			}
			depth += 1;
		}
	}
}