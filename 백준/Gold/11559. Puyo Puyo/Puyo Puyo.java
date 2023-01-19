import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Scanner;

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
	static char[][] arr;

	public static void main(String[] args) throws IOException {
		N = 12;
		M = 6;
		inputs();

		int ans = 0;

		while (findExplosion()) {
			gravity();
			ans++;
		}

		System.out.println(ans);
	}

	private static void gravity() {
		for (int i = 0; i < M; i++) {
			for (int j = N - 1; j >= 0; j--) {
				if (arr[j][i] != '.')
					continue; // 현재 좌표에 뿌요가 들어 있기 때문에 앞의 칸으로 넘어감

				boolean isEmpty = true; // 앞의 칸들이 모두 비어있는지
				// 현재 좌표는 공백이므로 앞의 좌표들을 확인하며 뿌요가 나오면 중력 적용
				for (int k = j - 1; k >= 0; k--) {
					if (arr[k][i] != '.') {
						arr[j][i] = arr[k][i];
						arr[k][i] = '.';
						isEmpty = false;
						break; // 하나 땡겼으니 다음 좌표로 넘어가기
					}
				}
				if (isEmpty) // 앞의 값들이 모두 비어있을 때는 더 볼 필요 없음
					break; // 다음 열로 넘어가기
			}
		}
	}

	private static boolean findExplosion() {
		Queue<Node> q = new LinkedList<>();
		boolean[][] visited;
		List<Node> explosionList = new ArrayList<>(); // 폭발할 좌표의 리스트

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (arr[i][j] != '.') { // 현재 좌표가 뿌요면 사방탐색 시작
					int cnt = 1; // 같은 색의 뿌요 갯수. 자기 포함하므로 1로 시작
					q.clear();
					visited = new boolean[N][M];
					q.add(new Node(i, j));
					visited[i][j] = true;
					explosionList.add(new Node(i, j)); // 자기 자신 리스트에 넣고 시작

					while (!q.isEmpty()) {
						Node curr = q.poll();
						for (int d = 0; d < 4; d++) {
							int nx = curr.x + dx[d];
							int ny = curr.y + dy[d];

							if (nx < 0 || nx >= N || ny < 0 || ny >= M)
								continue; // 범위 체크

							if (visited[nx][ny] || arr[nx][ny] != arr[i][j])
								continue; // 방문했는지, 현재 뿌요와 다른지 체크

							// 다음 좌표를 방문처리하고 큐, 폭발 리스트에 넣기
							q.add(new Node(nx, ny));
							visited[nx][ny] = true;
							explosionList.add(new Node(nx, ny));
							cnt++; // 같은 색의 뿌요 갯수 증가
						}
					}
					if (cnt < 4) {
						// 같은 색상의 뿌요가 4개가 안되면 리스트에 추가해놨던 좌표 다시 삭제
						for (int c = 0; c < cnt; c++)
							explosionList.remove(explosionList.size() - 1);
					}
				}
			}
		}
		for (Node node : explosionList) {
			arr[node.x][node.y] = '.'; // 리스트에 들어 있는 좌표들 모두 폭파
		}

		return !explosionList.isEmpty(); // 폭파 시킨게 있는지 여부를 반환
	}

	private static void inputs() throws IOException {
		Scanner sc = new Scanner(System.in);
		arr = new char[N][M];

		char[] input;
		for (int i = 0; i < N; i++) {
			input = sc.next().toCharArray();
			for (int j = 0; j < M; j++) {
				arr[i][j] = input[j];
			}
		}

		sc.close();
	}
}