import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

class Main {
	static class Node {
		int v, dist;

		public Node(int v, int dist) {
			this.v = v;
			this.dist = dist;
		}
	}

	static final int MAX_VAL = 200000000;
	static int N, E, v1, v2;
	static List<Node>[] graph;

	public static void main(String[] args) throws IOException {
		inputs();

		System.out.println(solution());
	}

	private static int solution() {
		int v1Result = 0; // v1을 먼저 들르는 경로에서의 최단 길이
		int v2Result = 0; // v2를 먼저 들르는 경로에서의 최단 길이

		int[] v1Arr = new int[] { 1, v1, v2, N }; // v1을 먼저 들르는 경로 배열
		int[] v2Arr = new int[] { 1, v2, v1, N }; // v2를 먼저 들르는 경로 배열

		for (int i = 0; i < 3; i++) {
			int st = v1Arr[i]; // 시작 노드
			int ed = v1Arr[i + 1]; // 도착 노드
			if (st == ed) {
				continue;
			}
			int result = dijkstra(st, ed); // 시작 노드에서 시작해 도착 노드까지 도달하는 거리
			if (result == -1) { // result가 -1이면 해당 경로가 없음
				v1Result = MAX_VAL; // v1Result에 최댓값을 할당하고 반복문 탈출
				break;
			}
			v1Result += result; // v1Result에 거리 추가
		}

		for (int i = 0; i < 3; i++) {
			int st = v2Arr[i];
			int ed = v2Arr[i + 1];
			if (st == ed) {
				continue;
			}
			int result = dijkstra(st, ed);
			if (result == -1) {
				v2Result = MAX_VAL; // v1Result에 최댓값을 할당하고 반복문 탈출
				break;
			}
			v2Result += result;
		}

		if (v1Result == MAX_VAL && v2Result == MAX_VAL) // 둘 다 최댓값이면(경로가 없으면)
			return -1;
		else if (v1Result == MAX_VAL)
			return v2Result;
		else if (v2Result == MAX_VAL)
			return v1Result;
		return Math.min(v1Result, v2Result); // 최솟값 반환
	}

	private static int dijkstra(int st, int ed) { // st에서 시작해 ed까지 도달하는데 걸린 최소 거리
		PriorityQueue<Node> pq = new PriorityQueue<>((a, b) -> a.dist - b.dist);
		int[] d = new int[N + 1]; // st에서 각 노드까지 도달하는데 걸리는 거리
		pq.add(new Node(st, 0));
		for (int i = 1; i <= N; i++) {
			d[i] = MAX_VAL;
		}
		d[st] = 0; // st 노드에서 시작이므로 거리 0으로 갱신

		while (!pq.isEmpty()) {
			Node curr = pq.poll();

			for (Node next : graph[curr.v]) {
				if (curr.dist + next.dist < d[next.v]) {
					d[next.v] = curr.dist + next.dist;
					pq.add(new Node(next.v, d[next.v]));
				}
			}
		}

		return d[ed] == MAX_VAL ? -1 : d[ed]; // ed 노드에 도달하는데 걸리는 최소 거리 출력
	}

	private static void inputs() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] input = br.readLine().split(" ");

		N = Integer.parseInt(input[0]);
		E = Integer.parseInt(input[1]);
		graph = new ArrayList[N + 1];
		for (int i = 1; i <= N; i++) {
			graph[i] = new ArrayList<>();
		}

		for (int i = 0; i < E; i++) {
			input = br.readLine().split(" ");
			int a = Integer.parseInt(input[0]);
			int b = Integer.parseInt(input[1]);
			int c = Integer.parseInt(input[2]);
			graph[a].add(new Node(b, c));
			graph[b].add(new Node(a, c));
		}

		input = br.readLine().split(" ");
		v1 = Integer.parseInt(input[0]);
		v2 = Integer.parseInt(input[1]);

		br.close();
	}
}