import java.io.*;
import java.util.*;

public class Main {
	
	static class Node{
		int dest, cost;
		
		Node(int dest, int cost){
			this.dest = dest;
			this.cost = cost;
		}
		
		@Override
		public String toString() {
			return dest + " " + cost;
		}
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine()); // 도시 개수
		int M = Integer.parseInt(br.readLine()); // 버스 개수
		
		List<Node>[] graph = new ArrayList[N];
		boolean[] visited = new boolean[N];
		int[] distance = new int[N];
		
		for(int i = 0; i<N; i++) {
			graph[i] = new ArrayList<>();
		}
		
		for(int i = 0; i<M; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int d = Integer.parseInt(st.nextToken())-1;
			int a = Integer.parseInt(st.nextToken())-1;
			int c = Integer.parseInt(st.nextToken());
			graph[d].add(new Node(a, c));
		}
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		int depart = Integer.parseInt(st.nextToken())-1;
		int arrive = Integer.parseInt(st.nextToken())-1;
		
		
		
//		해당 정점에서 이어진 모든 선으로 이동
//		visited 처리 -> 어케하지..!
//		** 유의: 한 정점에서 다른 정점으로 이동 시 최소 비용 노드를 우선처리 해야함!
		
		// 초기화
		Arrays.fill(distance, Integer.MAX_VALUE);
		distance[depart] = 0;
		
		PriorityQueue<Node> que = new PriorityQueue<>((o1, o2) -> {
			return o1.cost - o2.cost;
		});
		que.add(new Node(depart, 0));
		
		while(!que.isEmpty()) {
			Node now = que.poll();
			
			if(visited[now.dest]) continue;
			visited[now.dest] = true;
			
			for(Node node: graph[now.dest]) {
				if(distance[node.dest] > distance[now.dest] + node.cost) {
					distance[node.dest] = distance[now.dest] + node.cost;
					que.add(new Node(node.dest, distance[node.dest]));
				}
			}
		}
		
//		System.out.println(Arrays.toString(distance));
		System.out.println(distance[arrive]);
	}
}
