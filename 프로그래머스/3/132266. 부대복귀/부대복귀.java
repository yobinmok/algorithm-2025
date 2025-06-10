import java.util.*;

class Solution {
    
    /*
        - 다익스트라(한 점에서 다른 모든 점으로의 최단거리)
        아님 가중치가 다 1이니까 bfs -> 시간초과시간초과
    */
    
    int n, destination;
    List<Integer>[] graph;
    
    public int[] solution(int n, int[][] roads, int[] sources, int destination) {
        this.n = n;
        this.destination = destination;
        
        int[] answer = new int[sources.length];
        graph = new ArrayList[n+1]; // 간선 그래프
        for(int i = 1; i<=n; i++){
            graph[i] = new ArrayList<>();
        }
        
        for(int i = 0; i<roads.length; i++){
            graph[roads[i][0]].add(roads[i][1]);
            graph[roads[i][1]].add(roads[i][0]);
        }
        
        for(int i = 0; i<sources.length; i++){
            answer[i] = bfs(sources[i]);
        }
        
        return answer;
    }
    
    int bfs(int start){
        int step = 0;
        Queue<Integer> que = new ArrayDeque<>();
        boolean[] visited = new boolean[n+1];
        
        que.add(start);
        visited[start] = true;
        
        while(!que.isEmpty()){
            int size = que.size();
            for(int i = 0; i<size; i++){
                int cur = que.poll();

                if(cur == destination){
                    return step;
                }
                
                for(int next: graph[cur]){
                    if(next == destination) return step + 1;
                    if(visited[next]) continue;
                    visited[next] = true;
                    que.add(next);
                }
            }
            step += 1;
        }   
        
        return -1;
    }
    
    void dijkstra(){
        
    }
}