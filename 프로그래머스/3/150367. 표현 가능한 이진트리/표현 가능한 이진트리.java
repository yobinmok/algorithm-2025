import java.util.*;

class Solution {
    /*
        수가 주어졌을 때, 이를 하나의 이진트리로 표현할 수 있는지...................^^
    */
    
    public int[] solution(long[] numbers) {
        int[] answer = new int[numbers.length];
        
        for(int i = 0; i<numbers.length; i++){
            String s = Long.toString(numbers[i], 2);
            
            int[] temp = findLevel(s.length());
            s = "0".repeat(temp[0]- s.length()) + s;
            int root = s.length() / 2;
            int level = temp[1];
            
            if(bfs(s, root, level)){
                answer[i] = 1;
            }
        }
        
        return answer;
    }
    
    boolean bfs(String s, int root, int level){
        int cnt = 1;
        int step = (int)Math.pow(2, level-2);
        Queue<Integer> que = new ArrayDeque<>();
        que.add(root);
        
        while(!que.isEmpty()){
            int size = que.size();
            for(int i = 0; i<size; i++){
                int cur = que.poll();
                
                if(cnt == s.length()) break;
                if(s.charAt(cur) == '0'){ // 부모가 0일 때 자식은 1이면 안됨
                    if(cur+step < s.length() && s.charAt(cur + step) == '1'){
                        return false;
                    }
                    
                    if(cur-step >= 0 && s.charAt(cur - step) == '1'){
                        return false;
                    }
                }
                
                if(cur+step < s.length()){
                    que.add(cur + step);
                    cnt += 1;
                }
                if(cur-step >= 0){
                    que.add(cur - step);
                    cnt += 1;
                }
            }
            
            // if(step == 0) break;
            step /= 2;
        }
        
        return true;
    }
    
    int[] findLevel(int num){
        int l = 0;
        int sum = 0;
        while(num > sum){
            sum += Math.pow(2, l++);
        }
        return new int[] {sum, l};
    }
}