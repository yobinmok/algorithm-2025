import java.util.*;

class Solution {
    
    class Plan{
        String name;
        int start, playtime;
        
        Plan(String name, int start, int playtime){
            this.name = name;
            this.start = start;
            this.playtime = playtime;
        }
        
        String string(){
            return name + ", " + start + ", " + playtime;
        }
    }
    
    public String[] solution(String[][] plans) {
        String[] answer = new String[plans.length];
        Plan[] plan = new Plan[plans.length];
        Stack<Plan> stk = new Stack<>();
        int idx = 0;
        
        for(int i = 0; i<plans.length; i++){
            String[] temp = plans[i][1].split(":"); 
            int start = Integer.parseInt(temp[0]) * 60 + Integer.parseInt(temp[1]);
            int playtime = Integer.parseInt(plans[i][2]);
            plan[i] = new Plan(plans[i][0], start, playtime);
        }
        
        Arrays.sort(plan, (Plan s1, Plan s2) -> {
           return s1.start - s2.start;
        });
        
        for(Plan cur: plan){
            if(!stk.isEmpty()){
                Plan top = stk.peek();
                int gap = cur.start - top.start;
                while(!stk.isEmpty()){
                    top = stk.peek();
                    if(top.playtime <= gap){
                        gap -= top.playtime;
                        answer[idx++] = stk.pop().name;
                    }else{
                        top.playtime -= gap;
                        break;
                    }
                }
            }
            stk.push(cur);
        }
        
        while(!stk.isEmpty()){
            answer[idx++] = stk.pop().name;
        }
        
        return answer;
    }
}