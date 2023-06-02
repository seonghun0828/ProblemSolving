import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

class Main {

	static int N, ans;
	static int[] dx = { -1, 0, 1, 0 };
	static int[] dy = { 0, 1, 0, -1 };
	static int[][] arr;
	static Set<String> mySet;

	public static void main(String[] args) throws IOException {
		inputs();

		System.out.println(solution());
	}

	private static int solution() {
		mySet = new HashSet<>(); // 합친 블록의 좌표 저장
		recur(0);

		return ans;
	}

	private static void recur(int cnt) {
		if (cnt == 5) {
			return;
		}

		for (int i = 0; i < 4; i++) {
			int[][] copied = copy();
			moveByDirection(i);
			recur(cnt + 1);
			arr = copied; // 이동시킨 arr 초기화
		}
	}

	private static void moveByDirection(int d) {
		mySet.clear(); // 한 번 전체 블록을 이동시킬 때 mySet 초기화
		switch (d) { // 이동하는 방향에 따라 블록 순회 방향을 다르게
		case 0:
		case 3: // 0(위), 3(왼쪽) 방향은 정방향으로 순회
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if (arr[i][j] == 0)
						continue;
					move(i, j, d);
				}
			}
			break;
		case 1: // 1(오른쪽) 방향은 오른쪽 끝에서부터 순회
			for (int i = 0; i < N; i++) {
				for (int j = N - 1; j >= 0; j--) {
					if (arr[i][j] == 0)
						continue;
					move(i, j, d);
				}
			}
			break;
		case 2: // 2(아래쪽) 방향은 아래쪽 끝에서부터 순회
			for (int i = N - 1; i >= 0; i--) {
				for (int j = 0; j < N; j++) {
					if (arr[i][j] == 0)
						continue;
					move(i, j, d);
				}
			}
			break;
		}
	}

	private static void move(int i, int j, int d) {
		int nx = i + dx[d]; // 다음 x좌표
		int ny = j + dy[d]; // 다음 y좌표
		while (nx >= 0 && nx < N && ny >= 0 && ny < N) {
			if (arr[nx][ny] == 0) { // 다음 블록이 0이면 현재 블록과 바꾸기
				arr[nx][ny] = arr[i][j];
				arr[i][j] = 0;
				i = nx;
				j = ny;
				nx = i + dx[d];// 다음 좌표로 이동
				ny = j + dy[d];
				continue;
			}
			// 다음 블록이 0이 아닐 때 이전 블록과 다음 블록을 합치는 경우
			if (arr[nx][ny] == arr[i][j] && !mySet.contains(nx + "," + ny)) {
				arr[nx][ny] *= 2;
				arr[i][j] = 0;
				mySet.add(nx + "," + ny); // 다음 블록 좌표를 set에 추가
				ans = Math.max(ans, arr[nx][ny]); // ans 최댓값 갱신
			}
			break;
		}
	}

	private static int[][] copy() {
		int[][] copied = new int[N][N];
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				copied[i][j] = arr[i][j];
			}
		}
		return copied;
	}

	private static void inputs() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		arr = new int[N][N];
		StringTokenizer st;
		ans = 0;

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
				ans = Math.max(ans, arr[i][j]); // 초기 블록 최댓값 구하기
			}
		}

		br.close();
	}
}