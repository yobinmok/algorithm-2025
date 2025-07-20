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
		
		@Override
		public String toString() {
			return n + "번 > " + s + ", " + e;
		}
	}
	
	public static void main(String[] args)throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int N = Integer.parseInt(br.readLine());

		int K = 1;
		int[] timetable = new int[N+1];
		Class[] classes = new Class[N];
		for(int i = 0; i<N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int n = Integer.parseInt(st.nextToken()); // 강의번호
			int s = Integer.parseInt(st.nextToken());
			int e = Integer.parseInt(st.nextToken());
			
			classes[i] = new Class(n, s, e);
		}
		
		Arrays.sort(classes);
		
		PriorityQueue<int[]> temp = new PriorityQueue<>((int[] n1, int[] n2) -> {
			return n1[1] - n2[1];
		});
		
		for(int i = 0; i<N; i++) {
			if(temp.isEmpty()) {
				classes[i].order = K;
				timetable[classes[i].n] = K++;
				temp.add(new int[] {i, classes[i].e});
			}else {
				int[] e = temp.peek();
				if(classes[i].s  >= e[1]) { // 재사용
					temp.poll();
					timetable[classes[i].n] = classes[e[0]].order;
					classes[i].order = classes[e[0]].order;
					temp.add(new int[] {i, classes[i].e});
				}else { // 새로운 강의실
					classes[i].order = K;
					timetable[classes[i].n] = K++;
					temp.add(new int[] {i, classes[i].e});
				}
			}
		}
		
		sb.append(K-1 + "\n");
		for(int i = 1; i<=N; i++) {
			sb.append(timetable[i] + "\n");
		}
		
		System.out.println(sb);
	}
}