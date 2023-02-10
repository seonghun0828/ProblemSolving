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

	static int N;
	static int[] dx = { 1, -1, 0, 0 };
	static int[] dy = { 0, 0, 1, -1 };
	static boolean[][] isRoom;

	public static void main(String[] args) throws IOException {
		inputs();

		System.out.println(bfs());
	}

	private static int bfs() {
		Queue<Node> q = new LinkedList<>();
		int[][] visited = new int[N][N]; // 해당 좌표까지 도착하는데 바꾼 벽의 최솟값
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				visited[i][j] = -1; // 시작 좌표를 0에서 시작하기 위해 -1로 전부 초기화
			}
		}
		q.add(new Node(0, 0));
		visited[0][0] = 0;

		while (!q.isEmpty()) {
			Node curr = q.poll();
			for (int i = 0; i < 4; i++) {
				int nx = curr.x + dx[i];
				int ny = curr.y + dy[i];

				if (nx < 0 || nx >= N || ny < 0 || ny >= N)
					continue;

				int changedWall = isRoom[nx][ny] ? 0 : 1; // 다음 칸이 빈 방이면 0, 벽이면 1
				if (visited[nx][ny] == -1) { // 첫 방문이면 이전 visited 값에 changedWall 추가
					visited[nx][ny] = visited[curr.x][curr.y] + changedWall;
				} else if (visited[nx][ny] <= visited[curr.x][curr.y] + changedWall) {
					// 이전에 방문했고, 새로운 값이 이전 방문했을 때의 값 이상이라 갱신이 불가능
					continue;
				} else { // 이전에 방문했지만 새로운 값이 이전 값보다 작아 갱신 가능
					visited[nx][ny] = visited[curr.x][curr.y] + changedWall;
				}
				q.add(new Node(nx, ny));
			}
		}
		return visited[N - 1][N - 1];
	}

	private static void inputs() throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		N = Integer.parseInt(br.readLine());
		isRoom = new boolean[N][N];
		char[] tmp;

		for (int i = 0; i < N; i++) {
			tmp = br.readLine().toCharArray();
			for (int j = 0; j < N; j++) {
				isRoom[i][j] = tmp[j] == '1';
			}
		}
	}
}