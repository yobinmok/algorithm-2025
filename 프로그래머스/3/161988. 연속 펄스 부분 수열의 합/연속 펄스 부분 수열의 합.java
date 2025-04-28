import java.util.*;
class Solution {
    /*
        연속 펄스 수열의 합 중 가장 큰 것을 구해라.
        50만..
        투포인터 -> 이분탐색 -> dp -> 그리디
                2 3 -6, 1 3 -1 2 -12
                
        [1, -1] 2 -3 -6 -1 3 1 2 12
                2 -1 -7 -8 -5 -4 -2 10
                
        [1, -1] 2 -3 -6 -1 3 1 2 -4
                2 -1 -7 -8 -5 -4 -2 -6
        [-1, 1] -2 3 6 1 -3 -1 -2 4
                -2 1 7 8 5 4 2 6
                
        특정 인덱스 값 아래 중 최솟값을 빼면 -> 최대
        특정 인덱스 값 아래 중 최댓값을 빼면 -> 최소
        안빼는 경우는?
     */
    public long solution(int[] sequence) {
        int size = sequence.length;
        long answer = 0;
        
        long[] pulse = new long[size]; // [1, -1] 
        
        pulse[0] = sequence[0];
        for(int i = 1; i<size; i++){
            if(i % 2 == 0) pulse[i] = pulse[i-1] + sequence[i];
            else pulse[i] = pulse[i-1] + sequence[i] * -1;
        }
    
        // 최대, 최솟값
        long max = 0, min = Long.MAX_VALUE; // 최대 최솟값
        int maxIdx = 0, minIdx = 0; // 인덱스
        for(int i = 0; i<size; i++){
            // [1, -1] 2 -3 -6 -1 3 1 2 -4
            //         2 -1 -7 -8 -5 -4 -2 -6
            // [-1, 1] -2 3 6 1 -3 -1 -2 4
            //         -2 1 7 8 5 4 2 6
            // 최대, 최소 갱신
            if(max < pulse[i]){
                max = pulse[i];
                maxIdx = i;
            }
            
            if(min > pulse[i]){
                min = pulse[i];
                minIdx = i;
            }
            
            // 값 비교
            long ver1 = Math.abs(pulse[i]); // 빼지 않는 경우
            long ver2 = Math.abs(pulse[i] - min); // 최댓값
            long ver3 = Math.abs(pulse[i] - max); // 최솟값
            long candidate = Math.max(ver1, Math.max(ver2, ver3));
            answer = Math.max(answer, candidate);
        }
        
        return answer;
    }
}