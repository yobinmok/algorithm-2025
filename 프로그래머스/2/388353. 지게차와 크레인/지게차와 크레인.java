import java.util.*;
import java.io.*;

class Solution {
    
    /*
        컨테이너는 알파벳으로 구분됨
        바깥과 붙어있는 컨테이너는 빼낼 수 있음
        알파벳이 두 번 반복된 경우는 모든 컨테이너를 다 꺼냄
        모든 요청을 완료한 후 남은 컨테이너의 수를 구해라.
        
        그냥 bfs?
        A = 65
    */
    public int[] dr = {0, 0, 1, -1}, dc = {1, -1, 0, 0};
    public int n, m, answer;
    public int[][] board;
    public Queue<int[]> que;
    
    public int solution(String[] storage, String[] requests) {
        n = storage.length;
        m = storage[0].length();
        answer = n * m;
        board = new int[n][m];
        
        for(int i = 0; i<n; i++){
            for(int j = 0; j<m; j++){
                board[i][j] = storage[i].charAt(j) - 64;
            }
        }
        
        que = new ArrayDeque<>();
        for(String s: requests){ // <= 100
            int target = s.charAt(0) - 64;
            for(int i = 0; i<n; i++){
                for(int j = 0; j<m; j++){
                    if(board[i][j] == target){
                        if(s.length() == 1){
                            isPossible(i, j);
                        }else{
                            que.add(new int[] {i, j});
                            answer -= 1;
                        }
                    }
                }
            }
            
            while(!que.isEmpty()){
                int[] t = que.poll();
                board[t[0]][t[1]] = 0;
            }
        }
        
        return answer;
    }
    
    void isPossible(int r, int c){
        // 바깥과 붙어있는 컨테이너인지 확인
        for(int d = 0; d<4; d++){
            int nr = r + dr[d];
            int nc = c + dc[d];
            if(nr < 0 || nr >= n || nc < 0 || nc >= m){ // 지게차로 접근 가능
                que.add(new int[] {r, c});
                answer -= 1;
                break;
            }else if(board[nr][nc] == 0){ // 0으로 이어져서 범위 바깥에 닿느냐
                if(bfs(nr, nc)){
                    que.add(new int[] {r, c});
                    answer -= 1;
                    break;
                }
            }
        }
    }
    
    boolean bfs(int r, int c){
        Queue<int[]> tempQue = new ArrayDeque<>();
        boolean[][] visited = new boolean[n][m];
        
        tempQue.add(new int[] {r, c});
        visited[r][c] = true;
        
        while(!tempQue.isEmpty()){
            int[] t = tempQue.poll();
            for(int d = 0; d<4; d++){
                int nr = t[0] + dr[d];
                int nc = t[1] + dc[d];
                if(nr < 0 || nr >= n || nc < 0 || nc >= m ){ 
                    return true;
                }else if(board[nr][nc] == 0 && !visited[nr][nc]){
                    tempQue.add(new int[] {nr, nc});
                    visited[nr][nc] = true;
                }
            }
        }
        return false;
    }
}