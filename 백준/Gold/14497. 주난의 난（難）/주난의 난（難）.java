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

	static int N, M, x1, y1, x2, y2;
	static int[] dx = { 1, -1, 0, 0 };
	static int[] dy = { 0, 0, 1, -1 };
	static int[][] arr;

	public static void main(String[] args) throws IOException {
		inputs();

		int cnt = 0;
		while (true) {
			cnt++;
			if (succeedCatching()) {
				break;
			}
		}
		System.out.println(cnt);
	}

	private static boolean succeedCatching() { // 범인을 잡았는지 여부를 반환
		Queue<Node> q = new LinkedList<>();
		boolean[][] visited = new boolean[N][M];
		q.add(new Node(x1, y1));
		visited[x1][y1] = true;

		while (!q.isEmpty()) {
			Node curr = q.poll();
			for (int i = 0; i < 4; i++) {
				int nx = curr.x + dx[i];
				int ny = curr.y + dy[i];

				if (nx < 0 || nx >= N || ny < 0 || ny >= M || visited[nx][ny]) {
					continue; // 범위 초과 or 방문한 좌표
				}

				if (nx == x2 && ny == y2) { // 범인을 잡으면 true 리턴
					return true;
				}

				if (arr[nx][ny] == 0) { // 0이면 파장이 진행하기 때문에 q에 다음 노드 추가
					q.add(new Node(nx, ny));
				} else { // 1이면 파장이 끝나고 친구가 쓰러져 빈 공간이 되기 때문에 arr 값 0으로 할당
					arr[nx][ny] = 0;
				}
				visited[nx][ny] = true; // 방문 처리
			}
		}

		return false; // 범인을 못잡았으니 false 반환
	}

	private static void inputs() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		String[] input = br.readLine().split(" ");
		N = Integer.parseInt(input[0]);
		M = Integer.parseInt(input[1]);

		input = br.readLine().split(" ");
		x1 = Integer.parseInt(input[0]) - 1;
		y1 = Integer.parseInt(input[1]) - 1;
		x2 = Integer.parseInt(input[2]) - 1;
		y2 = Integer.parseInt(input[3]) - 1;

		arr = new int[N][M];
		char[] tmp;
		for (int i = 0; i < N; i++) {
			tmp = br.readLine().toCharArray();
			for (int j = 0; j < M; j++) {
				if (tmp[j] == '#' || tmp[j] == '*') { // 어차피 주난이와 범인 좌표 주어짐
					arr[i][j] = 0; // int[][]로 하기 위해 0으로 초기화
					continue;
				}
				arr[i][j] = tmp[j] - '0';
			}

		}

		br.close();
	}
}