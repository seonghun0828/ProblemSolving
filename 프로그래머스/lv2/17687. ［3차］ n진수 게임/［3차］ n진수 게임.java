import java.util.*;
class Solution {
    static Map<Integer, Character> map;
    public String solution(int n, int t, int m, int p) {
        String answer = "";
        map = new HashMap<>();  // 10~15 숫자에 A~F 매칭해주는 map
        map.put(10, 'A');
        map.put(11, 'B');
        map.put(12, 'C');
        map.put(13, 'D');
        map.put(14, 'E');
        map.put(15, 'F');

				//t가 최대 1천, m이 100이므로 최대 100,000번 탐색. 브루트포스 가능
        
        int num = 0;  // 현재 숫자
        int cnt = 0;  // 현재 차례
        p--; // 순서를 0부터 시작하기 위해 1 감소
        
        while (t > 0) {  // 구할 숫자 t를 줄여나가며 반복. 0이되면 탈출.
            String str = getJinbupFrom(num, n);  // 현재 숫자 num을 n진법으로 바꾼 str
            for (int i = 0; i < str.length(); i++) {  // str 길이만큼 반복
                if (cnt % m == p) {  // 현재 차례를 사람 수로 나눴을 때 내 순서이면
                    answer += str.charAt(i);  // 정답에 현재 char를 추가
                    t--;  // t 감소
                }
                if (t == 0) {  // t가 0이 되면 더 구하면 안되므로 탈출
                    break;
                }
                cnt++;  // 다음 차례
            }
            num++;  // 다음 숫자
        }
        
        return answer;
    }
    
    private String getJinbupFrom(int num, int n) { // 숫자 num을 n진수 문자열로 반환
        if (num == 0) {
            return "0";
        }
        String str = "";
        while (num > 0) {
            int share = (int) (num / n);
            int remainder = num % n;
            if (n > 10 && remainder >= 10) {  // 11진수 이상이고 나머지가 10 이상이면
                str = map.get(remainder) + str;  // 나머지를 A~F중 하나로 바꿔서 str에 추가
            } else {
                str = remainder + str;  // 나머지 숫자를 str에 추가
            }
            num = share;
        }
    
        return str;
    }
}
