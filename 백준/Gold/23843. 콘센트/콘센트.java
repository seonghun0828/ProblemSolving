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
		int totalTime = 0; // 충전하는데 걸리는 총 시간

		while (!pq.isEmpty()) {
			int LongestDeviceChargingTime = pq.poll(); // 현재 PQ 안에 있는 가장 긴 전자기기 충전 시간(LDCT)
			// 충전시간이 가장 긴 전자기기가 있으면 우선 그 시간만큼은 충전을 해야 한다.
			// 이미 시간이 가장 오래걸리는 전자기기는 충전 중이므로 충전 가능한 콘센트는 (M - 1) 개
			// 다른 전자기기들은 LDCT 안에 총 possibleChargingTime 만큼 충전할 수 있다.
			int possibleChargingTime = LongestDeviceChargingTime * (M - 1);

			// possibleChargingTime이 0이 될 때까지 반복한다.
			while (!pq.isEmpty() && possibleChargingTime > 0) {
				// PQ에서 그 다음으로 긴 충전시간을 꺼내 possibleChargingTime을 감소시켜 나간다.
				possibleChargingTime -= pq.poll();
			}
			totalTime += LongestDeviceChargingTime; // 총 시간에 LDCT을 더한다.
		}

		System.out.println(totalTime);
	}

	private static void inputs() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		pq = new PriorityQueue<>((a, b) -> b - a); // 내림차순 정렬하는 PriorityQueue
		st = new StringTokenizer(br.readLine());

		for (int i = 0; i < N; i++) {
			pq.add(Integer.parseInt(st.nextToken()));
		}

		br.close();
	}
}