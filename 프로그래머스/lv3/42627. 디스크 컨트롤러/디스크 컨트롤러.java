import java.util.*;
class Solution {
    public int solution(int[][] jobs) {
        Arrays.sort(jobs, ((a, b) -> { // jobs 배열 시간 순으로 정렬, 같은 시간이면 소요 시간 순으로
			if (a[0] == b[0]) {
				return a[1] - b[1];
			}
			return a[0] - b[0];
		}));

		int idx = 0; // jobs 배열의 요소를 확인할 idx
		int sum = 0; // 요청부터 종료까지 걸린 시간의 총 합
		int currTime = 0; // 현재 작업이 종료되는 시각
		// PQ는 소요 시간을 기준으로 정렬해야 총 합이 최소가 됨
		PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[1] - b[1]);
		pq.add(jobs[idx++]); // pq에 jobs[0] 추가

		while (!pq.isEmpty()) { // pq가 비어 있을 때까지 반복
			int[] next = pq.poll(); // pq에서 실행할 다음 작업을 뽑음
			// 다음 작업이 종료되는 시각. 현재 작업 종료 시각 기준 다음 작업 요청 시간에 따라 달라짐.
			currTime = next[0] > currTime ? next[0] + next[1] : currTime + next[1];
			sum += currTime - next[0]; // sum에 (작업이 종료되는 시각 - 작업이 요청된 시간) 더함
			// 새로 갱신된 작업 종료 시간보다 요청 시간이 빠른 작업들은 전부 PQ(대기)에 넣음
			while (idx < jobs.length && currTime >= jobs[idx][0]) {
				pq.add(jobs[idx++]);
			}

			if (pq.isEmpty() && idx < jobs.length) {
				pq.add(jobs[idx++]); // 대기 중인 작업은 없지만 아직 작업이 남았으면 pq에 다음 작업 추가
			}
		}

		return (int) Math.floor(sum / jobs.length); // 총 걸린 시간을 작업 길이로 나눠 반환
    }
}