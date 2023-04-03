import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

class Main {
	static class Node {
		int x, y;
		boolean isJihun;

		public Node(int x, int y, boolean isJihun) {
			this.x = x;
			this.y = y;
			this.isJihun = isJihun;
		}
	}

	static int R, C, sx, sy, ans;
	static int[] dx = { 1, -1, 0, 0 };
	static int[] dy = { 0, 0, 1, -1 };
	static char[][] maze;
	static List<int[]> fires;

	public static void main(String[] args) throws IOException {
		inputs();

		ans = 0;
		System.out.println(checkPossible() ? ans : "IMPOSSIBLE");
	}

	private static boolean checkPossible() {
		Queue<Node> q = new LinkedList<>();
		/**
		 * 불이 먼저 큐에 들어가야 지훈이가 불 무시하고 진행하는 경우가 없음.
		 * 그러므로 불을 먼저 다 큐에 넣고, 그 이후 지훈이 시작 위치를 큐에 넣음.
		 * 추후에도 큐에서 순서대로 빼기 때문에 같은 시간대의 불 -> 지훈 순서 유지
		 */
		for (int[] tmp : fires) { // q에 불 추가
			q.add(new Node(tmp[0], tmp[1], false));
		}
		q.add(new Node(sx, sy, true)); // q에 지훈 추가
		int[][] visited = new int[R][C];
		visited[sx][sy] = 1;

		while (!q.isEmpty()) {
			Node curr = q.poll();
			for (int i = 0; i < 4; i++) {
				int nx = curr.x + dx[i];
				int ny = curr.y + dy[i];

				if (nx < 0 || nx >= R || ny < 0 || ny >= C) {
					if (curr.isJihun) { // 지훈이 탈출!
						ans = visited[curr.x][curr.y];
						return true;
					}
					continue;
				}
				if (maze[nx][ny] == '#' || maze[nx][ny] == 'F') {
					continue; // 다음 미로 칸이 벽이나 불일 때
				}
				if (curr.isJihun && maze[nx][ny] == 'J') {
					continue; // 지훈이 자기가 갔던 곳 방문 처리
				}

				q.add(new Node(nx, ny, curr.isJihun)); // 다음 큐에 현재 큐와 같은 타입 노드 추가
				maze[nx][ny] = curr.isJihun ? 'J' : 'F'; // 다음 미로 칸에 현재 칸의 타입 적어주기
				if (curr.isJihun) { // 지훈이니? visited 갱신
					visited[nx][ny] = visited[curr.x][curr.y] + 1;
				}
			}
		}
		return false;  // 중간에 탈출하지 못하면 false 반환
	}

	private static void inputs() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		maze = new char[R][C];
		fires = new ArrayList<>();
		char[] input;

		for (int i = 0; i < R; i++) {
			input = br.readLine().toCharArray();
			maze[i] = input;
			for (int j = 0; j < C; j++) {
				if (input[j] == 'J') {
					sx = i;
					sy = j;
					maze[i][j] = '.';
				}
				if (input[j] == 'F') {
					fires.add(new int[] { i, j });
				}
			}
		}

	}
}