import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

class Main {
	static int N, M;
	static PriorityQueue<Integer> pq;

	public static void main(String[] args) throws IOException {
		inputs();
		int time = 0;

		while (!pq.isEmpty()) {
			int maxNum = pq.poll();
			int sum = maxNum * (M - 1);

			while (!pq.isEmpty() && sum > 0) {
				sum -= pq.poll();
			}
			time += maxNum;
		}

		System.out.println(time);
	}

	private static void inputs() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		pq = new PriorityQueue<>((a, b) -> b - a);
		st = new StringTokenizer(br.readLine());

		for (int i = 0; i < N; i++) {
			pq.add(Integer.parseInt(st.nextToken()));
		}

		br.close();
	}
}