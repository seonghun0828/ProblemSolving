import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

class Main {
	static class Node {
		int x, y, z;

		Node(int x, int y, int z) {
			this.x = x;
			this.y = y;
			this.z = z;
		}
	}

	static int L, R, C;
	static int[] dz = { 0, 0, 0, 0, 1, -1 };
	static int[] dx = { 0, 0, 1, -1, 0, 0 };
	static int[] dy = { 1, -1, 0, 0, 0, 0 };
	static char[][][] building;
	static int[][][] visited;
	static Node start;
	static BufferedReader br;

	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		while (true) {
			inputs();
			if (L == 0 && R == 0 && C == 0) {
				break;
			}

			int result = bfs();
			if (result == -1) { // -1이면 탈출 불가
				System.out.println("Trapped!");
			} else { // 탈출하는데 걸린 최단 시간 출력
				System.out.println("Escaped in " + result + " minute(s).");
			}
		}
		br.close();
	}

	private static int bfs() {
		Queue<Node> q = new LinkedList<>();
		q.add(start);
		visited[start.z][start.x][start.y] = 0;

		while (!q.isEmpty()) {
			Node curr = q.poll();
			for (int i = 0; i < 6; i++) {
				int nz = curr.z + dz[i];
				int nx = curr.x + dx[i];
				int ny = curr.y + dy[i];

				if (nz < 0 || nz >= L || nx < 0 || nx >= R || ny < 0 || ny >= C)
					continue;

				if (visited[nz][nx][ny] > 0 || building[nz][nx][ny] == '#') // 이미 방문했거나 막혀있음
					continue;

				if (building[nz][nx][ny] == 'E') { // 출구에 도착했으면 현재까지 시간 + 1 반환
					return visited[curr.z][curr.x][curr.y] + 1;
				}

				q.add(new Node(nx, ny, nz));
				visited[nz][nx][ny] = visited[curr.z][curr.x][curr.y] + 1;
			}
		}
		return -1; // 탈출이 불가능하므로 -1 반환
	}

	private static void inputs() throws IOException {
		String[] input = br.readLine().split(" ");

		L = Integer.parseInt(input[0]);
		R = Integer.parseInt(input[1]);
		C = Integer.parseInt(input[2]);

		if (L == 0 && R == 0 && C == 0) {
			return;
		}

		building = new char[L][R][C];
		visited = new int[L][R][C];

		for (int i = 0; i < L; i++) {
			for (int j = 0; j < R; j++) {
				char[] tmp = br.readLine().toCharArray();
				for (int k = 0; k < C; k++) {
					if (tmp[k] == 'S') { // 시작 좌표 저장
						start = new Node(j, k, i);
					}
					building[i][j] = tmp;
				}
			}
			br.readLine();
		}
	}
}