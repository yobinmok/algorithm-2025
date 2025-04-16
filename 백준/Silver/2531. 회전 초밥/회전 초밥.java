import java.io.*;
import java.util.*;

public class Main{
	
	/*
	 * 같은 초밥이 여러 개 있을 수 있음
	 * 아래와 같은 두 행사를 통해 매상을 올리려 함
	 * 1. k접시를 연속해서 먹으면 할인된 정액가격으로 제공
	 * 2. 1번 행사에 참가하면, 쿠폰에 적힌 초밥을 하나 무료로 제공, 없으면 만들어주슈
	 * 손님이 먹을 수 있는 초밥 가짓수의 최댓값을 구해라.
	 * 
	 * 투포인터, 슬라이딩윈도우
	 * */
	
	static int N, d, k, c, sushi[];
	
	public static void main(String[] args)throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken()); // 벨트에 놓인 접시 수
		d = Integer.parseInt(st.nextToken()); // 초밥 가짓 수, 초밥 종류는 1~d
		k = Integer.parseInt(st.nextToken()); // 연속해서 먹는 접시의 수
		c = Integer.parseInt(st.nextToken()); // 쿠폰번호
		
		sushi = new int[N];
		for(int i = 0; i<N; i++) {
			sushi[i] = Integer.parseInt(br.readLine());
		}
		
		Queue<Integer> select = new ArrayDeque<>();
		int max = 0, cnt = 0;
		for(int idx = 0; idx<N + k; idx++) {
			int i = idx % N;
			if(select.size()<k) {
				if(!select.contains(sushi[i])) cnt += 1;
			}else {
				int pop = select.poll();
				if(!select.contains(pop)) cnt -= 1; // 유일하던 초밥이 빠지면 cnt -= 1
				if(!select.contains(sushi[i])) cnt += 1; // 없던 초밥이 들어오면 cnt += 1
			}
			
			select.add(sushi[i]);
			if(select.size() >= k && !select.contains(c)) max = Math.max(max, cnt + 1); // 쿠폰을 포함하고 있으면 + 1
			else max = Math.max(max, cnt);
		}
		
		System.out.println(max);
	}
}