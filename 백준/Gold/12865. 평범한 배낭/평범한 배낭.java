import java.io.IOException;
import java.util.Scanner;

class Main {
	static class Item {
		int w, v;

		public Item(int w, int v) {
			this.w = w;
			this.v = v;
		}
	}

	static int N, K;
	static Item[] items;

	public static void main(String[] args) throws IOException {
		inputs();

		// 행 : 현재 물품 인덱스, 열 : 최대 무게
		int[][] dp = new int[N + 1][K + 1];

		for (int i = 1; i <= N; i++) {
			Item item = items[i]; // 현재 잡은 물건
			for (int j = 1; j <= K; j++) {
				if (item.w > j) // 현재 물품의 무게가 최대 무게보다 크면 이전 물품까지의 가치로 할당
					dp[i][j] = dp[i - 1][j];

				else { // 현재 물품의 무게가 최대 무게 이하이면 둘 중 큰 값을 할당
					dp[i][j] = Math.max(
							// 현재 아이템 포함. (최대 무게 - 현재 아이템 무게)한 dp값 + 현재 아이템 가치
							dp[i - 1][j - item.w] + item.v,
							// 현재 아이템 미포함. 이전 아이템까지의 가치
							dp[i - 1][j]);
				}
			}
		}
		System.out.println(dp[N][K]);
	}

	private static void inputs() throws IOException {
		Scanner sc = new Scanner(System.in);

		N = sc.nextInt();
		K = sc.nextInt();
		items = new Item[N + 1];

		for (int i = 1; i <= N; i++) {
			items[i] = new Item(sc.nextInt(), sc.nextInt());
		}

		sc.close();
	}
}