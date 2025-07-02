import java.awt.Point;
import java.io.*;
import java.util.*;

public class Main{
	
	/*
	 *  길이 아닌 인접한 목초지는 자유롭게 건널 수 있다.
	 *  다리를 건너지 않으면 만나지 못하는 소의 쌍 개수를 구해라.
	 * */
	
	static Point[] cowList ;
	static boolean[] cowChecked;
	static List<Integer>[][] bridge;
	static int N, K, R, answer, cow[][];
	static int[] dr = {-1, 0, 1, 0}, dc = {0, 1, 0, -1}; // 북쪽부터 시계방향
	
	public static void main(String[] args)throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken()); // <= 100
		K = Integer.parseInt(st.nextToken()); // 소 <= 100
		R = Integer.parseInt(st.nextToken()); // 다리
		
		cow = new int[N][N];
		bridge = new ArrayList[N][N];
		for(int i = 0; i<R; i++) {
			st = new StringTokenizer(br.readLine());
			int r1 = Integer.parseInt(st.nextToken())-1;
			int c1 = Integer.parseInt(st.nextToken())-1;
			int r2 = Integer.parseInt(st.nextToken())-1;
			int c2 = Integer.parseInt(st.nextToken())-1;
			// 다리의 위치에 지어진 방향 저장
			for(int d = 0; d<4; d++) {
				int nr = r1 + dr[d];
				int nc = c1 + dc[d];
				if(nr == r2 && nc == c2) {
					if(bridge[r1][c1] == null) bridge[r1][c1] = new ArrayList<>();
					if(bridge[r2][c2] == null) bridge[r2][c2] = new ArrayList<>();
					
					bridge[r1][c1].add(d);
					bridge[r2][c2].add(d>=2 ? d-2 : d+2);
					break;
				}
			}
		}
		
		// 소의 쌍 kC2
		cowList = new Point[K];
		for(int i = 0; i<K; i++) {
			st = new StringTokenizer(br.readLine());
			int r = Integer.parseInt(st.nextToken())-1;
			int c = Integer.parseInt(st.nextToken())-1;
			cowList[i] = new Point(r, c);
			cow[r][c] = i;
		}
		
		answer = K * (K-1) / 2;
		cowChecked = new boolean[K];
		for(int i = 0; i<K; i++) {
			cowChecked[i] = true;
			isConnected(cowList[i]);
		}
		
		System.out.println(answer);
	}
	
	
	static boolean isConnected(Point s) {
		Queue<Point> que = new ArrayDeque<>();
		boolean[][] visited = new boolean[N][N];
		
		que.add(s);
		visited[s.x][s.y] = true;
		
		while(!que.isEmpty()) {
			Point cur = que.poll();
			
			if(cow[cur.x][cur.y] > 0 && !cowChecked[cow[cur.x][cur.y]]) {
				answer -= 1;
			}
			
			boolean[] hasBridge = new boolean[4];
			if(bridge[cur.x][cur.y] != null) {
				for(int bri: bridge[cur.x][cur.y]) {
					hasBridge[bri] = true;
				}
			}

			for(int d = 0; d<4; d++) { // 0123
				if(hasBridge[d]) continue;
				int nr = cur.x + dr[d];
				int nc = cur.y + dc[d];
				
				if(nr < 0 || nr >= N || nc < 0 || nc >= N || visited[nr][nc]) continue;
				
				visited[nr][nc] = true;
				que.add(new Point(nr, nc));
			}
		}
		
		return false;
	}
}