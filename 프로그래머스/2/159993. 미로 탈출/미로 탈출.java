import java.util.*;

class Solution {
    /* 
        출발 -> 레버가 있는 칸 -> 문
    */
    
    public int solution(String[] maps) {
        int depth = 0;
        int n = maps.length;
        int m = maps[0].length();
        int[] start = new int[2];
        int[] dr = {0, 0, 1, -1}, dc = {1, -1, 0, 0};
        
        char[][] board = new char[n][m];
        for(int i = 0; i<n; i++){
            for(int j = 0; j<m; j++){
                board[i][j] = maps[i].charAt(j);
                if(board[i][j] == 'S'){
                    start[0] = i;
                    start[1] = j;
                }
            }
        }
        
        Queue<int[]> que = new ArrayDeque<>();
        boolean[][][] visited = new boolean[n][m][2]; // 0: 레버를 못찾은 경우, 1: 레버를 찾은 경우
        
        que.add(new int[] {start[0], start[1], 0});
        visited[start[0]][start[1]][0] = true;
        
        while(!que.isEmpty()){
            int size = que.size();
            for(int i = 0; i<size; i++){
                int[] cur = que.poll();
            
                if(board[cur[0]][cur[1]] == 'E' && visited[cur[0]][cur[1]][1]){
                    return depth;
                }

                for(int d = 0; d<4; d++){
                    int nr = cur[0] + dr[d];
                    int nc = cur[1] + dc[d];

                    if(nr < 0 || nr >= n || nc < 0 || nc >= m || board[nr][nc] == 'X' || visited[nr][nc][cur[2]]) continue;

                    int find = cur[2];
                    if(board[nr][nc] == 'L') find = 1;

                    visited[nr][nc][find] = true;
                    que.add(new int[] {nr, nc, find});
                }
            }
            depth += 1;
        }
        
        return -1;
    }
}