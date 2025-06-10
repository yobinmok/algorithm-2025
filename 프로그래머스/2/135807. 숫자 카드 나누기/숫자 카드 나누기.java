import java.util.*;

class Solution {
    
    /*
        자신이 가진 카드는 모두 나눌 수 있고, 상대방이 가진 카드는 모두 나눌 수 없는
        가장 큰 양의 정수를 구해라..
        
        한 배열의 최대 공약수 구하기
        가장 큰 수의 약수들을 구하고 -> 다른 수들 확인(시초각인디)
    */
    public int solution(int[] arrayA, int[] arrayB) {
        int size = arrayA.length;
        
        Arrays.sort(arrayA);
        Arrays.sort(arrayB);

        int a1 = getDivider(arrayA, arrayB);
        int a2 = getDivider(arrayB, arrayA);
        return Math.max(a1, a2);
    }
    
    int getDivider(int[] arr, int[] arr2){ // 해당 배열에서 조건을 만족하는 a 구하기
        int a = 0;
        int size = arr.length;
        int num = arr[0];
        Set<Integer> dividers = new HashSet<>();
        List<Integer> candidate = new ArrayList<>();
        
        for(int i = 1; i <= num/2; i++){
            if(num % i == 0){
                dividers.add(i);
                dividers.add(num/i);
            }
        }
        
        for(int d: dividers){
            boolean possible = true;
            for(int n: arr){
                if(n % d != 0){
                    possible = false;
                    break;
                }
            }
            
            if(possible) candidate.add(d);
        }
        
        for(int d: candidate){
            boolean possible = true;
            for(int n: arr2){
                if(n % d == 0){
                    possible = false;
                    break;
                }
            }
            
            if(possible) a = Math.max(a, d);
        }
        return a;
    }
}