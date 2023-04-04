import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

class Main {
	static class CCTV {
		int x, y, num;

		public CCTV(int x, int y, int num) {
			this.x = x;
			this.y = y;
			this.num = num;
		}
	}

	static int N, M, emptyCnt, maxSum;
	static int[] dx = { 1, 0, -1, 0 };
	static int[] dy = { 0, 1, 0, -1 };
	static int[][] firstArr;
	static List<CCTV> cctvList;
	static Map<Integer, List<List<int[]>>> map;

	public static void main(String[] args) throws IOException {
		inputs();
		makeMap(); // cctv 번호와 진행 방향 list를 매칭하는 map 생성

		maxSum = 0;
		recur(0, 0, firstArr);
		System.out.println(emptyCnt - maxSum);
	}

	private static void recur(int idx, int sum, int[][] arr) {
		if (idx == cctvList.size()) { // cctvList를 다 봤으면 maxSum 갱신
			maxSum = Math.max(maxSum, sum);
			return;
		}
		CCTV curr = cctvList.get(idx);
		for (List<int[]> dir : map.get(curr.num)) { // 현재 CCTV가 확인해야 할 방향 리스트
			int[][] copied = copy(arr);
			int cnt = 0; // 이번에 CCTV가 도달한 칸의 수
			for (int[] d : dir) { // 방향 리스트의 한 방향
				cnt += watch(copied, curr, d);
			}
			recur(idx + 1, sum + cnt, copied);
		}

	}

	private static int watch(int[][] arr, CCTV cctv, int[] d) { // 진행 방향에 따라 cctv 가동
		int cnt = 0; // cctv가 도달한 칸의 범위. 이전에 도달한 칸 세지 않음
		int k = 0;

		int nx = cctv.x;
		int ny = cctv.y;

		while (nx >= 0 && nx < N && ny >= 0 && ny < M) {
			if (arr[nx][ny] == 6) { // 벽이면 가동 종료
				break;
			}

			if (arr[nx][ny] == 0) { // 빈 칸이면 cnt 세고, 배열 값 현재 cctv와 같은 num으로 갱신(방문처리)
				cnt++;
				arr[nx][ny] = cctv.num;
			}
			k++;
			nx = cctv.x + d[0] * k;
			ny = cctv.y + d[1] * k;
		}
		return cnt;
	}

	private static int[][] copy(int[][] arr) {
		int[][] copied = new int[N][M];
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				copied[i][j] = arr[i][j];
			}
		}
		return copied;
	}

	private static void makeMap() { // cctv 번호에 맞게 진행 방향 list를 생성하고 매핑
		map = new HashMap<>();
		List<List<int[]>> list1 = new ArrayList<>();
		for (int i = 0; i < 4; i++) {
			List<int[]> tmp = new ArrayList<>();
			tmp.add(new int[] { dx[i], dy[i] });
			list1.add(tmp);
		}
		map.put(1, list1);

		List<List<int[]>> list2 = new ArrayList<>();
		for (int i = 0; i < 2; i++) {
			List<int[]> tmp = new ArrayList<>();
			tmp.add(new int[] { dx[i], dy[i] });
			tmp.add(new int[] { dx[i + 2], dy[i + 2] });
			list2.add(tmp);
		}
		map.put(2, list2);

		List<List<int[]>> list3 = new ArrayList<>();
		for (int i = 0; i < 4; i++) {
			List<int[]> tmp = new ArrayList<>();
			tmp.add(new int[] { dx[i], dy[i] });
			tmp.add(new int[] { dx[(i + 1) % 4], dy[(i + 1) % 4] });
			list3.add(tmp);
		}
		map.put(3, list3);

		List<List<int[]>> list4 = new ArrayList<>();
		for (int i = 0; i < 4; i++) {
			List<int[]> tmp = new ArrayList<>();
			tmp.add(new int[] { dx[i], dy[i] });
			tmp.add(new int[] { dx[(i + 1) % 4], dy[(i + 1) % 4] });
			tmp.add(new int[] { dx[(i + 2) % 4], dy[(i + 2) % 4] });
			list4.add(tmp);
		}
		map.put(4, list4);

		List<List<int[]>> list5 = new ArrayList<>();
		List<int[]> tmp = new ArrayList<>();
		for (int i = 0; i < 4; i++) {
			tmp.add(new int[] { dx[i], dy[i] });
		}
		list5.add(tmp);
		map.put(5, list5);
	}

	private static void inputs() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		firstArr = new int[N][M];
		emptyCnt = 0;
		cctvList = new ArrayList<>();

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				firstArr[i][j] = Integer.parseInt(st.nextToken());
				if (firstArr[i][j] == 0)
					emptyCnt++;
				else if (firstArr[i][j] < 6)
					cctvList.add(new CCTV(i, j, firstArr[i][j]));
			}
		}
	}
}