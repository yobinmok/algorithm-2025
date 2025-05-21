import java.util.*;

class Solution {
    /*
        뒷큰수: 자신보다 뒤에 있는 숫자 중 자신보다 크면서 가장 가까이 있는 수        
    */
    
    public int[] solution(int[] numbers) {
        int cur = 0;
        int size = numbers.length;
        int[] answer = new int[size];
        Arrays.fill(answer, -1);
        
        Stack<Integer> stk = new Stack<>();
        for(int i = 0; i<size; i++){
            while(!stk.isEmpty() && numbers[stk.peek()] < numbers[i]){
                answer[stk.pop()] = numbers[i];
            }
            stk.push(i);
        }
        
        return answer;
    }
}