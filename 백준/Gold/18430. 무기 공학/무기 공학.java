import java.io.*;
import java.util.*;

public class Main {
    static int N, M, max;
    static int[][] board;
    static boolean[][] visited;
    
    static int[][][] shapes = {
        {{0, 0}, {1, 0}, {0, 1}}, // 아래 + 오른쪽
        {{0, 0}, {-1, 0}, {0, 1}}, // 위 + 오른쪽
        {{0, 0}, {-1, 0}, {0, -1}}, // 위 + 왼쪽
        {{0, 0}, {1, 0}, {0, -1}} // 아래 + 왼쪽
    };

    public static void main(String[] args)throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        board = new int[N][M];
        visited = new boolean[N][M];
        for(int i = 0; i<N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j<M; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        max = 0;
        dfs(0, 0);
        System.out.println(max);
    }

    static void dfs(int idx, int sum) {
        if (idx == N * M) {
            max = Math.max(max, sum);
            return;
        }

        int r = idx / M;
        int c = idx % M;

        // 현재 위치에 부메랑을 만들 수 있는지 확인
        if (!visited[r][c]) {
            for (int[][] shape : shapes) {
                int r1 = r + shape[1][0], c1 = c + shape[1][1];
                int r2 = r + shape[2][0], c2 = c + shape[2][1];

                if (inRange(r1, c1) && inRange(r2, c2)) {
                    if (!visited[r1][c1] && !visited[r2][c2]) {
                        visited[r][c] = visited[r1][c1] = visited[r2][c2] = true;
                        int strength = board[r][c] * 2 + board[r1][c1] + board[r2][c2];
                        dfs(idx + 1, sum + strength);
                        visited[r][c] = visited[r1][c1] = visited[r2][c2] = false;
                    }
                }
            }
        }

        // 부메랑 안 만들고 그냥 넘기기
        dfs(idx + 1, sum);
    }

    static boolean inRange(int r, int c) {
        return r >= 0 && r < N && c >= 0 && c < M;
    }
}
