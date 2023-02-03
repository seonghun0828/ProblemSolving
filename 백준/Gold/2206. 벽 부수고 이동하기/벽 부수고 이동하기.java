import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

class Main {
	static class Block {
		int x, y;
		boolean isWallBroken; // 벽을 부쉈는지

		public Block(int x, int y, boolean isWallBroken) {
			this.x = x;
			this.y = y;
			this.isWallBroken = isWallBroken;
		}
	}

	static int N, M;
	static int[] dx = { 1, -1, 0, 0 };
	static int[] dy = { 0, 0, 1, -1 };
	static int[][] arr;

	public static void main(String[] args) throws IOException {
		inputs();

		// [][][0]: 방문한 해당 좌표의 벽을 안부순 최단 거리, [][][1] : 해당 좌표의 벽을 부순 최단 거리
		int[][][] visited = new int[N][M][2];
		bfs(visited);

		// 마지막 좌표 두 값 중 만약 0이면 정수 최댓값을, 아니면 최단 거리를 삽입
		int first = visited[N - 1][M - 1][0] > 0 ? visited[N - 1][M - 1][0] : Integer.MAX_VALUE;
		int second = visited[N - 1][M - 1][1] > 0 ? visited[N - 1][M - 1][1] : Integer.MAX_VALUE;
		// 마지막 좌표의 두 값 중 더 작은 값을 ans에 저장
		int ans = Math.min(first, second);
		// ans가 정수 최댓값이면 방문한 적이 없기 때문에 -1을 출력, 아니면 ans 출력
		System.out.println(ans == Integer.MAX_VALUE ? -1 : ans);
	}

	private static void bfs(int[][][] visited) {
		Queue<Block> q = new LinkedList<>();
		q.add(new Block(0, 0, false));
		visited[0][0][0] = 1; // 0,0 좌표의 벽 안부순 최단 거리 1 할당

		while (!q.isEmpty()) {
			Block curr = q.poll();
			for (int i = 0; i < 4; i++) {
				int nx = curr.x + dx[i];
				int ny = curr.y + dy[i];

				if (nx < 0 || nx >= N || ny < 0 || ny >= M)
					continue; // 범위 체크

				boolean isWallBroken = curr.isWallBroken;
				// 배열의 3번째 인덱스. 벽을 부수고 왔으면 1, 안부쉈으면 0
				int thirdIdx = isWallBroken ? 1 : 0;

				if (visited[nx][ny][thirdIdx] > 0)
					continue; // thirdIdx 그대로 다음 좌표 방문 여부 체크

				if (isWallBroken && arr[nx][ny] == 1)
					continue; // 벽을 부수고 왔는데 또 벽을 만남

				if (!isWallBroken && arr[nx][ny] == 1) {
					isWallBroken = true; // 벽을 아직 안부쉈는데 벽을 만나면 벽 부쉈는지 값 true로
					// 벽 부순 최단 거리에 안부쉈을 때의 이전 최단 거리 + 1을 할당
					visited[nx][ny][1] = visited[curr.x][curr.y][0] + 1;
				} else { // 빈 칸을 만나면 배열의 3번째 값에 이전 값 + 1만 추가
					visited[nx][ny][thirdIdx] = visited[curr.x][curr.y][thirdIdx] + 1;
				}

				q.add(new Block(nx, ny, isWallBroken));
			}
		}
	}

	private static void inputs() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		arr = new int[N][M];

		for (int i = 0; i < N; i++) {
			char[] input = br.readLine().toCharArray();
			for (int j = 0; j < M; j++) {
				arr[i][j] = input[j] == '0' ? 0 : 1;
			}
		}

		br.close();
	}
}