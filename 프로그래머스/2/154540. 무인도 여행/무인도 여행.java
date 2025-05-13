import java.util.*;

class Solution {
    
    /*
        섬에서 최대 며칠 머무를 수 있는지
    */
    
    int n, m, board[][];
    int[] dr = {0, 0, 1, -1}, dc = {1, -1, 0, 0};
    List<Integer> list = new ArrayList<>();
    
    public int[] solution(String[] maps) {
        n = maps.length;
        m = maps[0].length();
        board = new int[n][m];
        
        for(int i = 0; i<n; i++){
            for(int j = 0; j<m; j++){
                char s = maps[i].charAt(j);
                if(s != 'X') board[i][j] = s-48;
            }
        }
        
        for(int i = 0; i<n; i++){
            for(int j = 0; j<m; j++){
                if(board[i][j] == 0) continue;
                bfs(i, j);
            }
        }
        
        int[] answer = new int[list.size()];
        for(int i = 0; i<list.size(); i++){
            answer[i] = list.get(i);
        }
        Arrays.sort(answer);
        
        return list.size() > 0 ? answer : new int[]{-1};
    }
    
    void bfs(int r, int c){
        int sum = board[r][c];
        Queue<int[]> que = new ArrayDeque<>();
        que.add(new int[] {r, c});
        board[r][c] = 0;
        
        while(!que.isEmpty()){
            int[] cur = que.poll();
            
            for(int d = 0; d<4; d++){
                int nr = cur[0] + dr[d];
                int nc = cur[1] + dc[d];
                
                if(nr < 0 || nr >= n || nc < 0 || nc >= m || board[nr][nc] == 0) continue;
                
                sum += board[nr][nc];
                board[nr][nc] = 0;
                que.add(new int[] {nr, nc});
            }
        }
        
        if(sum > 0) list.add(sum);
    }
}