import java.io.*;
import java.util.*;

public class Main {

	/*
	 * 합집합 연산과 두 원소가 같은 집합에 포함되어 있는지를 확인하는 연산을 수행하려 한다.
	 * 
	 * 두 원소가 같은 집합에 포함되어 있는지 확인 -> union find 활용
	 * 타 그래프 탐색 알고리즘으로도 가능은 하지만 주어진 숫자가 크기 때문에 union find를 활용해야 함.
	 * 
	 * */
	
	static int[] parent;
	
	public static void main(String[] args)throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		parent = new int[n+1];
		
		for(int i = 0; i<n+1; i++) {
			parent[i] = i;
		}
		
		for(int i = 0; i<m; i++) {
			st = new StringTokenizer(br.readLine());
			int command = Integer.parseInt(st.nextToken());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			
			if(command == 0) {
				union(a, b);
			}else {
				sb.append(isContain(a, b) ? "YES" : "NO");
				sb.append("\n");
			}
		}
		System.out.println(sb);
	}
	
	static boolean isContain(int a, int b) {
		return find(a) == find(b);
	}
	
	static void union(int a, int b) {
		a = find(a);
		b = find(b);
		
		if(a > b) parent[b] = a;
		else parent[a] = b;
	}
	
	static int find(int a) {
		if(parent[a] == a) return a;
		return find(parent[a]);
	}
}


