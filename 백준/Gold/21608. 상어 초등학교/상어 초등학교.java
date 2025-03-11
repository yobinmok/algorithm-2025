import java.io.*;
import java.util.*;

public class Main {

	static int N, order[], stu[][], classroom[][];
	static int[] dr = {1, -1, 0, 0}, dc = {0, 0, 1, -1};
	static class Seat{ // N <= 20이라 우선순위큐 쓰려는 것..
		int r, c, fav, empty;
		Seat(int r, int c, int fav, int empty){
			this.r = r;
			this.c = c;
			this.fav = fav;
			this.empty = empty;
		}
		@Override
        public String toString() {
            return r + ", " + c + ", fav: " + fav + ", empty: " + empty;
        }
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		
		stu = new int[N*N+1][4];
		order = new int[N*N];
		classroom = new int[N][N];
		for(int i = 0; i<N*N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int s = Integer.parseInt(st.nextToken());
			order[i] = s;
			for(int j = 0; j<4; j++) {
				stu[s][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		for(int n: order) {
			findSeat(n);
		}
		
		int sum = 0;
		for(int i = 0; i<N; i++) {
			for(int j = 0; j<N; j++) {
				int std = classroom[i][j];
				int cnt = 0;
				for(int d = 0; d<4; d++) {
					int nr = i + dr[d];
					int nc = j + dc[d];
					if(nr < 0 || nr >= N || nc < 0 || nc >= N) continue;
					
					for(int f: stu[std]) {
						if(classroom[nr][nc] == f) cnt += 1;
					}
				}
				sum += cnt == 0 ? 0 : Math.pow(10, cnt-1);
			}
		}
		
		System.out.println(sum);
	}
	
	static void findSeat(int s) {
		PriorityQueue<Seat> que = new PriorityQueue<>((o1, o2)->{
			if(o1.fav == o2.fav) {
				if(o1.empty == o2.empty) {
					if(o1.r == o2.r) {
						return o1.c - o2.c;
					}
					return o1.r - o2.r;
				}
				return o2.empty - o1.empty;
			}
			return o2.fav - o1.fav;
		});
		
		
		for(int i = 0; i<N; i++) {
			for(int j = 0; j<N; j++) {
				if(classroom[i][j] != 0) continue;
				
				Seat temp = new Seat(i, j, 0, 0);
				for(int d = 0; d<4; d++) {
					int nr = i + dr[d];
					int nc = j + dc[d];
					if(nr < 0 || nr >= N || nc < 0 || nc >= N) continue;
					
					if(classroom[nr][nc] == 0) {
						temp.empty += 1;
					}else {
						for(int f: stu[s]) {
							if(classroom[nr][nc] == f) temp.fav += 1;
						}
					}
				}
				que.add(temp);
			}
		}
		
		if(que.size() > 0 ) {
			Seat t = que.poll();
			classroom[t.r][t.c] = s;
		}
	}
}
