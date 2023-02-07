import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main {
	static int N;
	static StringBuilder sb;
	static int[] inorder, postorder, rootIdxFromInorder;

	public static void main(String[] args) throws IOException {
		inputs();
		// 최대 10만개 print문 찍으면 느리기 때문에 sb에 모았다가 한번에 출력
		sb = new StringBuilder();

		searchPreorder(1, N, 1, N); // 처음에는 in, post 모두 시작 인덱스 : 1, 마지막 인덱스 : N
		System.out.println(sb);
	}

	// 현재 함수에서 inorder의 시작, 끝 인덱스, postOrder의 시작, 마지막 인덱스 매개 변수로 받아 옴
	private static void searchPreorder(int inSt, int inEd, int poSt, int poEd) throws IOException {
		if (inSt > inEd || poSt > poEd) // 인덱스가 범위를 벗어나면 재귀 멈추기
			return;

		int root = postorder[poEd]; // postOrder의 마지막 인덱스가 현재 서브 트리의 루트
		sb.append(root + " "); // 출력을 위해 sb에 추가

		int rootIdx = rootIdxFromInorder[root]; // 현재 서브 트리의 루트 번호에 해당하는 inorder의 인덱스
		int leftLength = rootIdx - inSt; // inorder 왼쪽 서브 트리의 길이
		// postOrder의 서브 트리는 inorder의 서브 트리와 길이가 같으므로 inorder 서브 트리의 길이를 이용해 post 서브 트리
		// 범위 구하기
		// 왼쪽 post 범위 : po시작 ~ po시작 + 길이 - 1 / 오른쪽 post 범위 : 왼쪽 post + 1 ~ poEd - 1
		searchPreorder(inSt, rootIdx - 1, poSt, poSt + leftLength - 1); // rootIdx 기준 왼쪽 서브 트리 재귀 탐색
		searchPreorder(rootIdx + 1, inEd, poSt + leftLength, poEd - 1); // rootIdx 기준 오른쪽 서브 트리 재귀 탐색
	}

	private static void inputs() throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		N = Integer.parseInt(br.readLine());
		inorder = new int[N + 1];
		postorder = new int[N + 1];
		rootIdxFromInorder = new int[N + 1];

		st = new StringTokenizer(br.readLine());
		for (int i = 1; i <= N; i++)
			inorder[i] = Integer.parseInt(st.nextToken());

		st = new StringTokenizer(br.readLine());
		for (int i = 1; i <= N; i++)
			postorder[i] = Integer.parseInt(st.nextToken());

		for (int i = 1; i <= N; i++) // 노드 번호를 넣었을 때 inorder에서 해당 노드 번호에 해당하는 idx 찾는 배열
			rootIdxFromInorder[inorder[i]] = i; // postorder로 루트 번호를 찾아 inorder에서 그 idx 찾는 목적
	}
}