import java.io.*;
import java.util.*;

public class Main{
	
	/*
	 * 최대 몇 번이나 임스와 함께 게임을 플레이할 수 있는가
	 * 한번 플레이한 사람과는 다시 플레이하지 않는다
	 * */
	
	public static void main(String[] args)throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int num = 0;
		String t = st.nextToken();
		
		if(t.equals("Y")) {
			num = 1;
		}else if(t.equals("F")) {
			num = 2;
		}else {
			num = 3;
		}
		
		Set<String> played = new HashSet<>();
		int cur = 0, max = 0;
		for(int i = 0; i<N; i++) {
			String name = br.readLine();
			if(!played.contains(name)) {
				cur += 1;
				played.add(name);
			}
			
			if(cur == num) {
				cur = 0;
				max += 1;
			}
		}
		
		System.out.println(max);
	}
}