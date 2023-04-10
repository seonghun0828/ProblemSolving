import java.util.*;
class Solution {
    static int N;
	static boolean finished;
	static Map<String, List<String>> map;
	static Set<String> visited;
	static List<String> ans;
    public String[] solution(String[][] tickets) {
        N = tickets.length + 1;
		finished = false;
		map = new HashMap<>();
		visited = new HashSet<>();
		ans = new LinkedList<>();

		for (String[] ticket : tickets) {
			if (map.containsKey(ticket[0])) {
				map.get(ticket[0]).add(ticket[1]);
			} else {
				List<String> list = new LinkedList<>();
				list.add(ticket[1]);
				map.put(ticket[0], list);
			}
		}

		for (String key : map.keySet()) {
			map.get(key).sort((a, b) -> a.compareTo(b));
		}

		ans.add("ICN");
		flight(1, "ICN");

		return ans.toArray(new String[N]);
    }
    
    private static void flight(int cnt, String curr) {
		if (cnt == N && !finished) {
			finished = true;
			return;
		}
		if (!map.containsKey(curr)) {
			return;
		}

		List<String> nextList = map.get(curr);
		for (int i = 0; i < nextList.size(); i++) {
			String next = nextList.get(i);
			if (next.length() == 1) {
				continue;
			}

			nextList.set(i, "X");
			ans.add(next);
			flight(cnt + 1, next);
			if (finished) {
				return;
			}
			nextList.set(i, next);
			ans.remove(ans.size() - 1);
		}
	}
}