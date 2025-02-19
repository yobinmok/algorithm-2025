import java.util.*;

class Solution {
    /*
    미사일을 최소로 사용해 모든 폭격미사일을 요격하려 함.
    A나라의 폭격미사일은 X축에 평행 -> 폭격미사일의 양끝을 맞추면 요격 불가 // 500000
    B나라의 미사일은 Y축과 수평
    */
    public int solution(int[][] targets) {
        int answer = 0, cur = 0;
        
        // 끝나는 지점 오름차순으로 정렬
        Arrays.sort(targets, (int[] o1, int[] o2) -> {
            return o1[1] - o2[1];
        });
        for(int[] target: targets){
            // 겹치지 않으면
            if(target[1] < cur || target[0] >= cur){
                cur = target[1];
                answer += 1;
            }
        }
        return answer;
    }
}