import java.io.*;
import java.util.*;

public class Main {
    static int N, M, H;
    static int[][] dp;
    static List<Integer>[] block;
    static final int MOD = 10007;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        H = Integer.parseInt(st.nextToken());

        dp = new int[N + 1][H + 1];
        block = new ArrayList[N];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            block[i] = new ArrayList<>();
            while (st.hasMoreTokens()) {
                block[i].add(Integer.parseInt(st.nextToken()));
            }
        }

        dp[0][0] = 1;

        for (int s = 1; s <= N; s++) {
            for (int h = 0; h <= H; h++) {
                dp[s][h] = dp[s - 1][h]; // 블록을 사용하지 않는 경우
            }

            for (int b : block[s - 1]) {
                for (int h = b; h <= H; h++) {
                    dp[s][h] += dp[s - 1][h - b];
                    dp[s][h] %= MOD;
                }
            }
        }

        System.out.println(dp[N][H]);
    }
}
