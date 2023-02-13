import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

class Main {
	static class Node {
		int x, y, dist, brokenCnt;

		public Node(int x, int y, int dist, int brokenCnt) {
			this.x = x;
			this.y = y;
			this.dist = dist;
			this.brokenCnt = brokenCnt;
		}
	}

	static int N, M, K;
	static int[] dx = { 1, -1, 0, 0 };
	static int[] dy = { 0, 0, 1, -1 };
	static boolean[][] isWall;
	static boolean[][][] visited;

	public static void main(String[] args) throws IOException {
		inputs();

		/**
		 * 벽을 부쉈는지 여부를 나타낸 brokenCnt를 분리하지 않고 하나로 설정해놓으면,
		 * 벽을 부숴서 어떤 좌표에 먼저 도달해 거리가 더 낮은 탐색이
		 * 벽을 덜 부수고 와서 거리는 높지만 정답이 가능했을 탐색을 묵살할 수 있음.
		 * 그러므로 K + 1 길이의 배열을 만들어 어떤 좌표까지 도착하는데 몇 번 부숴서 왔는지 파악해야 함.
		 * 
		 * visited 배열의 셋째 칸에 K + 1 길이의 배열을 만드는데, int[][][]로 만들면 메모리 초과!
		 * boolean[][][]으로 만들고 방문처리만 진행하고 Node에 dist 프로퍼티로 거리를 계산
		 */
		System.out.println(bfs());
	}

	private static int bfs() {
		Queue<Node> q = new LinkedList<>();
		visited = new boolean[N][M][K + 1];

		q.add(new Node(0, 0, 1, 0)); // dist 1의 Node 큐에 추가
		visited[0][0][0] = true; // 벽을 하나도 안부수고 0,0 좌표 방문

		while (!q.isEmpty()) {
			Node curr = q.poll();
			if (curr.x == N - 1 && curr.y == M - 1) {
				return curr.dist; // 좌표가 목적지에 도착하면 dist 반환
			}

			for (int i = 0; i < 4; i++) {
				int nx = curr.x + dx[i];
				int ny = curr.y + dy[i];

				if (nx < 0 || nx >= N || ny < 0 || ny >= M)
					continue; // 범위 체크

				// 다음 좌표의 brokenCnt는 다음 좌표가 벽이면 현재 cnt에 1 추가, 빈 칸이면 현재 cnt 그대로
				int nextBrokenCnt = curr.brokenCnt + (isWall[nx][ny] ? 1 : 0);

				if (nextBrokenCnt > K || visited[nx][ny][nextBrokenCnt])
					continue; // 벽을 K개 초과해서 부쉈거나, 방문했으면 진행 불가

				visited[nx][ny][nextBrokenCnt] = true;
				q.add(new Node(nx, ny, curr.dist + 1, nextBrokenCnt));
			}
		}
		return -1;
	}

	private static void inputs() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		String[] input = br.readLine().split(" ");
		N = Integer.parseInt(input[0]);
		M = Integer.parseInt(input[1]);
		K = Integer.parseInt(input[2]);

		isWall = new boolean[N][M];
		char[] tmp;

		for (int i = 0; i < N; i++) {
			tmp = br.readLine().toCharArray();
			for (int j = 0; j < M; j++) {
				isWall[i][j] = tmp[j] == '1';
			}
		}

		br.close();
	}
}