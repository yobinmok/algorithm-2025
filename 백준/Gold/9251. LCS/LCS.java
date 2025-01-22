import java.io.*;
import java.util.Arrays;

public class Main {

	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		String a = br.readLine();
		String b = br.readLine(); // 문자열의 길이는 최대 1000
		
		int[][] dp = new int[a.length()][b.length()];
		
		for(int i = 0; i<a.length(); i++) { // 행
			for(int j = 0; j<b.length(); j++) { // 열
				if(a.charAt(i) == b.charAt(j)) { // 해당 위치의 문자가 같으면
					if(i > 0 && j > 0) 
						dp[i][j] = dp[i-1][j-1] + 1;
					else	
						dp[i][j] += 1;
				}else {
					if(i > 0 && j > 0) 
						dp[i][j] = Math.max(dp[i][j-1], dp[i-1][j]);
					else if(j > 0) 
						dp[i][j] = dp[i][j-1];
					else if(i > 0)
						dp[i][j] = dp[i-1][j];
				}
			}
		}
		
		System.out.println(dp[a.length()-1][b.length()-1]);
	}
}
