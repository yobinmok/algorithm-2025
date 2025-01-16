import java.io.*;
import java.util.*;

public class Main {

	
	static int N, M;
	static int[] ladder, snake;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken()); // 사다리
		M = Integer.parseInt(st.nextToken()); // 뱀
		
		ladder = new int[101];
		snake = new int[101];
		for(int i = 0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			ladder[Integer.parseInt(st.nextToken())] = Integer.parseInt(st.nextToken());
		}
		
		for(int i = 0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			snake[Integer.parseInt(st.nextToken())] = Integer.parseInt(st.nextToken());
		}
		
		System.out.println(bfs());
	}
	
	static int bfs() {
		Queue<Integer[]> que = new ArrayDeque<>();
		boolean[] visited = new boolean[101];
		
		visited[1] = true;
		que.add(new Integer[] {1, 0}); // [n, m] = 현재 칸, 이동횟수
		
		while(!que.isEmpty()) {
			Integer[] temp = que.poll();
			
			if(temp[0] == 100) {
				return temp[1];
			}
			
			for(int dice = 1; dice<7; dice++) {
				int next = temp[0] + dice;
				
				if(next > 100) break;
				
				if(ladder[next] != 0) next = ladder[next];
				else if(snake[next] != 0) next = snake[next];
				
				if(visited[next]) continue;
				
				visited[next] = true;
				que.add(new Integer[] {next, temp[1] + 1});
			}
		}
		
		return 0;
	}
}
