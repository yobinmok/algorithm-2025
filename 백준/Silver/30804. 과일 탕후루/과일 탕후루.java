import java.io.*;
import java.util.*;

public class Main {

	
	public static void main(String[] args)throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
        	int left = 0, max = 0, cnt = 0;
		int[] fruits = new int[N];
	        HashMap<Integer, Integer> map = new HashMap<>();
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i = 0; i<N; i++) {
			fruits[i] = Integer.parseInt(st.nextToken());
		}
		
		for(int right = 0; right<N; right++) {
			map.put(fruits[right], map.getOrDefault(fruits[right], 0) + 1);
			
			if(map.size() > 2) {
				max = Math.max(max, cnt);
				
				// * left 갱신 작업
				// 1. left값이 얼마나 이어져있는지 확인
				// 2. 그 수만큼 left ++, cnt --, map 내 값 갱신
				int remove = fruits[left]; // 지울 숫자 
				int rCnt = 0; // 지울 숫자의 연결된 길이
				for(int l = left; l < right; l++) {
					if(fruits[l] == remove) rCnt += 1;
					else break;
				}
				
				if(map.get(fruits[left]) == rCnt) {
					map.remove(remove);
				}else {
					map.replace(remove, map.get(remove) - rCnt);
				}
				cnt -= rCnt;
				left += rCnt;
			}
			cnt += 1;
		}
		
		max = Math.max(max, cnt);
		System.out.println(max);
	}
}
