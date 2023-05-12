class Solution {
    static int ans;
    public int solution(String s) {
        ans = s.length();
        
        for (int i = s.length() / 2; i > 0; i--) {
            cut(s, i);
        }
        return ans;
    }
    public boolean cut(String s, int len) {
        String prev = s.substring(0, len);
        int cnt = 1;
        int totalLen = len;
        boolean isChanged = false;
        
        for (int i = len; i < s.length(); i += len) {
            if (i + len > s.length()) {
                totalLen += s.length() - i;
                break;
            }
            String curr = s.substring(i, i + len);
            if (curr.equals(prev)) {
                cnt++;
                isChanged = true;
            }
            else {
                totalLen += cnt > 1 ? (cnt + "").length() : 0;
                totalLen += curr.length();
                prev = curr;
                cnt = 1;
            }
        }
        if (cnt > 1) {
            totalLen += cnt > 1 ? (cnt + "").length() : 0;
        }
        ans = Math.min(ans, totalLen);
        return isChanged;
    }
}