import java.io.*;
import java.util.*;

public class Main{
	
	/*
	 * 격자 밖으로 나간 모래의 양 구하기
	 * y로 토네이도가 이동하면 y의 모든 모래가 a와 비율이 적혀있는 칸으로 이동
	 * - 비율: y의 모래에 쓰여진 비율만큼 이동
	 * - a: 비율 이동 후 남은 양
	 * */
	
	static int N, sum;
	static int[] dr = {0, 1, 0, -1}, dc = {-1, 0, 1, 0};
	static int[][] board;
	static int[][][] tornado = {
			{{0, 0, 2, 0, 0}, {0, 10, 7, 1, 0}, {5, -1, 0, 0, 0}, {0, 10, 7, 1, 0}, {0, 0, 2, 0, 0}},
			{{0, 0, 0, 0, 0}, {0, 1, 0, 1, 0}, {2, 7, 0, 7, 2}, {0, 10, -1, 10, 0}, {0, 0, 5, 0, 0}},
			{{0, 0, 2, 0, 0}, {0, 1, 7, 10 ,0}, {0, 0, 0, -1, 5}, {0, 1, 7, 10, 0}, {0, 0, 2, 0, 0}},
			{{0, 0, 5, 0, 0}, {0, 10, -1, 10, 0}, {2, 7, 0, 7, 2}, {0, 1, 0, 1, 0}, {0, 0, 0, 0, 0}}
	};
	
	public static void main(String[] args)throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		board = new int[N][N];
		for(int i = 0; i<N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for(int j = 0; j<N; j++) {
				board[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		int r = N/2, c = N/2, step = 1, cur = 0, dir = 0;
		while(r != 0 || c != 0) {
			for(int s = 0; s<step; s++) {
				r = r + dr[dir%4];
				c = c + dc[dir%4];
				
				move(r, c, dir%4);
				if(r == 0 && c == 0) break; // 마지막 이동의 경우 step이 더 크기 때문에 그 전에 break 해주는 역할
			}
			
			dir += 1;
			cur += 1;
			
			if(cur == 2) {
				step += 1;
				cur = 0;
			}
		}
		
		System.out.println(sum);
	}
	
	static void move(int r, int c, int d) {
		int sand = board[r][c];
		int ratio = 0;
		
		// 비율 처리
		for(int i = 0; i<5; i++) {
			for(int j = 0; j<5; j++) {
				if(tornado[d][i][j] == 0) continue;
				int nr = r + (i - 2);
				int nc = c + (j - 2);
				
				double percent = (double) tornado[d][i][j] / 100;
				int out = (int)(sand * percent);
				
				if(nr < 0 || nr >= N || nc < 0 || nc >= N) {
					sum += out;
				}else {
					board[nr][nc] += out;
				}
				ratio += out;
			}
		}
		
		// alpha 처리
		int nr = r + dr[d];
		int nc = c + dc[d];
		if(nr < 0 || nr >= N || nc < 0 || nc >= N) {
			sum += (sand - ratio);
		}else {
			board[nr][nc] += (sand - ratio);
		}
		board[r][c] = 0;
	}
}