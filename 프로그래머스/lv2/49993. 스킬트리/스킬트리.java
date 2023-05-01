import java.util.*;
class Solution {
    public int solution(String skill, String[] skill_trees) {
        int answer = 0;
        
        char[] skills = skill.toCharArray();
        Set<Character> skillSet = new HashSet<>();
        
        for (char s : skills) {  // skill의 원소를 set에 넣기
            skillSet.add(s);
        }
        
        for (String tree : skill_trees) {  // 유저의 스킬 트리
            List<Character> skippedTree = new ArrayList<>();  // set에 있는 원소만 담는 스킵된 트리
            for (char c : tree.toCharArray()) {
                if (skillSet.contains(c)) {
                    skippedTree.add(c);
                }
            }
            boolean isPossible = true;  // 스킬 트리가 가능한지
            for (int i = 0; i < skippedTree.size(); i++) {  // 스킵된 트리 만큼 순회
                if (skippedTree.get(i) != skills[i]) {  // 스킵된 트리와 skills의 i번째가 다르면
                    isPossible = false;  // 불가능한 스킬 트리
                    break;
                }
            }
            if (isPossible) {  // 현재 유저의 스킬 트리가 가능
                answer++;
            }
        }
        
        return answer;
    }
}