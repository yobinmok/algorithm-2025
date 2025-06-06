import java.util.*;

class Solution {
    
    /*
        첫 번째 컬럼은 기본키
        해시함수
        1. 열, 행시작, 행끝
    */
    
    public int solution(int[][] data, int col, int row_begin, int row_end) {
        int answer = 0;
        
        Arrays.sort(data, (int[] a1, int[] a2) -> {
            if(a1[col-1] == a2[col-1]){
                return a2[0] - a1[0];
            }else{
                return a1[col-1] - a2[col-1];
            }
        });
        
        
        List<Integer> sum = new ArrayList<>();
        for(int i = row_begin-1; i<row_end; i++){
            int s = 0;
            for(int j = 0; j<data[0].length; j++){
                s += data[i][j] % (i+1);
            }
            sum.add(s);
        }
            
        for(int i = 0; i<sum.size(); i++){
            answer ^= sum.get(i);
        }
    
        return answer;
    }
    
}