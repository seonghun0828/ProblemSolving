import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

class Main {
	static class Node {
		// firstVisitNode : 최단경로의 해당 노드까지 오는데 가장 먼저 거친 집하장
		int firstVisitNode, v, dist; // v : 노드 번호, dist : 이동에 걸리는 거리

		public Node(int firstVisitNode, int v, int dist) {
			this.firstVisitNode = firstVisitNode;
			this.v = v;
			this.dist = dist;
		}
	}

	static int N, M;
	static int[] dist; // 최단 경로 위해 저장해 놓는 최단 거리
	static int[][] arr; // 정답 배열
	static List<Node>[] list; // 간선을 반영한 인접 리스트
	static PriorityQueue<Node> pq = new PriorityQueue<>((a, b) -> a.dist - b.dist);

	public static void main(String[] args) throws IOException {
		inputs();

		arr = new int[N + 1][N + 1];
		for (int i = 1; i <= N; i++) {
			dijkstraFrom(i); // i 노드에서 시작하는 다익스트라 로직을 돌려서 arr 배열 채우기
		}
		StringBuilder sb = new StringBuilder();
		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= N; j++) {
				if (i == j) {
					sb.append("- ");
					continue;
				}
				sb.append(arr[i][j] + " ");
			}
			sb.append("\n");
		}
		System.out.print(sb);
	}

	private static void dijkstraFrom(int start) {
		dist = new int[N + 1];
		for (int i = 1; i <= N; i++) {
			dist[i] = Integer.MAX_VALUE;
		}
		pq.clear();
		dist[start] = 0; // start 노드에서 시작하기 때문에 거리 0으로 갱신
		pq.add(new Node(0, start, 0));
		int cnt = 0; // 이은 최단 거리 간선의 갯수

		while (!pq.isEmpty()) {
			Node curr = pq.poll();
			for (Node next : list[curr.v]) {
				if (dist[next.v] > dist[curr.v] + next.dist) {
					if (cnt > M) // 간선의 갯수가 M을 초과하면 while문 탈출
						break;

					// curr.FVN가 0이면 curr가 시작 노드이기 때문에 next.FVN는 next.v가 됨.
					// 0이 아니면 이미 시작 노드가 있다는 것이므로 curr.FVN를 그대로 이어감.
					int firstVisitNode = curr.firstVisitNode == 0 ? next.v : curr.firstVisitNode;
					dist[next.v] = dist[curr.v] + next.dist;
					pq.add(new Node(firstVisitNode, next.v, dist[next.v]));
					arr[start][next.v] = firstVisitNode; // arr에 FVN 갱신
					cnt++;
				}
			}
		}
	}

	private static void inputs() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stnz = new StringTokenizer(br.readLine());
		N = Integer.parseInt(stnz.nextToken());
		M = Integer.parseInt(stnz.nextToken());
		list = new ArrayList[N + 1];
		for (int i = 1; i <= N; i++) {
			list[i] = new ArrayList<>();
		}
		for (int i = 0; i < M; i++) {
			stnz = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(stnz.nextToken());
			int b = Integer.parseInt(stnz.nextToken());
			int w = Integer.parseInt(stnz.nextToken());
			list[a].add(new Node(0, b, w));
			list[b].add(new Node(0, a, w));
		}
		br.close();
	}
}