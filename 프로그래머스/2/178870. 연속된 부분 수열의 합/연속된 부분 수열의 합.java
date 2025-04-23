/*
    비내림차순으로 정렬
    어느 범위를 선택하든 부분 수열이여야 함
    부분수열의 합은 k
    - 길이가 짧은 수열 찾기
    - 앞쪽에 나오는 수열
    
    투포인터
    저번에 이런 문제 풀었던 것 같은데... 
*/
import java.util.*;

class Solution {
    public int[] solution(int[] sequence, int k) {
        int[] answer = {Integer.MAX_VALUE, Integer.MAX_VALUE};
        int size = sequence.length;
        int start = 0, end = 0, sum = 0, min = Integer.MAX_VALUE;
        
        while(true){            
            if(sum > k){
                if(start < size) 
                    sum -= sequence[start++];
                else break;
            }else if(sum < k){
                if(end < size)
                    sum += sequence[end++];
                else break;
            }
            
            if(end == size && start == size){
                break;
            }
            
            if(sum == k){
                if((end - start < min) || (end-start == min && answer[0] > start)){
                    answer[0] = start;
                    answer[1] = end-1;
                    min = end - start;
                }
                
                if(end == size) sum -= sequence[start++];
                else sum += sequence[end++];
            }
        }
        return answer;
    }
}