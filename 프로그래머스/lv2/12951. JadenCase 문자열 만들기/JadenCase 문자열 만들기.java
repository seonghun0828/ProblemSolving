import java.util.*;
class Solution {
    public String solution(String s) {
        String ans = "";
        char prev = ' ';
        
        for (char curr : s.toCharArray()) {
            if (prev == ' ') {
                ans += (curr + "").toUpperCase();
            } else {
                ans += (curr + "").toLowerCase();
            }
            prev = curr;
        }
        
        return ans;
    }
}