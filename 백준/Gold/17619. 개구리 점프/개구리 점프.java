import java.io.*;
import java.util.*;

public class Main{
	
	static int N, parent[];
	
	public static void main(String[] args)throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
		
		N = Integer.parseInt(st.nextToken());
		int Q = Integer.parseInt(st.nextToken());
		
		parent = new int[N+1];
		for(int i = 1; i<=N; i++) {
			parent[i] = i;
		}

		int[][] wood = new int[N+1][4];
		for(int i = 1; i<=N; i++) {
			st = new StringTokenizer(br.readLine());
			int s = Integer.parseInt(st.nextToken());
			int e = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			
			wood[i][0] = s;
			wood[i][1] = e;
			wood[i][2] = y;
			wood[i][3] = i;
		}
		
		Arrays.sort(wood, (int[] n1, int[] n2) -> {
			return n1[0] - n2[0];
		});
		
		for(int i = 1; i<=N; i++) {
			for(int j = i+1; j<=N; j++) {
				if(wood[i][1] >= wood[j][0]) {
					union(wood[i][3], wood[j][3]);
				}
			}
		}
		
		for(int i = 0; i<Q; i++) {
			st = new StringTokenizer(br.readLine());
			int s = Integer.parseInt(st.nextToken());
			int e = Integer.parseInt(st.nextToken());
			
			if(find(s) == find(e)) sb.append("1\n");
			else sb.append("0\n");
		}
		
		System.out.println(sb);
	}
	
	
	static void union(int n1, int n2) {
		// 작은 숫자를 부모로
		int p1 = find(n1);
		int p2 = find(n2);
		
		if(p1 > p2) {
			parent[p1] = p2;
		}else if(p1 < p2) {
			parent[p2] = p1;
		}
	}
	
	static int find(int n) {
		if(parent[n] == n) return n;
		return parent[n];
	}
	
}