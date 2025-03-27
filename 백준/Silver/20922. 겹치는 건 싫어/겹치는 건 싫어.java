import java.io.*;
import java.util.*;

public class Main{
	
	public static void main(String[] args)throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken()); // 같은 원소가 K개 이하..
		int max = 0, SIZE = 100000;
		
		int[] cnt = new int[SIZE+1];
		Queue<Integer> que = new ArrayDeque<>();
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i<N; i++) {
			int n = Integer.parseInt(st.nextToken());
			
			if(cnt[n] == K) {
				while(que.peek() != n) {
					cnt[que.poll()] -= 1;
				}
				que.poll();
			}else {
				cnt[n] += 1;
			}
			que.add(n);
			
			max = Math.max(max, que.size());
		}
		
		System.out.println(max);
	}
}