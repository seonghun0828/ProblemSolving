import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

class Main {
	static class Node {
		int x, y;

		public Node(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}

	static int N, M, cnt;
	static int[] dx = { 1, -1, 0, 0 };
	static int[] dy = { 0, 0, 1, -1 };
	static int[][] arr;
	static Queue<Node> q = new LinkedList<>();

	public static void main(String[] args) throws IOException {
		inputs();

		// 간단한 bfs 문제. 탐색해가며 좌표가 한바퀴 돌아 오는 것만 주의
		cnt = 0;

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (arr[i][j] >= 1) // 방문했으면 arr[i][j]가 1이상임
					continue;

				bfs(i, j);
			}
		}
		System.out.println(cnt);
	}

	private static void bfs(int sx, int sy) {
		q.clear();
		q.add(new Node(sx, sy));
		arr[sx][sy] = ++cnt; // arr는 0 or 1이므로 배열의 값에 cnt값을 넣어 방문처리까지 해결

		while (!q.isEmpty()) {
			Node curr = q.poll();
			for (int i = 0; i < 4; i++) {
				int nx = curr.x + dx[i];
				int ny = curr.y + dy[i];
				nx = (nx + N) % N; // -1이면 N-1로, N이면 0으로 한바퀴 돌아 좌표 조정
				ny = (ny + M) % M; // -1이면 M-1로, M이면 0으로 좌표 조정

				if (arr[nx][ny] >= 1) // 숲이거나 방문했으면
					continue;

				q.add(new Node(nx, ny));
				arr[nx][ny] = cnt;
			}
		}
	}

	private static void inputs() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stnz = new StringTokenizer(br.readLine());
		N = Integer.parseInt(stnz.nextToken());
		M = Integer.parseInt(stnz.nextToken());
		arr = new int[N][M];
		for (int i = 0; i < N; i++) {
			stnz = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				arr[i][j] = Integer.parseInt(stnz.nextToken());
			}
		}
		br.close();
	}
}