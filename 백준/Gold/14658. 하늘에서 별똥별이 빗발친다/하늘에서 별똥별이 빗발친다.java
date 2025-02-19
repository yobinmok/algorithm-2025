import java.io.*;
import java.util.*;

public class Main {

	static int N, M, L, K, max;
	static List<int[]> meteor ;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		L = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		meteor = new ArrayList<>();
		for(int i = 0; i<K; i++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			
			meteor.add(new int[] {x, y});
		}
		
		max = 0;
		for(int[] s1: meteor) {
			for(int[] s2: meteor) {
				isContain(s1[0], s2[1]);
			}
		}
		
		System.out.println(K - max);
	}
	
	static void isContain(int x, int y) {
		int cnt = 0;
		for(int[] s: meteor) {
			// 정한 두 별의 범위 내에 있는지 확인
			if(s[0] >= x && s[0] <= x+L &&
					s[1] >= y && s[1] <= y+L) {
				cnt += 1;
			}
		}
		
		max = Math.max(cnt, max);
	}
}
