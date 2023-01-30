import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;

class Main {
	static int N;
	static int[][] arr;

	public static void main(String[] args) throws IOException {
		inputs();

		/*
		 * 교차하지 않기 위해 없앨 전깃줄의 최소 개수 X는 X = 전체 전깃줄(N) - 교차 없이 가능한 최대 전깃줄 교차 없이 가능한 최대
		 * 전깃줄의 개수를 구해 N에서 뺀 값이 정답 X
		 */

		// B에 연결된 위치만 보고 교차 여부를 확인하기 위해 오름차순 정렬
		Arrays.sort(arr, (a, b) -> a[0] - b[0]);

		int[] dp = new int[N]; // A의 i번째까지 놓을 수 있는 전깃줄 최대 개수
		int max = 0; // 교차 없이 가능한 최대 전깃줄 개수

		for (int i = 0; i < N; i++) {
			dp[i] = 1; // 자기 자신에는 전깃줄 놓을 수 있으니 1로 초기화

			for (int j = 0; j < i; j++) {
				if (arr[i][1] < arr[j][1])
					continue; // j 위치에 놓으면 현재 i 전깃줄과 교차하므로 무시

				// j 위치에 놔도 괜찮으므로 j 위치에 놨을 때 최대 전깃줄 + 1 과 현재 값 비교
				dp[i] = Math.max(dp[i], dp[j] + 1);
			}
			max = Math.max(max, dp[i]);
		}

		System.out.println(N - max);
	}

	private static void inputs() throws IOException {
		Scanner sc = new Scanner(System.in);

		N = sc.nextInt();
		arr = new int[N][2];

		for (int i = 0; i < N; i++) {
			arr[i] = new int[] { sc.nextInt(), sc.nextInt() };
		}

		sc.close();
	}
}