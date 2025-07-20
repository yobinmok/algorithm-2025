import java.io.*;
import java.util.*;

public class Main{
	
		
	static class Class implements Comparable<Class>{
		int n, s, e, order;
		
		Class(int n, int s, int e){
			this.n = n;
			this.s = s;
			this.e = e;
		}

		@Override
		public int compareTo(Class o) {
			if(this.s == o.s) {
				return this.e - o.e;
			} return this.s - o.s;
		}
	}
	
	public static void main(String[] args)throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());

		int K = 0;
		Class[] classes = new Class[N];
		for(int i = 0; i<N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int n = Integer.parseInt(st.nextToken()); // 강의번호
			int s = Integer.parseInt(st.nextToken());
			int e = Integer.parseInt(st.nextToken());
			
			classes[i] = new Class(n, s, e);
		}
		
		Arrays.sort(classes);
		
		PriorityQueue<Integer> temp = new PriorityQueue<>();
		for(int i = 0; i<N; i++) {
			if(temp.isEmpty()) {
				temp.add(classes[i].e);
			}else {
				int cur = temp.peek();
				if(classes[i].s >= cur) { // 기존 강의실 배정
					temp.poll();
					temp.add(classes[i].e);
				}else {
					K += 1;
					temp.add(classes[i].e);
				}
			}
		}
		
		System.out.print(K+1);
	}
}