import java.io.*;
import java.util.*;

public class Main {

	static int N, min;
	static boolean goal[];
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine()); // <= 100,000
		
		String str = br.readLine();
		boolean[] light1 = new boolean[N];
		boolean[] light2 = new boolean[N];
		goal = new boolean[N];
		for(int i = 0; i<N; i++) {
			light1[i] = str.charAt(i) == '0';
			light2[i] = str.charAt(i) == '0';
		}
		
		str = br.readLine();
		for(int i = 0; i<N; i++) {
			goal[i] = str.charAt(i) == '0';
		}
		
		min = Integer.MAX_VALUE;
		greedy(1, 0, light1);
		greedy(1, 1, useSwitch(0, light2));
		
		System.out.println(min == Integer.MAX_VALUE ? -1 : min);
	}
	
	static void greedy(int n, int cnt, boolean[] cur) {
		if(n == N) {
			if(Arrays.equals(cur, goal)) {
				min = Math.min(cnt, min);
			}
			return;
		}
		
		if(cur[n-1] != goal[n-1]) { // i를 바꾸고싶으면 i+1을 눌러라 -> 현재의 변경이 이전값에 영향을 주면 안됨
			greedy(n+1, cnt+1, useSwitch(n, cur));
		}else {
			greedy(n+1, cnt, cur);
		}
	}
	
	static boolean[] useSwitch(int n, boolean[] cur) {
		if(n != 0) cur[n-1] = !cur[n-1];
		cur[n] = !cur[n];
		if(n != N-1) cur[n+1] = !cur[n+1];
		
		return cur;
	}
}

