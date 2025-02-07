import java.io.*;
import java.util.*;

public class Main {

	static int N, cnt, time;
	static Shark s;
	static int[][] board;
	static int[] dr = {1, -1, 0, 0}, dc = {0, 0, 1, -1};
	static class Shark{
		int r, c, size, eat;

		public Shark(int r, int c, int size, int eat) {
			super();
			this.r = r;
			this.c = c;
			this.size = size;
			this.eat = eat;
		}
	}
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine()); // <= 20
		board = new int[N][N];
	
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {;
				board[i][j] = Integer.parseInt(st.nextToken());
				if(board[i][j] == 9) {
					s = new Shark(i, j, 2, 0);
					board[i][j] = 0; ///////////////////////////
				}
				else if(board[i][j] != 0) cnt += 1;
			}
		}
		
		while(true) {
			//if(cnt == 0) break;
			if(!bfs()) break;
		}
		
		System.out.println(time);
	}
	
	static boolean bfs() {
		// 최단거리 물고기 먹기 -> 주어진 조건 만족 위해 PQ 사용
		PriorityQueue<Shark> pq = new PriorityQueue<>((Shark f1, Shark f2) ->{
			if(f1.r == f2.r) return f1.c - f2.c;
			return f1.r - f2.r;
		});

		boolean[][] visited = new boolean[N][N];
		Queue<Shark> que = new ArrayDeque<>();
		visited[s.r][s.c] = true;
		que.add(s);
		int depth = 0;
		
		// 먹을 물고기까지 이동
		while(!que.isEmpty()) {
			int size = que.size();
			for (int i = 0; i < size; i++) {
				Shark cur = que.poll();
				
				for (int d = 0; d < 4; d++) {
					int nr = cur.r + dr[d];
					int nc = cur.c + dc[d];
					
					if(nr < 0 || nr >= N || nc < 0 || nc >= N || visited[nr][nc]) continue;
					if(board[nr][nc] > cur.size) continue; // 상어가 물고기보다 작으면 못지나감
					
					// 물고기가 상어보다 작으면 먹을 수 있음
					if(board[nr][nc] >= 1 && board[nr][nc] < cur.size) { 
						pq.add(new Shark(nr, nc, board[nr][nc], 0));
					}
					
					visited[nr][nc] = true;
					que.add(new Shark(nr, nc, cur.size, cur.eat)); // 아 왤케 풀어본 것 같지 이 부분 -> 미생물?
				}
			}
			depth += 1;
			
			if(!pq.isEmpty()) { // 먹은 물고기가 있으면
				Shark t = pq.poll();
				// 먹음
				board[t.r][t.c] = 0;
				s.eat += 1;
				cnt -= 1;
				
				// 이동
				s.r = t.r;
				s.c = t.c;
				time += depth; 
				
				if(s.eat == s.size) {
					s.size += 1; // 크기 = 먹은 물고기 수이면 크기 증가
					s.eat = 0; //////////////////////
				}
				return true;
			}
		}
		
		return false;
	}
}
