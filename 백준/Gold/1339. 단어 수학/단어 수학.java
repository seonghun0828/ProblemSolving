import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

class Main {
	static int N, ans, alphaCnt;
	static int[] sel;
	static String[] words;
	static List<Character> list;

	public static void main(String[] args) throws IOException {
		inputs();

		ans = 0;
		sel = new int[alphaCnt]; // 순열 저장할 배열
		permu(0, 0); // 알파벳에 할당할 숫자들의 순열을 구해 계산하기

		System.out.println(ans);
	}

	private static void permu(int cnt, int visited) {
		if (cnt == alphaCnt) { // cnt가 알파벳 갯수가 되면 ans 갱신
			ans = Math.max(ans, getSumFromSel());
			return;
		}

		for (int i = 0; i < 10; i++) {
			if ((visited & 1 << i) > 0)
				continue;

			sel[cnt] = i; // cnt번째 순열에 i 할당
			permu(cnt + 1, visited | 1 << i); // cnt + 1, i 방문처리하고 재귀
			sel[cnt] = 0; // cnt번째 순열에 할당했던 수 0으로 다시 초기화
		}
	}

	private static int getSumFromSel() {
		int sum = 0;

		// 현재 순열에서 총합 구하기
		for (String word : words) {
			int num = 0;
			for (int i = 0; i < word.length(); i++) {
				num *= 10;
				char c = word.charAt(i);
				num += sel[list.indexOf(c)];
			}
			sum += num;
		}
		return sum;
	}

	private static void inputs() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		words = new String[N];
		list = new ArrayList<>();
		alphaCnt = 0;

		for (int i = 0; i < N; i++) {
			String input = br.readLine();
			words[i] = input;
			for (char c : input.toCharArray()) {
				if (!list.contains(c)) {
					list.add(c);
					alphaCnt++;
				}
			}
		}
		br.close();
	}
}