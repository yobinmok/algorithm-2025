import java.io.*;
import java.util.*;

public class Main {

    static int N;
    static int[] maze;
    static List<Integer>[] graph;
    static boolean[] visited;
    static boolean answer;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        while (true) {
            N = Integer.parseInt(br.readLine());
            if (N == 0) break;

            maze = new int[N + 1];
            graph = new ArrayList[N + 1];

            for (int i = 1; i <= N; i++) {
                graph[i] = new ArrayList<>();
            }

            for (int i = 1; i <= N; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                String type = st.nextToken();
                int cost = Integer.parseInt(st.nextToken());

                if (type.equals("L")) {
                    maze[i] = cost; // 레프리콘
                } else if (type.equals("T")) {
                    maze[i] = -cost; // 트롤 (음수로 저장)
                } else {
                    maze[i] = 0; // 빈 방
                }

                while (st.hasMoreTokens()) {
                    int to = Integer.parseInt(st.nextToken());
                    if (to == 0) break;
                    graph[i].add(to);
                }
            }

            visited = new boolean[N + 1];
            answer = false;

            if (maze[1] >= 0) {
                dfs(1, maze[1]);
            }

            sb.append(answer ? "Yes" : "No").append("\n");
        }

        System.out.print(sb);
    }

    static void dfs(int cur, int money) {
        if (cur == N) {
            answer = true;
            return;
        }

        visited[cur] = true;

        for (int next : graph[cur]) {
            if (visited[next]) continue;

            int nextMoney = money;

            if (maze[next] < 0) { // 트롤
                int toll = -maze[next];
                if (money < toll) continue;
                nextMoney = money - toll;
            } else if (maze[next] > 0) { // 레프리콘
                nextMoney = Math.max(money, maze[next]);
            }

            dfs(next, nextMoney);
        }

        visited[cur] = false;
    }
}
