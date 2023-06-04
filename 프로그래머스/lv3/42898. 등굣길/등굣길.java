import java.util.*;
class Solution {
    static int[][] maps;
    public int solution(int m, int n, int[][] puddles) {
        int answer = 0;
        maps = new int[n][m];
        setPuddlesInMaps(puddles);  // 웅덩이 좌표에 추가
        
        Queue<int[]> q = new LinkedList<>();
        q.add(new int[] {0, 0});
        maps[0][0] = 1;  // 시작 좌표의 경로 수 1로 설정
        int[] dx = {1, 0};
        int[] dy = {0, 1};
        
        while (!q.isEmpty()) {
            int[] curr = q.poll();
            for (int i = 0; i < 2; i++) {
                int nx = curr[0] + dx[i];
                int ny = curr[1] + dy[i];
                
                if (nx >= n || ny >= m || maps[nx][ny] == -1) {
                    continue;  // 범위를 벗어나거나 다음 좌표가 물에 잠김
                }
                if (maps[nx][ny] == 0) {  // 다음 좌표 첫 방문이면 큐에 추가
                    q.add(new int[] {nx, ny});
                }
                maps[nx][ny] += maps[curr[0]][curr[1]];  // 다음 좌표에 경로 수 추가
                if (maps[nx][ny] > 1000000007) {
                    maps[nx][ny] %= 1000000007;  // 좌표 계산 마다 나머지 연산
                }
            }
        }
        
        return maps[n - 1][m - 1];
    }
    public static void setPuddlesInMaps(int[][] puddles) {
        for (int[] puddle : puddles) {
            maps[puddle[1] - 1][puddle[0] - 1] = -1;
        }
    }
}