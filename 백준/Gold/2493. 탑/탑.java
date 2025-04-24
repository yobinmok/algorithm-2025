import java.io.*;
import java.util.*;

public class Main{
	
	/*
	 * 레이저 신호는 수평 직선의 왼쪽 방향으로 발사
	 * 가장 먼저 만나는 단 하나의 탑에서만 수신 가능
	 * 
	 * 스택 사용
	 * */
	public static void main(String[] args)throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine());
		int[] top = new int[N];
		int[] ans = new int[N];
		for(int i = 0; i<N; i++) {
			top[i] = Integer.parseInt(st.nextToken());
		}
		
		Stack<Integer> stk = new Stack<>();
		for(int i = N-1; i>=0; i--) {
			while(!stk.isEmpty() && top[stk.peek()] < top[i]) {
				ans[stk.pop()] = i+1;
			}
			stk.push(i);
		}
		
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < N; i++) {
			sb.append(ans[i] + " ");
		}
		
		System.out.println(sb);
	}
}