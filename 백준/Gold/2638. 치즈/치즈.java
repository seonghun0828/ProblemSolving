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

		public Node(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}

	static int N, M, leftCheese;
	static int[] dx = { 1, -1, 0, 0 };
	static int[] dy = { 0, 0, 1, -1 };
	static int[][] cheese, visited;
	static List<Node> melting;

	public static void main(String[] args) throws IOException {
		inputs();

		int time = 0;
		melting = new ArrayList<>();

		while (leftCheese > 0) {
			aHourLater();
			time++;
		}
		System.out.println(time);
	}

	private static void aHourLater() {
		Queue<Node> q = new LinkedList<>();
		visited = new int[N][M];
		q.add(new Node(0, 0));
		visited[0][0] = 1;
		melting.clear();

		while (!q.isEmpty()) {
			Node curr = q.poll();
			for (int i = 0; i < 4; i++) {
				int nx = curr.x + dx[i];
				int ny = curr.y + dy[i];

				if (nx < 0 || nx >= N || ny < 0 || ny >= M)
					continue;

				if (cheese[nx][ny] == 1) { // 다음 칸이 치즈일 때
					visited[nx][ny]++;
					if (visited[nx][ny] == 2) { // 방문 횟수가 2이면
						melting.add(new Node(nx, ny)); // melting에 추가
					}
					continue;
				}

				if (visited[nx][ny] == 1) // 다음 칸이 공백인데 방문했을 때
					continue;

				visited[nx][ny] = 1; // 다음 칸이 공백이지만 방문하지 않았을 때
				q.add(new Node(nx, ny));

			}
		}
		for (Node curr : melting) { // 2면 이상 공기와 맞닿은 치즈가 녹음
			cheese[curr.x][curr.y] = 0;
		}
		leftCheese -= melting.size(); // 남은 치즈 수 감소
	}

	private static void inputs() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		cheese = new int[N][M];
		leftCheese = 0;

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				cheese[i][j] = Integer.parseInt(st.nextToken());
				if (cheese[i][j] == 1) {
					leftCheese++;
				}
			}
		}
	}
}