import java.io.*;
import java.util.*;

public class Main{
	
	static int n, k, cnt; 
	static boolean solve;
	static String ans;
	
	public static void main(String[] args)throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		n = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());
		solve = false;
		ans = "";
		
		recur(0, "");
	
		if(ans.equals("")) {
			System.out.println("-1");
		}else {
			st = new StringTokenizer(ans);
			String exp = st.nextToken();
			while(st.hasMoreTokens()) {
				exp += "+" + st.nextToken();
			}
			
			System.out.println(exp);
		}
	}
	
	static void recur(int num, String exp) {
		if(num > n || solve) return;
		if(num == n) {
			cnt += 1;
			if(cnt == k) {
				ans = exp;
				solve = true;
			}
			return;
		}
		
		for(int i = 1; i<=3; i++) {
			recur(num+i, exp + " " + Integer.toString(i));
		}
	}
}