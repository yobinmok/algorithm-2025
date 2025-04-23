import java.util.*;

class Solution {
    
    /*
        목표위치까지의 최단거리
        bfs
    */
    
    int n, m;
    String[] board;
    int[] dr = {0, 0, 1, -1}, dc = {1, -1, 0, 0};
    
    public int solution(String[] board) {
        int answer = 0;
        int r = 0, c = 0, 
        n = board.length;
        m = board[0].length();
        this.board = board;
        L: for(int i = 0; i<n; i++){
            for(int j = 0; j<m; j++){
                if(board[i].charAt(j) == 'R'){
                    r = i;
                    c = j;
                    break L;
                }
            }
        }
        
        return bfs(r, c);
    }
    
    public int bfs(int r, int c){
        // 슬라이딩의 끝값에 대해서만 방문처리
        int depth = 0;
        int N = board.length;
        int M = board[0].length();
        Queue<int[]> que = new ArrayDeque<>();
        boolean[][] visited = new boolean[N][M];
        
        visited[r][c] = true;
        que.add(new int[]{r, c});
        
        while(!que.isEmpty()){
            int size = que.size();
            
            for(int i = 0; i<size; i++){
                int[] cur = que.poll();
                System.out.println(Arrays.toString(cur));
                if(board[cur[0]].charAt(cur[1]) == 'G'){
                    return depth;
                }
                
                for(int d = 0; d<4; d++){
                    int tr = cur[0], tc = cur[1];
                    boolean possible = true;
                    while(true){
                        int nr = tr + dr[d];
                        int nc = tc + dc[d];       
                        if(nr < 0 || nr >= N || nc < 0 || nc >= M || board[nr].charAt(nc) == 'D'){
                            if(visited[tr][tc]) possible = false;
                            break;
                        }else{
                            tr = nr;
                            tc = nc;
                        }
                    }
                    
                    if(possible){
                        visited[tr][tc] = true;
                        que.add(new int[] {tr, tc});
                    }
                }
            }
            depth += 1;
        }
        return -1;
    }
}