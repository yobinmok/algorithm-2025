class Solution {
    
    int startX, startY;
    
    public int[] solution(int m, int n, int startX, int startY, int[][] balls) {
        int size = balls.length;
        int[] answer = new int[size];
        startX += m;
        startY += n;
        this.startX = startX;
        this.startY = startY;
        
        for(int i = 0; i<size; i++){
            int nx = balls[i][0] + m;
            int ny = balls[i][1] + n;
            int min = Integer.MAX_VALUE;
            
            // 하
            int y1 = n - balls[i][1];
            if(!(nx == startX && ny < startY)) min = Math.min(min, getDistance(nx, y1));
            
            // 좌
            int x2 = 2*m + Math.abs(m - balls[i][0]);
            if(!(ny == startY && nx > startX)) min = Math.min(min, getDistance(x2, ny));

            // 상
            int y3 = 2*n + Math.abs(n - balls[i][1]);
            if(!(nx == startX && ny > startY)) min = Math.min(min, getDistance(nx, y3));    
            
            // 우
            int x4 = m - balls[i][0];
            if(!(ny == startY && nx < startX)) min = Math.min(min, getDistance(x4, ny));
            
            answer[i] = min;
        }
        return answer;
    }
    
    public int getDistance(int nx, int ny){
        return (int) (Math.pow(startX - nx, 2) + Math.pow(startY-ny, 2));
    }
    
    
}