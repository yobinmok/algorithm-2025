import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int n = Integer.parseInt(br.readLine());
		
		// 높이와 높이별 연결여부에 따라 처리
		// 이전 높이가 자신보다 낮으면 그 층보다 높은층들은 연결여부 false
		int ans = 0, prev = 0;
		boolean[] visited = new boolean[500001];
		visited[0] = true;
		
		Stack<Integer> stk = new Stack<>();
		for(int i = 0; i<n; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken()); // 무조건 오름차순으로 입력됨
			int y = Integer.parseInt(st.nextToken()); // 연결여부 확인, 같은 건물인지 확인
			
			if(prev > y) {
				while(stk.size() > 0 && stk.peek() > y) {
					visited[stk.pop()] = false;
				}
			}
			
			if(!visited[y]) {
				ans += 1;
				visited[y] = true;
				stk.add(y);
			}
			
			prev = y;
		}
		
		System.out.println(ans);
	}
}
