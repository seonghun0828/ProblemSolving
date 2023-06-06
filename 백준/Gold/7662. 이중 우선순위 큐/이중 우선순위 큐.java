import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

class Main {

	static int N, ans;
	static int MAX_INT = (1 << 31) - 1;
	static int MIN_INT = 1 << 31;
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));;
	static Map<Integer, Integer> pqMap;
	static PriorityQueue<Integer> maxPq;
	static PriorityQueue<Integer> minPq;

	public static void main(String[] args) throws IOException {
		int T = Integer.parseInt(br.readLine());
		for (int tc = 0; tc < T; tc++) {
			System.out.println(solution());
		}
	}

	private static String solution() throws IOException {
		int k = Integer.parseInt(br.readLine());
		/**
		 * pqMap : 각 연산이 모두 반영된 진짜 정수들의 집합. { 정수 : 정수의 개수 } maxPq : 최댓값을 pop하는 pq이지만 최솟값
		 * pop이 즉시 반영되지는 않음 minPq : 최솟값을 pop하는 pq이지만 최댓값 pop이 즉시 반영되지는 않음
		 */
		pqMap = new HashMap<>();
		maxPq = new PriorityQueue<>((a, b) -> b - a);
		minPq = new PriorityQueue<>((a, b) -> a - b);
		for (int kk = 0; kk < k; kk++) {
			String[] tmp = br.readLine().split(" ");
			if (tmp[0].equals("I")) {
				int num = Integer.parseInt(tmp[1]); // maxPq와 minPq에 num 삽입
				maxPq.add(num);
				minPq.add(num);
				if (pqMap.containsKey(num)) { // pqMap에 이미 있는 num이면 기존 개수 + 1
					pqMap.put(num, pqMap.get(num) + 1);
				} else { // num이 처음이면 해당 num의 개수 1로 설정
					pqMap.put(num, 1);
				}
			} else if (tmp[1].equals("1")) { // 최댓값 삭제 연산
				while (!maxPq.isEmpty()) {
					int maxVal = maxPq.poll(); // maxPq에서 값을 하나 뽑음
					if (pqMap.containsKey(maxVal) && pqMap.get(maxVal) > 0) {
						// maxPq에서 뽑은 최댓값이 pqMap에 하나라도 존재하면 pqMap에서도 하나 줄이기
						pqMap.put(maxVal, pqMap.get(maxVal) - 1);
						break;
					}
					// 해당 max값이 최솟값을 뽑는 연산 때 미처 반영되지 않은 값이기 때문에 다시 반복
				}
			} else { // tmp[1] == "-1" -> 최솟값 삭제 연산
				while (!minPq.isEmpty()) {
					int minVal = minPq.poll();
					if (pqMap.containsKey(minVal) && pqMap.get(minVal) > 0) {
						pqMap.put(minVal, pqMap.get(minVal) - 1);
						break;
					}
				}
			}
		}
		int maxVal = MIN_INT;
		int minVal = MAX_INT;

		for (int num : pqMap.keySet()) { // pqMap의 키(저장된 정수)를 순회
			int numCnt = pqMap.get(num); // 저장된 num의 개수
			if (numCnt > 0) { // 개수가 0보다 크면 max, min값 갱신
				maxVal = Math.max(maxVal, num);
				minVal = Math.min(minVal, num);
			}
		}
		if (maxVal == MIN_INT && minVal == MAX_INT) // max, min값이 초기값에서 바뀌지 못함
			return "EMPTY";

		return maxVal + " " + minVal;
	}
}