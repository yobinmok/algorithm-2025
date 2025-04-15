import java.util.*;

class Solution {
    
    public long solution(int r1, int r2) {
        long answer = 0;
        double r1_2 = Math.pow(r1, 2);
        double r2_2 = Math.pow(r2, 2);
        
        for(int x = 1; x <= r2; x++){
            // x*x + y*y = r*r;
            double x_2 = Math.pow(x, 2);
            int y1 = (int) Math.ceil(Math.sqrt(r1_2 - x_2));
            int y2 = (int) Math.sqrt(r2_2 - x_2);
            answer += y2 - y1 + 1;
        }
        
        answer *= 4;
        return answer;
    }
}