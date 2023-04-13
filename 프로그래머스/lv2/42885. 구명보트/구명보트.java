import java.util.*;
class Solution {
    public int solution(int[] people, int limit) {
        int answer = 0;
        
        int left = 0;
        int right = people.length - 1;
        Arrays.sort(people);  // 투포인터 사용 위해 오름차순 정렬
        
        while (left <= right) {
            int sum = people[left] + people[right];
            if (sum > limit) {  // 두 값의 합이 limit 초과하면 합을 줄여야함
                answer++;
                right--;  // 해당하는 right 인덱스의 사람은 홀로 탈출
            } else {  // 두 값의 합이 limit 이하면 최적의 경우이므로 둘이 탈출하고 다음 사람
                answer++;
                left++;
                right--;
            }
        }
        
        return answer;
    }
}