import java.io.*;
import java.util.*;

public class Main {

	/*
	 * 범위를 넘으면 다시 첨부터 시작
	 * 격자에는 바구니
	 * 비구름이 생김 (좌측하단에
	 * 구름 이동 -> 이동한 곳에서 물 +1 -> 구름 사라짐 -> 이동한 곳에서 물복사 버그(대각선 확인) 
	 * -> 바구니에 물이 2이상이면 구름이 생김(이전에 구름이 있던 곳이면 안됨)
	 * */
	
	static int N, M;
	static Queue<int[]> cloud;
	static boolean[][] hasCloud;
	static int[] board[], dr = {0, -1, -1, -1, 0, 1, 1, 1}, dc = {-1, -1, 0, 1, 1, 1, 0, -1}; 
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		board = new int[N][N];
		hasCloud = new boolean[N][N];
		for(int i = 0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j<N; j++) {
				board[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		// 초기화
		cloud = new ArrayDeque<>();
		cloud.add(new int[] {N-1, 0});
		cloud.add(new int[] {N-1, 1});
		cloud.add(new int[] {N-2, 0});
		cloud.add(new int[] {N-2, 1});
		
		for(int i = 0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			// d 방향으로 s칸 이동
			int d = Integer.parseInt(st.nextToken())-1;
			int s = Integer.parseInt(st.nextToken());
			moveCloud(d, s);
		}
		
		int sum = 0;
		for(int i = 0; i<N; i++) {
			for(int j = 0; j<N; j++) {
				sum += board[i][j];
			}
		}
		
		System.out.println(sum);
	}
	
	static void moveCloud(int d, int s) {
		int size = cloud.size();
		for(int i = 0; i<size; i++) {
			// 1. 구름 이동 후 물의 양 +1
			int[] cur = cloud.poll();
			int nr = cur[0] + dr[d] * (s%N);
			int nc = cur[1] + dc[d] * (s%N);
			
			cloud.add(move(nr, nc));
		}
		
		// 2. 물복사버그
		while(!cloud.isEmpty()) {
			int[] cur = cloud.poll();
			int cnt = 0;
			for(int dir = 1; dir < 8; dir += 2) {
				int nr = cur[0] + dr[dir];
				int nc = cur[1] + dc[dir];
				if(nr < 0 || nr >= N || nc < 0 || nc >= N) continue;
				
				if(board[nr][nc] > 0) cnt += 1;
			}
			board[cur[0]][cur[1]] += cnt;
		}
		
		// 3. 구름 갱신
		for(int i = 0; i<N; i++) {
			for(int j = 0; j<N; j++) {
				if(board[i][j] >= 2 && !hasCloud[i][j]) {
					cloud.add(new int[] {i, j});
					board[i][j] -= 2;
					continue;
				}
				
				if(hasCloud[i][j]) hasCloud[i][j] = false;
			}
		}
	}
	
	static int[] move(int nr, int nc) {
		if(nr < 0) {
			nr += N;
		}else if(nr >= N) {
			nr -= N;
		}
		
		if(nc < 0) {
			nc += N;
		}else if(nc >= N) {
			nc -= N;
		}
		
		board[nr][nc] += 1;
		hasCloud[nr][nc] = true;
		return new int[] {nr, nc};
	}
}
