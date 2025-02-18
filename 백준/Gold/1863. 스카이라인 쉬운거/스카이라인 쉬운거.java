import java.io.*;
import java.util.*;

public class Main {
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int n = Integer.parseInt(br.readLine());
		
		Set<Integer> height = new HashSet<>();
		int ans = 0, prev = 0;
		boolean[] isConnected = new boolean[500001];
		Arrays.fill(isConnected, true);
		
		for(int i = 0; i<n; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken()); // 무조건 오름차순으로 입력됨
			int y = Integer.parseInt(st.nextToken()); // 연결여부 확인, 같은 건물인지 확인
			
			if(y == 0) { // 건물이 없는 경우
				height.clear();
				continue;
			}
			
			if(prev > y) {
				for(int t: height) {
					if(t > y)
						isConnected[t] = false;
				}
			}
			
			if(!height.contains(y) || !isConnected[y]) {
				ans += 1;
				height.add(y);
				isConnected[y] = true;
			}
			
			prev = y;
		}
		
		System.out.println(ans);
	}
}
