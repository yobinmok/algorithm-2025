import java.util.*;

class Solution {
    
    /*
        글자수가 작은 주문 -> 알파벳 순
        삭제된 주문서에서 n번째 주문을 찾아야 함
    */
    public String solution(long n, String[] bans) {
        StringBuilder sb = new StringBuilder();
        
        Arrays.sort(bans, (s1, s2) -> {
            return s1.length() == s2.length() ? s1.compareTo(s2) : s1.length() - s2.length();
        });
            
        // a = 1, aa = 27, bb = 54
        // 26 ^ 1(길이-i-1) * 2(아스키) + 2 = 54
        for(String ban: bans){
            long t = 0;
            for(int i = 0; i<ban.length(); i++){
                t += (ban.charAt(i)-96) * Math.pow(26, ban.length()-i-1);
            }
            
            if(t <= n){
                n += 1;
            }else{
                break;
            }
        }
        
        // 26 ^ 1(길이-i-1) * 2(아스키) => 모든 자리 수에서 반복
        while(n > 0){
            long t = n % 26 == 0 ? 26 : n % 26;
            sb.append((char) (t + 96));  
            n -= 1;
            n /= 26;
        }
        return sb.reverse().toString();
    }
}