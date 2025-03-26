import java.io.*;
import java.util.*;

public class Main{
	
	/*
	 * 파이어스톰
	 * - 격자를 2L × 2L 크기의 부분격자로 나누기
	 * - 각 부분 격자를 시계방향 90도 회전
	 * - 얼음이 3칸이상 인접하지 않으면 얼음양 -= 1
	 * */
	
	static int N, Q, max, board[][];
	static int[] dr = {0, 1, 0, -1}, dc = {1, 0, -1, 0};
	
	public static void main(String[] args)throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
	
		N = (int) Math.pow(2, Integer.parseInt(st.nextToken()));
		Q = Integer.parseInt(st.nextToken());
		board = new int[N][N];
		max = 0;
		
		for(int i = 0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j<N; j++) {
				board[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		st = new StringTokenizer(br.readLine());
		for(int i = 0; i<Q; i++) {
			int L = Integer.parseInt(st.nextToken());
			// 2^L × 2^L 부분격자 회전
			int size = (int)Math.pow(2, L);
			if(L != 0) {
				for(int r = 0; r<N; r+=size) {
					for(int c = 0; c<N; c+=size) {
						turn(r, c, L);
					}
				}
			}
			
			// 얼음 녹기
			Queue<int[]> que = new ArrayDeque<>();
			for(int r = 0; r<N; r++) {
				for(int c = 0; c<N; c++) {
					int cnt = 0;
					for(int d = 0; d<4; d++) {
						int nr = r + dr[d];
						int nc = c + dc[d];
						
						if(nr < 0 || nr >= N || nc < 0 || nc >= N || board[nr][nc] == 0) continue;
						cnt += 1;
					}
					
					if(cnt < 3) que.add(new int[] {r, c});
				}
			}
			
			while(!que.isEmpty()) {
				int[] t = que.poll();
				if(board[t[0]][t[1]] > 0) board[t[0]][t[1]] -= 1;
			}
		}
		
		int sum = 0;
		for(int r = 0; r<N; r++) {
			for(int c = 0; c<N; c++) {
				sum += board[r][c];
			}
		}
		
		for(int r = 0; r<N; r++) {
			for(int c = 0; c<N; c++) {
				if(board[r][c] == 0) continue;
				bfs(r, c);
			}
		}
		
		System.out.println(sum + "\n" + max);
	}
	
	static void bfs(int r, int c) {
		Queue<int[]> que = new ArrayDeque<>();
		que.add(new int[] {r, c});
		board[r][c] = -1;
		int size = 1;
		
		while(!que.isEmpty()) {
			int[] temp = que.poll();
			
			for(int d = 0; d<4; d++) {
				int nr = temp[0] + dr[d];
				int nc = temp[1] + dc[d];
				
				if(nr < 0 || nr >= N || nc < 0 || nc >= N || board[nr][nc] == 0 || board[nr][nc] == -1) continue;
				
				board[nr][nc] = -1;
				size += 1;
				que.add(new int[] {nr, nc});
			}
		}
		
		max = Math.max(max, size);
	}
	
	static void turn(int r, int c, int L) {
		int size = (int)Math.pow(2, L);
		int[][] temp = new int[size][size];
		for(int i = 0; i<size; i++) {
			for(int j = 0; j<size; j++) {
				temp[j][size-i-1] = board[r+i][c+j];
			}
		}
		
		for(int i = 0; i<size; i++) {
			for(int j = 0; j<size; j++) {
				board[r+i][c+j] = temp[i][j];
			}
		}
	}
	
}