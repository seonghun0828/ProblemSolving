import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main {
	static int N, M;
	static int[] heights;
	static int[][] orders;

	public static void main(String[] args) throws IOException {
		inputs();

		int[] sum = new int[N + 2]; // 0번부터 N + 1번까지

		for (int[] order : orders) {
			/**
			 * a부터 b까지의 구간에 k를 더하기 위해서는 a에 k를 더하고 누적합을 시키면 됨. 단, b에서 누적합이 멈춰야하므로 b + 1에서 -k를
			 * 더해 상쇄시킴.
			 */
			int a = order[0];
			int b = order[1];
			int k = order[2];
			sum[a] += k;
			sum[b + 1] += k * -1;
		}
		for (int i = 1; i < N + 2; i++) {
			sum[i] += sum[i - 1]; // 누적합 구하기
		}

		StringBuilder sb = new StringBuilder();
		for (int i = 1; i < N + 1; i++) {
			sb.append(heights[i] + sum[i] + " ");
		}
		System.out.println(sb);
	}

	private static void inputs() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		heights = new int[N + 1];
		st = new StringTokenizer(br.readLine());
		for (int i = 1; i <= N; i++) {
			heights[i] = Integer.parseInt(st.nextToken());
		}
		orders = new int[M][3];
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			orders[i][0] = Integer.parseInt(st.nextToken());
			orders[i][1] = Integer.parseInt(st.nextToken());
			orders[i][2] = Integer.parseInt(st.nextToken());
		}
	}
}