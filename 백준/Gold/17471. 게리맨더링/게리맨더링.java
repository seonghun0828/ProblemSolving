import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Scanner;

class Main {
	static int N, ans;
	static int[] population;
	static boolean[] firstSection;
	static List<Integer>[] graph;

	public static void main(String[] args) throws IOException {
		inputs();

		System.out.println(solution());
	}

	private static int solution() {
		ans = 900;  // 두 선거구 인구 차이의 최댓값 899 + 1
		firstSection = new boolean[N];  // i번째 구역이 첫번째 선거구인지

		for (int r = 1; r < N; r++) {  // N개 중 r개 조합을 뽑는 경우를 구하기
			recur(0, r);
		}

		return ans == 900 ? -1 : ans;
	}

	private static void recur(int idx, int cnt) {
		if (cnt == 0) {  // r개를 다 뽑았으면 두 선거구가 가능한 지 확인
			if (isPossibleSection()) {  // 가능하면 두 선거구의 인구 차를 비교, 갱신
				int diff = getDiffFromTwoSection();
				ans = Math.min(diff, ans);
			}
			return;
		}

		for (int i = idx; i < N; i++) {  // firstSection의 가능한 조합 구하기
			firstSection[i] = true;
			recur(i + 1, cnt - 1);
			firstSection[i] = false;
		}
	}

	private static int getDiffFromTwoSection() {  // 두 선거구의 인구수 차이
		int firstSectionPopulation = 0;
		int secondSectionPopulation = 0;
		for (int i = 0; i < N; i++) {
			if (firstSection[i]) {
				firstSectionPopulation += population[i];
			} else {
				secondSectionPopulation += population[i];
			}
		}
		return Math.abs(firstSectionPopulation - secondSectionPopulation);
	}

	private static boolean isPossibleSection() {
		List<Integer> aList = new ArrayList<>();
		List<Integer> bList = new ArrayList<>();

		for (int i = 0; i < N; i++) {  // 두 선거구에 속한 구역을 리스트로 나누기
			if (firstSection[i]) {
				aList.add(i);
			} else {
				bList.add(i);
			}
		}

		// 각 선거구에 속한 구역 하나를 bfs 탐색해 나온 탐색한 구역들의 cnt 구하기
		int aCnt = bfs(aList.get(0), aList.size(), true);
		int bCnt = bfs(bList.get(0), bList.size(), false);

		if (aCnt == aList.size() && bCnt == bList.size()) {
			return true;  // 두 cnt와 선거구의 숫자가 각각 같으면 가능한 선거구 획정 방법
		}
		return false;
	}

	private static int bfs(int start, int size, boolean isFirstSection) {
		int cnt = 1;
		Queue<Integer> q = new LinkedList<>();
		boolean[] visited = new boolean[N];
		q.add(start);
		visited[start] = true;

		while (!q.isEmpty()) {
			int curr = q.poll();
			for (int next : graph[curr]) {
				if (visited[next]) {
					continue;
				}
				if (firstSection[next] == isFirstSection) {  // 다음 구역이 현재 선거구에 있을 때
					q.add(next);
					visited[next] = true;
					cnt++;
				}
			}
		}
		return cnt;
	}

	private static void inputs() throws IOException {
		Scanner sc = new Scanner(System.in);

		N = sc.nextInt();
		population = new int[N];
		for (int i = 0; i < N; i++) {
			population[i] = sc.nextInt();
		}
		graph = new ArrayList[N];
		for (int i = 0; i < N; i++) {
			graph[i] = new ArrayList<>();
		}

		for (int i = 0; i < N; i++) {
			int size = sc.nextInt();
			for (int j = 0; j < size; j++) {
				graph[i].add(sc.nextInt() - 1);
			}
		}
		sc.close();
	}
}