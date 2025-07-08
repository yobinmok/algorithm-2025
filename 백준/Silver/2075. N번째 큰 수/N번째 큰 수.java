import java.io.*;
import java.util.*;

public class Main{
	
	
	public static void main(String[] args)throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine()); // N번째 큰 수
		
		PriorityQueue<Integer> que = new PriorityQueue<>(Comparator.reverseOrder());
		for(int i = 0; i<N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			while(st.hasMoreTokens()) {
				que.add(Integer.parseInt(st.nextToken()));
			}
		}
		
		for(int i = 0; i<N-1; i++) {
			que.poll();
		}
		
		System.out.println(que.poll());
	}
	
}