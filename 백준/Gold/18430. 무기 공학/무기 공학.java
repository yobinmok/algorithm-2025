import java.io.*;
import java.util.*;

public class Main{
	
	/*
	 * 나무 재료를 잘라 여러 개의 부메랑을 만들려고 함
	 * 부메랑의 중심은 강동 영향 2배
	 * 만들 수 있는 부메랑들의 강도 합의 최댓값
	 * */
	
	static int N, M, max;
	static int[][] board;
	static boolean[][] visited;
	
	
	public static void main(String[] args)throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		board = new int[N][M];
		visited = new boolean[N][M];
		for(int i = 0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j<M; j++) {
				board[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		max = 0;
		dfs(0, 0, 0);
		System.out.println(max);
	}
	
	static int[][] dr = {{0, -1, 1, 0}, {0, -1, -1, 0}, {0, 1, -1, 0}, {0, 1, 1, 0}};
	static void dfs(int r, int c, int cur) {
		// 부메랑을 더 만들 수 없으면 return
//		for(boolean[] b: visited) {
//			System.out.println(Arrays.toString(b));
//		}
//		System.out.println("=>" + cur + "\n");
		max = Math.max(cur, max);
		boolean canMake = false;
		for(int i = r; i<N; i++) {
			for (int j = (i == r ? c : 0); j < M; j++) {
				if(visited[i][j]) continue;
				for(int d = 0; d<4; d++) {
					int hr = i+dr[d][0], hc = j+dr[d][1];
					int vr = i+dr[d][2], vc = j+dr[d][3];
					
					if(hr < 0 || hr >= N || hc < 0 || hc >= M) continue;
					if(vr < 0 || vr >= N || vc < 0 || vc >= M) continue;
					
					if(!visited[i][j] && !visited[hr][hc] && !visited[vr][vc]){
						int intensity = board[i][j]*2 + board[hr][hc] + board[vr][vc];
						canMake = true;
						visited[i][j] = true;
						visited[hr][hc] = true;
						visited[vr][vc] = true;
						
						dfs(i, j, cur + intensity);
						
						visited[i][j] = false;
						visited[hr][hc] = false;
						visited[vr][vc] = false;
					}
				}
			}
		}
		
		if(!canMake) return;
	}	
}