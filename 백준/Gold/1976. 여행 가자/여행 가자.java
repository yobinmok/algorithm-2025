import java.io.*;
import java.util.*;

public class Main {

	// 그래프
	public static int[] set;

	public static void main(String[] args) throws NumberFormatException, IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int N = Integer.parseInt(br.readLine());
		int M = Integer.parseInt(br.readLine());

		set = new int[N+1]; // Union Find용 집합
		
		for(int i = 1; i<=N; i++) {
			set[i] = i;
		}
		
		for(int i = 1; i<=N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 1; j<=N; j++) {
				if(Integer.parseInt(st.nextToken()) == 1) {
					union(i, j);
				}
			}
		}
		
		st = new StringTokenizer(br.readLine());
		int start = Integer.parseInt(st.nextToken());
		for(int i = 1; i<M; i++) {
			int now = Integer.parseInt(st.nextToken());
			if (find(start) != find(now)) {
				System.out.println("NO");
				return;
			}
		}
		System.out.println("YES");		
		
	}
	
	public static int find(int x) {
		// 자신의 최상위 노드
		if(set[x] == x)
			return x;
		return set[x] = find(set[x]);
	}
	
	public static void union(int x, int y) {
		// 합병 -> 큰 수 대입
		x = find(x);
		y = find(y);
		if(x != y) {
			if(x > y) set[y] = x;
			else set[x] = y;
		}
	}
}