class Solution {
    static int zeroCnt;
    public int[] solution(String s) {
        int convertCnt = 0;
        zeroCnt = 0;
        
        while (!s.equals("1")) {
            s = convert(s);
            convertCnt++;
        }
        
        return new int[] {convertCnt, zeroCnt};
    }
    
    private String convert(String binary) { // 문제에서 설명한 이진 변환 함수
        String converted = "";
        for (char c : binary.toCharArray()) {
            if (c == '1') {
                converted += c;
            } else {
                zeroCnt++;
            }
        }
        return numToBinary(converted.length());
    }
    
    private String numToBinary(int num) { // 숫자를 이진수로 변환
        String binary = "";
        while (num > 0) {
            int share = (int)Math.floor(num / 2);
            int remainder = num % 2;
            num = share;
            binary = remainder + binary;
        }
        return binary;
    }
}