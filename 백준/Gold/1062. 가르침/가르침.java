import java.io.IOException;
import java.util.Scanner;

class Main {
	static int N, K, alphaLen, ans;
	static boolean[] alphabet;
	static String[] words;

	public static void main(String[] args) throws IOException {
		inputs();

		System.out.println(solution());
	}

	private static int solution() {
		ans = 0;

		if (K < 5) { // 기본 문자인 a n t i c 을 배울 수 없으므로 0 반환
			return 0;
		}

		alphaLen = 26;
		alphabet = new boolean[alphaLen];
		char[] basicAlpha = new char[] { 'a', 'n', 't', 'i', 'c' };
		for (char basic : basicAlpha) { // 기본 문자 alphabet 배열에 반영
			alphabet[basic - 'a'] = true;
		}

		recur(0, 5); // idx 0, cnt 5에서 시작해 재귀

		return ans;
	}

	private static void recur(int idx, int cnt) {
		if (cnt == K) { // 가르친 글자 수가 K개가 되면 재귀 종료
			int teachableWordCnt = getTeachableWordCnt(); // 가르친 글자로 읽을 수 있는 단어 숫자
			ans = Math.max(ans, teachableWordCnt);
			return;
		}

		for (int i = idx; i < alphaLen; i++) { // 시작 idx부터 26까지 반복
			if (alphabet[i]) { // 이미 가르친 글자
				continue;
			}

			alphabet[i] = true; // i번째 글자를 가르침
			recur(i + 1, cnt + 1); // i번째 글자 가르치고 재귀
			alphabet[i] = false; // i번째 글자 가르친거 취소
		}
	}

	private static int getTeachableWordCnt() {
		int cnt = 0;

		for (String ele : words) {
			boolean isReadable = true;
			char[] word = ele.toCharArray();
			for (char c : word) {
				if (!alphabet[c - 'a']) { // 단어의 한 글자가 알파벳에 없음
					isReadable = false;
					break;
				}
			}

			if (isReadable) { // 단어의 모든 글자를 가르쳐서 읽을 수 있음
				cnt++;
			}
		}

		return cnt;
	}

	private static void inputs() throws IOException {
		Scanner sc = new Scanner(System.in);

		N = sc.nextInt();
		K = sc.nextInt();
		words = new String[N];
		for (int i = 0; i < N; i++) {
			words[i] = sc.next();
			// 모든 단어에 포함되어 있는 prefix anta 와 postfix tica를 제거
			words[i] = words[i].substring(4, words[i].length() - 4);
		}

		sc.close();
	}
}