import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args)throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		
		for(int t = 0; t<T; t++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int M = Integer.parseInt(st.nextToken());
			int N = Integer.parseInt(st.nextToken());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			int k = 0;
			
			for(int i = x; i <= M*N; i += M) {
				int n = i % N == 0 ? N : i % N;
				if(n == y) {
					k = i;
					break;
				}
			}
			
			sb.append(k == 0 ? -1 : k);
			sb.append("\n");
		}
		
		System.out.println(sb);
	}
}


