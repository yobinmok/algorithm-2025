class Solution {
    
    /*
        점과 점 사이 거리 구하는 공식 d = sqrt((a1-a2)^2 - (b1-b2)^2)
        반지름이 d인 원 안에 k의 배수로 이루어진 좌표의 갯수 구하기
        
        x2 + y2 = d2
    */
    public long solution(int k, int d) {
        long answer = 0;
        
        for(int a = 0; a<=d; a += k){ // x
            double h1 = Math.floor(Math.sqrt(Math.pow(d, 2) - Math.pow(a, 2)));
            // System.out.println(a + " > " + h1 + " / h1보다 낮은 수 중 k의 배수: " + ((int) h1 / k));
            // h1 보다 낮은 수 중 k의 배수
            answer += ((int) h1 / k) + 1; // x축 위에 있는 경우 더해주기
        }
        return answer;
    }
}