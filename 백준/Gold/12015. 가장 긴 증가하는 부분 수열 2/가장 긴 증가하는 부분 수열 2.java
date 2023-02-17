import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

class Main {
	static int N;
	static int[] arr;
	static List<Integer> dp;

	public static void main(String[] args) throws IOException {
		inputs();

		/**
		 * dp List를 만들어 arr[i]가 dp의 최댓값보다 크면 바로 push,
		 * 작으면 dp 이분탐색해서 나온 idx에 넣기.
		 * 
		 * 그렇게 되면 dp는 기존 arr에서 나올 수 있는 수열 형태는 아니지만 길이는 실제 LIS와 같게 됨.
		 * dp[idx]를 arr[i]로 대체하면서 LIS는 더 증가할 수 있게 되므로 이 방법을 사용
		 */
		dp = new ArrayList<>();
		dp.add(arr[0]);  // dp에 첫 요소로 arr 첫 번째 요소 추가

		for (int i = 1; i < N; i++) {  // 두 번째 요소부터 순회
			if (arr[i] > dp.get(dp.size() - 1)) {
				dp.add(arr[i]);  // arr[i]가 dp 최댓값보다 크면 바로 dp에 추가
			} else {
				int idx = findIdx(arr[i]);  // arr[i]가 dp에 들어갈 적절한 idx 찾기
				dp.set(idx, arr[i]);  // 그 자리값으로 대체
			}
		}
		System.out.println(dp.size());  // dp가 곧 LIS이므로 size 출력
	}

	private static int findIdx(int num) {  // 이분탐색
		int st = 0;
		int ed = dp.size() - 1;
		int mid;

		while (st <= ed) {
			mid = (int) ((st + ed) / 2);
			if (dp.get(mid) >= num) {
				ed = mid - 1;
			} else {
				st = mid + 1;
			}
		}
		return st;
	}

	private static void inputs() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		arr = new int[N];
		StringTokenizer stnz = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(stnz.nextToken());
		}

		br.close();
	}
}