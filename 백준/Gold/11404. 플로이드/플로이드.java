import java.io.*;
import java.util.*;

public class Main {
	
	static int n, m, distance[][], INF = 1000000000;
	static StringBuilder sb;
	
	
	/*
	 * 도시를 연결하는 노선이 여러개일 수 있다. 
	 * 플로이드 워셜: 모든 조합을 다 확인 -> 이전에 계산한 값을 활용함(DP)
	 * */
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		
		n = Integer.parseInt(br.readLine());
		m = Integer.parseInt(br.readLine());
		
		distance = new int[n][n];
		for(int i=0; i<n; i++) {
			for(int j=0; j<n; j++) {
				distance[i][j] = INF;

				if(i == j) {
					distance[i][j] = 0;
				}
			}
		}
		
		for(int i = 0; i<m; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int u = Integer.parseInt(st.nextToken())-1;
			int v = Integer.parseInt(st.nextToken())-1;
			int c = Integer.parseInt(st.nextToken());
			
			distance[u][v] = Math.min(c, distance[u][v]);
		}
		
		for(int k = 0; k<n; k++) { // 중간
			for(int i = 0; i<n; i++) { // 시작
				for(int j = 0; j<n; j++) { // 끝
					if (distance[i][j] > distance[i][k] + distance[k][j]) {
						distance[i][j] = distance[i][k] + distance[k][j];
                    }
				}
			}
		}
		
		for(int i = 0; i < n; i++) {
            for(int j = 0; j < n; j++) {
            	if(distance[i][j] == INF) {
            		distance[i][j] = 0;
            	}

                sb.append(distance[i][j] + " ");
            }
            sb.append("\n");
        }
		
		System.out.println(sb);
	}
	
}
