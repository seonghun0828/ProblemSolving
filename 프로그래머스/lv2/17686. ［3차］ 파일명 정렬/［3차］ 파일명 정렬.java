import java.util.*;
class Solution {
    static class File {
        String name;  // 파일 전체 이름
        String head;  // 파일의 head 부분
        int number;  // 파일의 number 부분
        int order;  // 파일의 원래 순서
        File(String name, String head, int number, int order) {
            this.name = name;
            this.head = head;
            this.number = number;
            this.order = order;
        }
    }
    public String[] solution(String[] files) {
        int N = files.length;
        String[] answer = new String[N];
        File[] fileArr = new File[N];
        
        for (int i = 0; i < N; i++) {
            String fileName = files[i];
            File curr = parse(fileName, i);  // fileName을 파싱해서 curr File로 만듦
            fileArr[i] = curr;
        }
        
        Arrays.sort(fileArr, (f1, f2) -> {  // fileArr 기준에 맞게 정렬
            if (f1.head.equals(f2.head)) {
                if (f1.number == f2.number) {
                    return f1.order - f2.order;  // 3순위 : 원래 순서에 맞게 정렬
                }
                return f1.number - f2.number;  // 2순위 : number에 맞게 정렬
            }
            return f1.head.compareTo(f2.head);  // 1순위 : head에 맞게 정렬
        });
        
        for (int i = 0; i < N; i++) {
            File curr = fileArr[i];  // 정렬된 fileArr 순회
            answer[i] = fileArr[i].name;  // fileArr의 name을 answer에 할당
        }
        
        return answer;
    }
    private File parse(String fileName, int order) {  // fileName과 원래 순서 받아옴
        char[] tmp = fileName.toCharArray();
        boolean headParseOver = false;  // 현재 head 파싱이 끝났는지
        String head = "";  // head 부분
        String number = "";  // number 부분
        
        for (int i = 0; i < tmp.length; i++) {
            char curr = tmp[i];  // fileName의 현재 부분
            // fileName의 다음 부분(숫자 형태). 만약 i가 마지막 인덱스면 -1로 설정
            int next = (i == tmp.length - 1) ? -1 : tmp[i + 1] - '0';
            if (headParseOver) {  // head 끝나고 number 파싱 중
                number += curr;
                if (next < 0 || next > 9) {  // next가 number가 아니면
                    break;  // 전체 파싱 끝. tail은 볼 필요 없음.
                }
            } else {  // 처음 ~ head 파싱 중
                head += curr;
                if (next >= 0 && next <= 9) {  // next가 number이면
                    headParseOver = true;  // head 파싱 끝
                }
            }
        }
        System.out.println(fileName + " " + head.toUpperCase() + " " + Integer.parseInt(number));
        // head는 대소문자 구분 없으므로 대문자로 통일. number는 문자열을 int로 변환
        return new File(fileName, head.toUpperCase(), Integer.parseInt(number), order);
    }
}