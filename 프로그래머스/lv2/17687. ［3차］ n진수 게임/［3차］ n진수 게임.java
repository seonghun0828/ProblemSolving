import java.util.*;
class Solution {
    static Map<Integer, Character> map = new HashMap<>();
    public String solution(int n, int t, int m, int p) {
        String answer = "";
        map.put(10, 'A');
        map.put(11, 'B');
        map.put(12, 'C');
        map.put(13, 'D');
        map.put(14, 'E');
        map.put(15, 'F');
        
        int num = 0;
        int cnt = 0;
        
        while (t > 0) {
            String str = getJinbupFrom(num, n);
            for (int i = 0; i < str.length(); i++) {
                if (cnt % m == p - 1) {
                    answer += str.charAt(i);
                    t--;
                }
                if (t <= 0) {
                    break;
                }
                cnt++;
            }
            num++;
        }
        
        
        return answer;
    }
    
    private String getJinbupFrom(int num, int n) {
        if (num == 0) {
            return "0";
        }
        String str = "";
        while (num > 0) {
            int share = (int) (num / n);
            int remainder = num % n;
            if (n > 10 && remainder >= 10) {
                str = map.get(remainder) + str;
            } else {
                str = remainder + str;   
            }
            num = share;
        }
    
        return str;
    }
}