import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

class Main {
	static class Bus {
		int prev, city, cost;

		Bus(int prev, int city, int cost) {
			this.prev = prev; // 이전 도시
			this.city = city; // 현재 도시
			this.cost = cost; // 비용
		}
	}

	static int N, M, st, ed;
	static List<Bus>[] list;

	public static void main(String[] args) throws IOException {
		inputs();

		PriorityQueue<Bus> pq = new PriorityQueue<>((a, b) -> a.cost - b.cost);
		// { 현재 도시 : 이전 도시 } 관계를 나타내는 map. map을 통해 도착 도시에서 출발 도시까지 순회 가능
		Map<Integer, Integer> map = new HashMap<>();
		int[] dist = new int[N + 1];
		for (int i = 1; i <= N; i++)
			dist[i] = Integer.MAX_VALUE;

		pq.add(new Bus(0, st, 0)); // 이전 도시는 없으므로 0으로 하고 현재 도시를 st로 하는 버스 추가
		dist[st] = 0;
		map.put(st, 0);

		while (true) { // dijkstra
			Bus curr = pq.poll();
			if (curr.city == ed) // 현재 도시가 도착 도시면 반복문 종료
				break;

			for (Bus next : list[curr.city]) { // 현재 도시에서 출발하는 다음 도시들 순회
				if (dist[next.city] > dist[curr.city] + next.cost) {
					dist[next.city] = dist[curr.city] + next.cost;
					map.put(next.city, curr.city); // pq에서 Bus를 꺼내면 map에 경로 저장
					pq.add(new Bus(curr.city, next.city, dist[next.city]));
				}
			}
		}
		List<Integer> stack = new ArrayList<>(); // st -> ed 까지의 경로를 스택으로 저장
		int key = ed; // map에서 경로를 탐색하기 위해 key를 ed로 시작
		while (key > 0) { // st는 이전 도시가 0이기 때문에 ed에서 st까지 탐색
			stack.add(key);
			key = map.get(key); // map의 value 값을 key로 갱신.
		}

		System.out.println(dist[ed]); // 최소 비용 출력
		System.out.println(stack.size()); // 경로의 도시 개수 출력
		StringBuilder sb = new StringBuilder();
		while (stack.size() > 0) { // 경로를 출력
			sb.append(stack.remove(stack.size() - 1) + " ");
		}
		System.out.println(sb);
	}

	private static void inputs() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stnz;
		N = Integer.parseInt(br.readLine());
		M = Integer.parseInt(br.readLine());
		list = new ArrayList[N + 1];
		for (int i = 1; i <= N; i++)
			list[i] = new ArrayList<>();

		for (int i = 0; i < M; i++) {
			stnz = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(stnz.nextToken());
			int b = Integer.parseInt(stnz.nextToken());
			int w = Integer.parseInt(stnz.nextToken());

			list[a].add(new Bus(a, b, w));
		}
		stnz = new StringTokenizer(br.readLine());
		st = Integer.parseInt(stnz.nextToken());
		ed = Integer.parseInt(stnz.nextToken());

		br.close();
	}
}