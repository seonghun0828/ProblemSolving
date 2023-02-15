import java.io.IOException;
import java.util.Scanner;

class Main {
	public static void main(String[] args) throws IOException {
		Scanner sc = new Scanner(System.in);

		int N = sc.nextInt();
		int M = sc.nextInt(); // 소문제 번호
		boolean[] visited = new boolean[N + 1];
		long[] fact = new long[N]; // 팩토리얼 값 저장

		for (int i = 0; i < N; i++) {
			fact[i] = factorial(i);
		}

		long k;

		if (M == 1) { // 소문제 1번
			k = sc.nextLong();

			for (int i = 1; i <= N; i++) { // i는 순열의 인덱스
				for (int j = 1; j <= N; j++) { // j는 인덱스 위치에 들어갈 순열의 값
					if (visited[j]) // 이미 방문했다는 것은 수열에 사용한 숫자라는 뜻이므로 continue
						continue;

					if (fact[N - i] >= k) { // i번째 경우의 수가 k 이상이면
						System.out.print(j + " "); // 현재 j가 순열의 i번째 값이므로 바로 출력
						visited[j] = true;
						break; // i를 키워 경우의 수를 줄임
					}
					k -= fact[N - i]; // i번째 수열에 해당하는 fact 값 k에서 차감
				}
			}
		} else { // 소문제 2번
			int[] arr = new int[N + 1];
			for (int i = 1; i <= N; i++) {
				arr[i] = sc.nextInt();
			}
			k = 0;

			for (int i = 1; i <= N; i++) {
				for (int j = 1; j < arr[i]; j++) { // i 자리에 가능한 수들 중 낮은 숫자부터 순회
					if (visited[j]) // 이미 방문했다는 것은 수열에 사용한 숫자라는 뜻이므로 continue
						continue;

					k += fact[N - i];
				}
				visited[arr[i]] = true; // 현재 수열의 값 방문 처리
			}
			System.out.println(k + 1); // 앞에 k개 있기 때문에 현재 수열은 k + 1번째
		}

		sc.close();
	}

	private static long factorial(long num) {
		if (num <= 1)
			return 1;
		return num * factorial(num - 1);
	}
}