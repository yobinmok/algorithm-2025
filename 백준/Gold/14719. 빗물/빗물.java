import java.io.*;
import java.util.*;

public class Main{

    static int N, M, sum, board[][];
	static int[] dr = {-1, 1};
	public static void main(String[] args)throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		M = Integer.parseInt(st.nextToken()); // 세로
		N = Integer.parseInt(st.nextToken()); // 가로
		board = new int[N][M];
		
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i<N; i++) {
			int s = Integer.parseInt(st.nextToken());
			for(int k = 0; k<s; k++) {
				board[i][k] = 1;
			
			}
		}
		
		sum = 0;
		for(int i = 0; i<M; i++) {
			for(int j = 0; j<N; j++) {
				if(board[j][i] != 0) continue;
				fill(j, i);
			}
		}
		
		System.out.println(sum);
	}
	
	static void fill(int i, int j) {
		Queue<int[]> que = new ArrayDeque<>();
		que.add(new int[] {i, j});
		board[i][j] = 2;
		int cnt = 1;
		
		boolean impossible = false;
		while(!que.isEmpty()) {
			int[] cur = que.poll();
			for(int d = 0; d<2; d++) {
				int nr = cur[0] + dr[d];				
				if(nr < 0 || nr >= N) {
					impossible = true;
					continue;
				}
				if(board[nr][cur[1]] != 0) continue;
				
				cnt += 1;
				board[nr][cur[1]] = 2;
				que.add(new int[] {nr, cur[1]});
			}
		}
		
		if(!impossible) sum += cnt;
	}
}