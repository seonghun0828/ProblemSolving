import java.io.IOException;
import java.util.Scanner;

class Main {

	static int R, C, ans;
	static int[] dx = { 1, -1, 0, 0 };
	static int[] dy = { 0, 0, 1, -1 };
	static int[][] arr;

	public static void main(String[] args) throws IOException {
		inputs();

		ans = 0;
		dfs(0, 0, 1 << arr[0][0], 1); // 첫번째 칸 알파벳 방문처리 하면서 dfs 시작

		System.out.println(ans);
	}

	private static void dfs(int x, int y, int visited, int cnt) {
		ans = Math.max(ans, cnt); // 최대 칸 수 갱신

		for (int i = 0; i < 4; i++) {
			int nx = x + dx[i];
			int ny = y + dy[i];

			if (nx < 0 || nx >= R || ny < 0 || ny >= C) // 범위 체크
				continue;

			if ((visited & 1 << arr[nx][ny]) > 0) // 다음 칸 알파벳 이전에 이미 방문
				continue;

			// 다음 칸 알파벳 방문 처리 하고 dfs 탐색
			dfs(nx, ny, visited | 1 << arr[nx][ny], cnt + 1);
		}
	}

	private static void inputs() throws IOException {
		Scanner sc = new Scanner(System.in);

		R = sc.nextInt();
		C = sc.nextInt();
		arr = new int[R][C];

		char[] input;
		for (int i = 0; i < R; i++) {
			input = sc.next().toCharArray();
			for (int j = 0; j < C; j++) {
				arr[i][j] = input[j] - 'A'; // 알파벳을 숫자로 입력 받기(비트마스킹 목적)
			}
		}

		sc.close();
	}
}