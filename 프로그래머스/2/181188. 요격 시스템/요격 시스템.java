import java.util.*;
import java.io.*;

class Solution {
    
    /*
        미사일을 최소로 사용해 모든 미사일을 요격하려 함, 이 때 필요한 요격 미사일 수의 최솟값을 구해라.
        좌표 크기 <= 50만
        겹치는 게 많은 부분에 쏘면 됨
        
        겹치는 부분을 구해라
        끝나는 게 작은 순으로 정렬
    */
    public int solution(int[][] targets) {
        int answer = 1;
        
        Arrays.sort(targets, (int[] o1, int[] o2)->{
            return o1[1] - o2[1];
        });
        
        int cur = targets[0][1];
        for(int i = 1; i<targets.length; i++){
            if(targets[i][0] >= cur){
                cur = targets[i][1];
                answer += 1;
            }
        }
        
        return answer;
    }
}