import java.io.*;
import java.util.*;

public class Main{
	
	public static void main(String[] args)throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		String input = br.readLine();
		int size = input.length(), window = 0, min = Integer.MAX_VALUE;
		for(int i = 0; i<size; i++) {
			int c = input.charAt(i)-'a';
			if(c == 0) window += 1;
		}
		
		for(int i = 0; i<size; i++) {
			int cnt = 0;
			for(int j = 0; j<window; j++) {
				int n = i + j >= size ? i+j-size : i+j;
				if(input.charAt(n) == 'b') cnt += 1;
			}
			
			min = Math.min(cnt, min);
		}
		System.out.println(min);
	}
}