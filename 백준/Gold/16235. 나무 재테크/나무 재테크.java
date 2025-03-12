import java.io.*;
import java.util.*;

public class Main {	
	
	static int N, M, K, nutri[][], feed[][];
	static PriorityQueue<Integer>[][] tree;
	static int[] dr = {0, -1, -1, -1, 0, 1, 1, 1}, dc = {-1, -1, 0, 1, 1, 1, 0, -1}; 
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken()); // 나무개수
		K = Integer.parseInt(st.nextToken()); // K년
		
		nutri = new int[N][N];
		feed = new int[N][N];
		tree = new PriorityQueue[N][N];
		for(int i = 0; i<N; i++) {
			for(int j = 0; j<N; j++) {
				tree[i][j] = new PriorityQueue<Integer>();
				nutri[i][j] = 5;
			}
		}
		
		for(int i = 0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j<N; j++) {
				feed[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		for(int i = 0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			int r = Integer.parseInt(st.nextToken())-1;
			int c = Integer.parseInt(st.nextToken())-1;
			int age = Integer.parseInt(st.nextToken());
			tree[r][c].add(age);
		}
		
		for(int i = 0; i<K; i++) {
			// 봄여름
			for(int r = 0; r<N; r++) {
				for(int c = 0; c<N; c++) {
					springNsummer(r, c);
				}
			}
			
			// 가을겨울
			for(int r = 0; r<N; r++) {
				for(int c = 0; c<N; c++) {
					fallNwinter(r, c);
				}
			}
		}
		
		int sum = 0;
		for(PriorityQueue<Integer>[] arr: tree) {
			for(PriorityQueue<Integer> que: arr) {
				sum += que.size();
			}
		}
		
		System.out.println(sum);
	}
	
	static void springNsummer(int r, int c) {
		PriorityQueue<Integer> temp = new PriorityQueue<>();
		int die = 0;
		
		while(!tree[r][c].isEmpty()) {
			int age = tree[r][c].poll();
			if(nutri[r][c] >= age) {
				nutri[r][c] -= age;
				temp.add(age+1);
			}else {
				die += age/2;
			}
		}
		
		tree[r][c] = temp;
		nutri[r][c] += die;
	}
	
	static void fallNwinter(int r, int c) {
		for(int age: tree[r][c]) {
			if(age % 5 == 0) {
				for(int d = 0; d<8; d++) {
					int nr = r + dr[d];
					int nc = c + dc[d];
					
					if(nr < 0 || nr >= N || nc < 0 || nc >= N) continue;
					
					tree[nr][nc].add(1);
				}
			}
		}
		
		nutri[r][c] += feed[r][c];
	}
}
