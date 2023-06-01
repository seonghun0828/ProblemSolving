import java.util.*;
class Solution {
    static class Node {
        int v, cost;
        public Node(int v, int cost) {
            this.v = v;
            this.cost = cost;
        }
    }
    static List<Node>[] list;
    public int solution(int n, int[][] costs) {
        int answer = 0;  // 누적 최소 비용
        parse(n, costs);  // costs 배열 파싱해서 list 값 채우기
        
        PriorityQueue<Node> pq = new PriorityQueue<>((a, b) -> a.cost - b.cost);
        boolean[] visited = new boolean[n];
        pq.add(new Node(costs[0][0], 0));  // costs 배열의 첫번째 노드 pq에 추가
        int cnt = 0;  // 현재 연결한 노드의 수
        
        while (!pq.isEmpty()) {
            Node curr = pq.poll();
            if (visited[curr.v])
                continue;
            
            visited[curr.v] = true;
            answer += curr.cost;
            cnt++;
            if (cnt == n) {
                break;
            }
            
            for (Node next : list[curr.v]) {
                pq.add(next);
            }
        }
        
        return answer;
    }
    public void parse(int n, int[][] costs) {
        list = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            list[i] = new ArrayList<>();
        }
        for (int[] cost : costs) {
            int a = cost[0];
            int b = cost[1];
            list[a].add(new Node(b, cost[2]));
            list[b].add(new Node(a, cost[2]));
        }
    }
}