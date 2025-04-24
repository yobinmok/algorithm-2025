import java.io.*;
import java.util.*;

public class Main {
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int N = Integer.parseInt(br.readLine()); // <= 500,000
		st = new StringTokenizer(br.readLine());
		
		int[] top = new int[N];
		int[] ans = new int[N];
		
		for(int i = 0; i<N; i++) {
			top[i] = Integer.parseInt(st.nextToken());
		}
		
		Stack<Integer> stk = new Stack<>();
		
		stk.add(0);
		for (int i = 1; i < N; i++) {
			while(true) {
				if(stk.isEmpty()) {
					stk.push(i);
					break;
				}else {
					// 스택의 top > 현재위치
					if(top[stk.peek()] > top[i]) {
						ans[i] = stk.peek()+1;
						stk.push(i);
						break;
					}else {
						// 스택의 top < 현재위치
						stk.pop();
					}
				}
			}
		}
		
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < N; i++) {
			sb.append(ans[i] + " ");
		}
		
		System.out.println(sb);
	}
}