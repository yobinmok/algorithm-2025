import java.io.*;
import java.util.*;

public class Main{
	
	public static void main(String[] args)throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		int[][] poles = new int[N][2];
		int max = 0;
		for(int i = 0; i<N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int L = Integer.parseInt(st.nextToken());
			int H = Integer.parseInt(st.nextToken());
			poles[i][0] = L;
			poles[i][1] = H;
			max = Math.max(max, H);
		}
		
		Arrays.sort(poles, (int[] a1, int[] a2) -> {
			return a1[0] - a2[0];
		});
		
		Stack<Integer> stk = new Stack<>(); // 기둥의 인덱스
		boolean maxHeight = false;
		for(int i = 0; i<N; i++) {
			if(stk.isEmpty()) {
				stk.push(i);
			}else {
				int[] peek = poles[stk.peek()];
				if(maxHeight) { // 내림차순
					if(peek[1] <= poles[i][1]) {
						while(!stk.isEmpty() && poles[stk.peek()][1] < poles[i][1]) {
							stk.pop();
						}
					}
					stk.push(i);
				}else { // 오름차순
					if(peek[1] < poles[i][1]) stk.push(i);
					if(poles[i][1] == max) maxHeight = true;
				}
			}
		}
		 
		
		int sum = (poles[N-1][0] - poles[0][0] + 1) * max;
		int[] p = new int[stk.size()];
		for(int i = stk.size()-1; i>=0; i--) {
			p[i] = stk.pop();
		}
		
		maxHeight = false;
		for(int i = 0; i<p.length-1; i++) {
			if(poles[p[i]][1] == max) {
				maxHeight = true;
			}
			
			int w = poles[p[i+1]][0] - poles[p[i]][0], h = 0;
			if(maxHeight) {
				h = max - poles[p[i+1]][1];
			}else {
				h = max - poles[p[i]][1];
			}
			
			sum -= w*h;
		}
		System.out.println(sum);
	}
}