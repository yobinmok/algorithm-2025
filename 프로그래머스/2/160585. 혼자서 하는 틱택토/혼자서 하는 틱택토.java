import java.util.*;

class Solution {
    
    /*  
        삼목, 칸이 모두 다 차면 무승부
        실수는 다음과 같다.
        - O, X 표시 반대로 -> 이 경우를 어떻게 판단?
        - 게임이 종료됐는데도 킵고잉
        게임 결과를 보고 규칙을 지켜서 진행했을 때 나올 수 있는 상황인지 판단해라.
        
        board의 크기가 작기 때문에 재귀로 브루트포스해도 될 듯
        1. O, X 위치 저장
        2. 선후공 순서에 따라 브루트포스로 놓는 경우 확인
        3. 실수여부 확인
    */
    
    int cnt, answer;
    int[] dr = {0, 1, 1, 1}, dc = {1, 1, 0, -1};
    char[][] test;
    List<int[]> o_pos, x_pos;
    
    public int solution(String[] board) {
        answer = 0;
        o_pos = new ArrayList<>();
        x_pos = new ArrayList<>();
        
        for(int i = 0; i<3; i++){
            for(int j = 0; j<3; j++){
                if(board[i].charAt(j) == 'O'){
                    o_pos.add(new int[]{i, j});
                }else if(board[i].charAt(j) == 'X'){
                    x_pos.add(new int[]{i, j});
                }
            }
        }
        cnt = o_pos.size() + x_pos.size();
        test = new char[3][3];
        for(char[] c: test){
            Arrays.fill(c, '.');
        }
        
        // 일단 x > o 이면 불가
        // if(x_pos.size() > o_pos.size()) return 0;
        recur(0);
        
        // test = new char[][]{{'O','O','X'},{'.', 'O', 'X'},{'.','O','.'}};
        // System.out.println(isFinished('O'));
        return answer;
    }
    
    public boolean isFinished(char player) {
        // 가로 확인
        for (int i = 0; i < 3; i++) {
            if (test[i][0] == player && test[i][1] == player && test[i][2] == player) return true;
        }

        // 세로 확인
        for (int i = 0; i < 3; i++) {
            if (test[0][i] == player && test[1][i] == player && test[2][i] == player) return true;
        }

        // 대각선 확인
        if (test[0][0] == player && test[1][1] == player && test[2][2] == player) return true;
        if (test[0][2] == player && test[1][1] == player && test[2][0] == player) return true;

        return false;
    }
    
    // 현재까지의 순서, o 인덱스, x 인덱스
    public void recur(int idx){
        if(idx == cnt){ // 모든 순서를 다 진행한 경우
            answer = 1;
            return;
        }
        
        char c = idx % 2 == 0 ? 'X' : 'O';
        if(isFinished(c)){ // 누군가 성공했는데도 놓지 않은게 남은 경우
            return;
        }
        
        if(idx % 2 == 0){ // 선공
            for(int i = 0; i<o_pos.size(); i++){
                int[] cur = o_pos.get(i);
                if(test[cur[0]][cur[1]] != '.') continue;
                test[cur[0]][cur[1]] = 'O';
                recur(idx+1);
                test[cur[0]][cur[1]] = '.';
            }
        }else{ // 후공
            for(int i = 0; i<x_pos.size(); i++){
                int[] cur = x_pos.get(i);
                if(test[cur[0]][cur[1]] != '.') continue;
                test[cur[0]][cur[1]] = 'X';
                recur(idx+1);
                test[cur[0]][cur[1]] = '.';
            }
        }
    }
}