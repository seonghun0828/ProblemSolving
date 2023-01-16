import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main {
	static class Node {
		int val, dist;

		public Node(int val, int dist) {
			this.val = val;
			this.dist = dist;
		}
	}

	static int N, ans;
	static int[] dx = { 1, -1, 0, 0 };
	static int[] dy = { 0, 0, 1, -1 };
	static Node[][] arr;

	public static void main(String[] args) throws IOException {
		inputs();

		ans = 0;

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (arr[i][j].dist > 1) // 방문함
					continue;

				ans = Math.max(ans, dfs(i, j));
			}
		}

		System.out.println(ans);
	}

	private static int dfs(int x, int y) {
		for (int i = 0; i < 4; i++) {
			int nx = x + dx[i];
			int ny = y + dy[i];

			if (nx < 0 || nx >= N || ny < 0 || ny >= N) // 범위 체크
				continue;

			if (arr[x][y].val >= arr[nx][ny].val) // 다음 칸의 대나무가 더 적거나 같음
				continue;

			if (arr[nx][ny].dist == 1) // 다음 칸 방문 안했으면 dfs 탐색
				arr[nx][ny].dist = dfs(nx, ny);

			// 현재 칸의 dist는 현재 칸의 원래 값과 다음 칸의 dist 값 + 1 중 최대값으로 갱신
			arr[x][y].dist = Math.max(arr[x][y].dist, arr[nx][ny].dist + 1);
		}
		return arr[x][y].dist;
	}

	private static void inputs() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		arr = new Node[N][N];
		StringTokenizer st;

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				int num = Integer.parseInt(st.nextToken());
				arr[i][j] = new Node(num, 1);
			}
		}

		br.close();
	}
}