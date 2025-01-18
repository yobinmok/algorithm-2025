import java.io.*;
import java.util.*;

public class Main {

	
	static int cnt;
	static int[] parent;
	static boolean[] know;
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken()); // 사람 수 
		int M = Integer.parseInt(st.nextToken()); // 파티 수
		
		st = new StringTokenizer(br.readLine());
		int num = Integer.parseInt(st.nextToken()); // 아는 사람 수
		know = new boolean[N];
		parent = new int[N];
		for(int i = 0; i<num; i++) {
			know[Integer.parseInt(st.nextToken())-1] = true;
		}
		
		// initialize parent
		for(int i = 0; i<N; i++) {
			parent[i] = i;
		}
		
		cnt = M;
		/*
		 * 한 파티에 있는 사람들은 무조건 union
		 * */
		List<Integer>[] parties = new ArrayList[M];
		for(int i = 0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			int n = Integer.parseInt(st.nextToken());
			int p = Integer.parseInt(st.nextToken())-1;
			
			parties[i] = new ArrayList<>();
			parties[i].add(p);
			for(int j = 0; j<n-1; j++) {
				int temp = Integer.parseInt(st.nextToken())-1;
				union(p, temp);
				parties[i].add(temp);
			}
		}
		
		for(int i = 0; i<M; i++) {
			for(int participant: parties[i]) {
				if(know[find(participant)]) {
					cnt -= 1;
					break;
				}
			}
		}
		
		System.out.println(cnt);
	}
	
	static boolean union(int a, int b) {
		int aP = find(a);
		int bP = find(b);
				
		if(aP == bP) return false; // 같은 부모를 가짐
		
		// know -> 작은 수를 부모로 둠
		if((know[aP] && know[bP])|| (!know[aP] && !know[bP])) {
			if(aP <= bP) parent[bP] = aP;
			else parent[aP] = bP;
		}else if(know[aP]) {
			parent[bP] = aP;
		}else if(know[bP]){
			parent[aP] = bP;
		}
		return true;
	}

	static int find(int n) { // 자신의 부모 반환
		if(parent[n] == n) return n;
		return find(parent[n]); // find(n)으로 하면 안된다..
	}
}

