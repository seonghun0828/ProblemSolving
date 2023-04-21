import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main {
	static int N, M;
	static int[] times;

	public static void main(String[] args) throws IOException {
		inputs();

		System.out.println(solution());
	}

	private static int solution() {
		if (N <= M) { // 아이보다 놀이기구가 많으면 시작하면서 마지막 아이까지 놀이기구에 탑승
			return N; // 마지막 아이의 번호를 그대로 반환
		}

		N -= M; // 0초에 우선 M명의 아이가 놀이기구에 탑승하기 때문에 N에서 차감

		// 마지막 아이가 타게 되는 시간 구하기 - 이분 탐색
		long lastTime = getLastChildTime();

		// lastTime 바로 이전 시간까지 총 몇 명의 아이가 탔는지 확인
		long prevTimeChildrenCnt = getChildrenCntAt(lastTime - 1);

		N -= prevTimeChildrenCnt; // 확인해야 할 아이 숫자에서 이전 시간까지 탔던 아이들 빼기

		// times 순회하며 lastTime에 마지막 아이가 몇 번 놀이기구 탔는지 확인
		int idx = 0;

		for (int i = 0; i < M; i++) {
			int playTime = times[i];
			if (lastTime % playTime == 0) {
				N--;
			}
			if (N == 0) { // 마지막 아이
				idx = i + 1;
				break;
			}
		}

		return idx;
	}

	private static long getLastChildTime() {
		long st = 0;
		long ed = 120000000000L; // 1200억. 최대 시간이 600억이기 때문에
		while (st <= ed) {
			long time = (st + ed) / 2;
			if (getChildrenCntAt(time) >= N) {
				ed = time - 1;
			} else {
				st = time + 1;
			}
		}
		return st;
	}

	private static long getChildrenCntAt(long time) {
		long cnt = 0; // 현재 시간까지 기구를 탄 아이들의 수

		for (int playTime : times) { // 현재 시간까지 해당 놀이기구를 탄 아이의 수
			cnt += (long) (time / playTime);
		}

		return cnt;
	}

	private static void inputs() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		times = new int[M];
		st = new StringTokenizer(br.readLine());

		for (int i = 0; i < M; i++) {
			times[i] = Integer.parseInt(st.nextToken());
		}

		br.close();
	}
}