import java.io.IOException;
import java.util.Scanner;

class Main {
	static int N, ans;
	static int[] arr, columns;

	public static void main(String[] args) throws IOException {
		/**
		 * 행 방향으로 한번 순회하며 모든 행 방향의 뒤집거나 놔두는 경우의 수를 구함 행 방향의 각 경우의 수에서 열 방향으로 현재 arr의 요소를
		 * 비트마스킹을 확인 열 방향으로 앞면 동전의 수를 columns 배열에 저장 columns[i]가 N/2보다 작으면 동전의 뒷면이 더 많은
		 * 것이기 때문에 해당 열 뒤집기
		 * 
		 * 시간 복잡도 행 방향 전체 탐색(2^20) * 열 방향 탐색(20 + 20) = 약 4천만
		 */
		inputs();
		ans = 400; // 가능한 최대 뒷면의 수

		recur(0); // 행 방향 완전 탐색 시작
		System.out.println(ans);
	}

	private static void recur(int idx) {
		if (idx == N) {
			int backCoinCnt = getBackCoinCnt(); // 현재 경우의 수의 동전 뒷면 수
			ans = Math.min(ans, backCoinCnt); // 최솟값 갱신
			return;
		}

		recur(idx + 1); // idx 번째 안뒤집고 다음 꺼로 넘어가기
		arr[idx] = reverse(arr[idx]);
		recur(idx + 1); // idx 번째 뒤집고 다음 꺼로 넘어가기
	}

	private static int getBackCoinCnt() {
		int totalCnt = 0; // 동전 뒷면의 수

		columns = new int[N]; // 각 열의 동전 앞면의 수
		for (int row : arr) { // arr의 한 행
			for (int i = 0; i < N; i++) { // 비트마스킹을 위해 N번 순회
				if ((row & 1 << i) > 0) { // row의 i번째 자리가 1이면
					columns[i]++; // i번째 열의 1의 수 증가
				}
			}
		}
		for (int i = 0; i < N; i++) { // i번째 열에 앞면의 수에 따라 동전 뒷면의 수 다르게 더해주기
			totalCnt += columns[i] > N / 2 ? N - columns[i] : columns[i];
		}

		return totalCnt;
	}

	private static void inputs() throws IOException {
		Scanner sc = new Scanner(System.in);

		N = sc.nextInt();
		arr = new int[N];
		for (int i = 0; i < N; i++) {
			char[] input = sc.next().toCharArray();
			for (int j = 0; j < N; j++) {
				if (input[j] == 'H') {
					arr[i] += 1 << (N - j - 1);
				}
			}
		}

		sc.close();
	}

	public static int reverse(int num) { // 비트마스킹으로 num의 전체 자릿수 뒤집기
		return num ^ ((1 << N) - 1);
	}
}