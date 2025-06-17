import java.io.*;
import java.util.*;

public class Main{
	
	static int N;
	static String[][] board;
	static List<int[]> tch;
	static int[] dr = {1, -1, 0, 0}, dc = {0, 0, 1, -1};
	static String answer = "NO";
	
	public static void main(String[] args)throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		board = new String[N][N];
		tch = new ArrayList<>();
		for(int i = 0; i<N; i++) {
			board[i] = br.readLine().split(" ");
			for(int j = 0; j<N; j++) {
				if(board[i][j].equals("T")) tch.add(new int[] {i, j});
			}
		}
		
		comb(0, 0);
		System.out.println(answer);
	}
	
	static void comb(int n, int o) {
		if(o == 3) {
			boolean possible = true;
			for(int[] t: tch) {
				if(canSeeStudent(t[0], t[1])) possible = false;
			}
			if(possible) answer = "YES";
			return;
		}
		
		for(int i = n; i<N*N; i++) {
			if(!board[i/N][i%N].equals("X")) continue;
			board[i/N][i%N] = "O";
			comb(n+1, o+1);
			board[i/N][i%N] = "X";
		}	
	}
	
	static boolean canSeeStudent(int r, int c) {
		int nr = r;
		int nc = c;
		
		for(int d = 0; d<4; d++) {
			nr += dr[d];
			nc += dc[d];
			while(nr >= 0 && nr < N && nc >= 0 && nc < N) {
				if(board[nr][nc].equals("O")) {
					break;
				}else if(board[nr][nc].equals("S")) {
					return true;
				}
				nr += dr[d];
				nc += dc[d];
			}
			nr = r;
			nc = c;
		}
		
		return false;
	}
}