import java.io.*;
import java.util.*;

public class Main {
	
	static class Node{
		int u, v, cost;
		Node(int u, int v, int cost){
			this.u = u;
			this.v = v;
			this.cost = cost;
		}
	}
	
	static int N, M, W, edge, distance[], INF = Integer.MAX_VALUE; 
	static ArrayList<Node> graph;
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int TC = Integer.parseInt(br.readLine());
		for(int t = 0; t<TC; t++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			W = Integer.parseInt(st.nextToken());
			
			// 간선 그래프
			graph = new ArrayList<>();
			 for (int i = 0; i < M + W; i++) {            
				 st = new StringTokenizer(br.readLine());            
				 int S = Integer.parseInt(st.nextToken())-1;            
				 int E = Integer.parseInt(st.nextToken())-1;            
				 int T = Integer.parseInt(st.nextToken());                        
				 if (i >= M) {                
					 graph.add(new Node(S, E, -T));             
				 } else {                
					 graph.add(new Node(S, E, T));                 
					 graph.add(new Node(E, S, T));             
				 }                    
			 }
			
			distance = new int[N];
			
			sb.append(bellman() ? "YES" : "NO").append("\n");
		}
		System.out.println(sb);
	}
	
	static boolean bellman() { // 음수사이클이 있으면 true		
		for(int i = 0; i<N; i++) { // N-1번 동안 최단거리 초기화 작업
			for(Node road: graph) { // 모든 간선 확인
				if(distance[road.u] == INF) continue; // 아직 이어진 길이 없음
				
				// next까지의 거리 > 현재 간선을 거쳐갈 때의 거리 -> 짧은 거리로 갱신
				if(distance[road.v] > distance[road.u] + road.cost) {
					distance[road.v] = distance[road.u] + road.cost;
					if(i == N-1) { // 음수 사이클 존재
						return true;
					}
				}
			}
		}
		
		return false;
	}
}
