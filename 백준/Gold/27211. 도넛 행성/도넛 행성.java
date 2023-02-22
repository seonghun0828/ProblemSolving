import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

class Main {
	static class Node {
		int x, y;

		public Node(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}

	static int N, M;
	static int[] dx = { 1, -1, 0, 0 };
	static int[] dy = { 0, 0, 1, -1 };
	static boolean[][] isForest;
	static boolean[][] visited;

	public static void main(String[] args) throws IOException {
		inputs();

		// 간단한 bfs 문제. 탐색해가며 좌표가 한바퀴 돌아 오는 것만 주의
		int cnt = 0;
		visited = new boolean[N][M];

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (visited[i][j] || isForest[i][j])
					continue;

				visited[i][j] = true;
				bfs(i, j);
				cnt++;
			}
		}
		System.out.println(cnt);
	}

	private static void bfs(int sx, int sy) {
		Queue<Node> q = new LinkedList<>();
		q.add(new Node(sx, sy));

		while (!q.isEmpty()) {
			Node curr = q.poll();
			for (int i = 0; i < 4; i++) {
				int nx = curr.x + dx[i];
				int ny = curr.y + dy[i];
				nx = (nx + N) % N; // -1이면 N-1로, N이면 0으로 한바퀴 돌아 좌표 조정
				ny = (ny + M) % M; // -1이면 M-1로, M이면 0으로 좌표 조정

				if (visited[nx][ny] || isForest[nx][ny])
					continue;

				visited[nx][ny] = true;
				q.add(new Node(nx, ny));
			}
		}
	}

	private static void inputs() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] input = br.readLine().split(" ");
		N = Integer.parseInt(input[0]);
		M = Integer.parseInt(input[1]);
		isForest = new boolean[N][M];
		for (int i = 0; i < N; i++) {
			input = br.readLine().split(" ");
			for (int j = 0; j < M; j++) {
				isForest[i][j] = input[j].equals("1");
			}
		}
		br.close();
	}
}