import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

class Main {
	static int T, W;
	static int[] tree;

	public static void main(String[] args) throws IOException {
		inputs();
		System.out.println(solution());
	}

	private static void inputs() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] tmp = br.readLine().split(" ");
		T = Integer.parseInt(tmp[0]);
		W = Integer.parseInt(tmp[1]);
		tree = new int[T + 1];
		tree[0] = 0;

		for (int i = 1; i <= T; i++) {
			tree[i] = Integer.parseInt(br.readLine());
		}

		br.close();
	}

	private static int solution() {
		int ans = 0;
		int[][] dp = new int[T + 1][W + 1];

		for (int i = 1; i <= T; i++) {
			for (int j = 0; j <= W; j++) {
				if (tree[i] == 1) { // 자두가 1번 나무에서 떨어짐
					if (j % 2 == 0) { // 짝수 번 이동 -> 현재 1번 위치 -> 이전 값에 + 1
						dp[i][j] = Math.max(dp[i][j], dp[i - 1][j] + 1);
						if (j > 0) {
							// 2번 위치에서 1번으로 이동하며 + 1
							dp[i][j] = Math.max(dp[i][j], dp[i - 1][j - 1] + 1);
						}
					} else { // 홀수 번 이동 -> 현재 2번 위치 -> 이전 값 그대로 사용
						dp[i][j] = dp[i - 1][j];
					}
				} else { // 자두가 2번 나무에서 떨어짐
					if (j % 2 == 0) { // 짝수 번 이동 -> 현재 1번 위치 -> 이전 값 그대로 사용
						dp[i][j] = dp[i - 1][j];
					} else { // 홀수 번 이동 -> 현재 2번 위치 -> 이전 값에 + 1
						dp[i][j] = Math.max(dp[i][j], dp[i - 1][j] + 1);
						if (j > 0) {
							// 2번 위치 -> 1번으로 이동하며 + 1
							dp[i][j] = Math.max(dp[i][j], dp[i - 1][j - 1] + 1);
						}
					}
				}
				ans = Math.max(dp[i][j], ans);
			}
		}

		return ans;
	}
}