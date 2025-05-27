class Solution {
    
    /*
        유사 칸토어 비트열
        - 0번째 비트열은 1
        - n번째 비트열 = n-1번째 비트열에서 1 -> 11011, 0 -> 00000로 치환
        
        n = 2
        1 -> 11011 -> 11011.11011.00000.11011.11011
        11011.11011.00000.11011.11011
        110[11.11011.00000.11]011.11011
        
        11011 = 27  
        5의 제곱근을 사용하니까 5진법임을 활용.....ㅜ
    */
    
    public int solution(int n, long l, long r) {
        int answer = 0;
        
        // 2, 7, 10, 11, 12, 13, 14, 17, 22
        for(long s = l-1; s < r; s++){
            String str = Long.toString(s, 5);
            boolean isZero = false;
            for(int i = 0; i<str.length(); i++){
                if(str.charAt(i) == '2'){
                    isZero = true;
                    break;
                }
            }
            if(!isZero) answer += 1;
        }
        return answer;
    }
}