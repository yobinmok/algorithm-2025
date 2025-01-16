import java.io.*;
import java.util.*;

public class Main {

	
	public static void main(String[] args)throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		int[] fruits = new int[N];
		int[] type = new int[10];
		int cnt = 0, max = 0;
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i = 0; i<N; i++) {
			fruits[i] = Integer.parseInt(st.nextToken());
			if(type[fruits[i]] == 0) {
				cnt += 1;
			}
			type[fruits[i]] += 1;
		}
		
		if(cnt <= 2) System.out.println(N);
		else {
			// a, b 경우의 수 구하기
			for(int f1 = 1; f1 < 9; f1++) {
				for(int f2 = f1+1; f2 <= 9; f2++) {
					int cur = 0;
					boolean continuous = false;
					for(int i = 0; i<N; i++) {
						if(fruits[i] == f1 || fruits[i] == f2) {
							cur += 1;
							continuous = true;
						}else {
							if(continuous) {
								continuous = false;
                                max = Math.max(max, cur);
								cur = 0;
							}
						}
					}
					max = Math.max(max, cur);
				}
			}
			System.out.println(max);
		}
	}
}
