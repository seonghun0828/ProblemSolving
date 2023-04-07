import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

class Main {
	static int N, K;
	static final int MAX = 100000;
	static int[] visited, parent;

	public static void main(String[] args) throws IOException {
		inputs();

		visited = new int[MAX + 1]; // 방문한 시간을 저장
		parent = new int[MAX + 1]; // 현재 위치 이전 위치(부모)를 저장

		bfs();
		System.out.println(visited[K] - 1);

		Stack<Integer> stack = new Stack<>();
		while (K != N) {
			stack.add(K);
			K = parent[K];
		}

		StringBuilder sb = new StringBuilder(N + " ");
		while (!stack.isEmpty()) {
			sb.append(stack.pop() + " ");
		}
		System.out.println(sb);
	}

	private static void bfs() {
		Queue<Integer> q = new LinkedList<>();
		q.add(N);
		visited[N] = 1; // 처음 N을 방문한 시간을 1로 설정
		parent[N] = N; // N의 부모는 N으로 설정

		while (!q.isEmpty()) {
			int curr = q.poll();
			if (curr == K) { // 동생을 찾으면 break
				break;
			}

			for (int next : new int[] { curr - 1, curr + 1, curr * 2 }) { // curr 기준 수빈이의 이동 가능 위치 3개
				if (next < 0 || next > MAX) // 범위 체크
					continue;

				if (visited[next] > 0) // 방문 처리
					continue;

				q.add(next);
				visited[next] = visited[curr] + 1;
				parent[next] = curr; // next의 부모로 curr 설정
			}
		}
	}

	private static void inputs() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] input = br.readLine().split(" ");
		N = Integer.parseInt(input[0]);
		K = Integer.parseInt(input[1]);
	}
}