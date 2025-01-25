import java.io.*;
import java.util.*;

public class Main {

	static int n, maxDistance, maxNode;
	static List<Node>[] graph;
	static boolean[] visited;
	
	static class Node{
		int d, c;
		Node(int d, int c){
			this.d = d;
			this.c = c;
		}
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		n = Integer.parseInt(br.readLine()); // 노드의 개수
		graph = new ArrayList[n];
		
		for(int i = 0; i<n; i++) {
			graph[i] = new ArrayList<>();
		}
		
		for(int i = 0; i<n-1; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int p = Integer.parseInt(st.nextToken())-1; // 부모(작은 것 부터)
			int c = Integer.parseInt(st.nextToken())-1; // 자식
			int cost = Integer.parseInt(st.nextToken());
			graph[p].add(new Node(c, cost));
			graph[c].add(new Node(p, cost));
		}
		
		visited = new boolean[n];
		visited[0] = true;
		maxDistance = 0;
		maxNode = 0;
		dfs(0, 0);
		
		visited = new boolean[n];
		visited[maxNode] = true;
		maxDistance = 0;
		dfs(maxNode, 0);
		
		System.out.println(maxDistance);
	}

	static void dfs(int node, int cost) {
		if(cost > maxDistance) {
			maxDistance = cost;
			maxNode = node;
		}
		
		List<Node> list = graph[node];
		
		for(Node n: list) {
			if(visited[n.d]) continue;
			visited[n.d] = true;
			
			dfs(n.d, cost + n.c);
			
		}
	}
}
