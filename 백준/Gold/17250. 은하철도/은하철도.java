import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

class Main {
	static int N, M;
	static StringBuilder sb;
	static int[] p, planets;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();

		String[] input = br.readLine().split(" ");
		N = Integer.parseInt(input[0]);
		M = Integer.parseInt(input[1]);
		p = new int[N];
		planets = new int[N];

		for (int i = 0; i < N; i++) {
			p[i] = i; // make set
			planets[i] = Integer.parseInt(br.readLine()); // p와 같은 인덱스에 행성 수 저장
		}

		for (int j = 0; j < M; j++) {
			input = br.readLine().split(" ");
			int a = Integer.parseInt(input[0]) - 1;
			int b = Integer.parseInt(input[1]) - 1;
			union(a, b);
		}

		System.out.println(sb);

		br.close();
	}

	private static void union(int a, int b) {
		int parentA = find(a);
		int parentB = find(b);

		if (parentA != parentB) { // 두 값이 다르면 서로 다른 set이므로 합쳐야 함
			p[parentB] = parentA;
			planets[parentA] += planets[parentB]; // 두 행성 수를 더해 A에 저장
			planets[parentB] = planets[parentA]; // 더한 행성 수를 B에도 저장
		}
		sb.append(planets[parentA] + "\n");
	}

	private static int find(int a) {
		if (p[a] == a) {
			return a;
		}
		return p[a] = find(p[a]);
	}
}