import java.util.*;

class Solution {
    /*
        최소한의 객실을 사용해 예약 손님을 받으려고 함
        한 번 사용한 객실은 퇴실시간 + 10분(청소시간) 후 다음 손님을 받을 수 있음
        최소 객실의 수를 구해라.
        
        정렬인데... 일찍 끝나는 순으로 정렬
    */
    public int solution(String[][] book_time) {
        int answer = 0;
        
        int[][] book = new int[book_time.length][2];
        for(int i = 0; i<book_time.length; i++){
            String[] start = book_time[i][0].split(":");
            String[] end = book_time[i][1].split(":");
            book[i][0] = Integer.parseInt(start[0]) * 60 + Integer.parseInt(start[1]);
            book[i][1] = Integer.parseInt(end[0]) * 60 + Integer.parseInt(end[1]);
        }
        
        Arrays.sort(book, (int[] s1, int[] s2) -> {
            return s1[0]-s2[0]; 
        });
        
        PriorityQueue<Integer> que = new PriorityQueue<>();
        for(int i = 0; i<book_time.length; i++){
            int compare = 1;
            if(!que.isEmpty()) compare = que.peek() - book[i][0];
            
            if(compare <= 0){ // 이전 끝 <= 다음 시작: 같은 방 쓸 수 있음
                que.poll();
            }else{ //  이전 끝 > 다음 시작: 같은 방 못씀
                answer += 1;
            }
            que.add(book[i][1] + 10);
            // System.out.println(que.peek() + " " + book_time[i][0] + " " + compare);
            // System.out.println(que);
        }
        
        return answer;
    }
}