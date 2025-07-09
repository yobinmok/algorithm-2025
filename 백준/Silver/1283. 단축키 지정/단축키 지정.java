import java.io.*;
import java.util.*;

public class Main{
	
	/*
	 *  N개의 옵션
	 *  하나의 옵션에 대해 왼->오 단축키가 지정되었는지 확인
	 *  우선순위: 단어의 첫글자 -> (왼->오)순서의 알파벳
	 */
	
	public static void main(String[] args)throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int N = Integer.parseInt(br.readLine());
		String[] input = new String[N];
		boolean[] used = new boolean[26];
		
		for(int i = 0; i<N; i++) {
			input[i] = br.readLine();
			String[] words = input[i].split(" ");
			boolean option = false;
			
			for(int j = 0; j<words.length; j++) { // 우선순위 1: 단어의 첫 글자
				int n = words[j].toLowerCase().charAt(0)-'a';
				if(used[n]) continue;
				
				option = true;
				used[n] = true;
				for(int k = 0; k<j; k++) {
					sb.append(words[k] + " ");
				}
				sb.append("[" + words[j].charAt(0) + "]");
				if(words[j].length() > 1) sb.append(words[j].substring(1) + " ");
				for(int k = j+1; k<words.length; k++) {
					sb.append(words[k] + " ");
				}
				sb.append("\n");
				
				break;
			}
			
			// 우선순위 2: (왼->오)순서의 알파벳
			if(!option) {
			    for(int j = 0; j < input[i].length(); j++) {
			        char c = input[i].charAt(j);
			        if(c == ' ') continue;

			        int n = Character.toLowerCase(c) - 'a';
			        if(n < 0 || n >= 26) continue;  // 알파벳이 아닌 경우 무시

			        if(used[n]) continue;

			        used[n] = true;
			        option = true;
			        sb.append(input[i], 0, j);
			        sb.append("[").append(input[i].charAt(j)).append("]");
			        if(j + 1 < input[i].length()) sb.append(input[i].substring(j + 1));
			        sb.append("\n");
			        break;
			    }
			}

			
			// 단축키 지정 못 한 경우
			if(!option) {
				sb.append(input[i] + "\n");
			}
		}
		
		System.out.println(sb);
	}
}