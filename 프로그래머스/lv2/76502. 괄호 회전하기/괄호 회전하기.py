bracket_pair = {
    ']' : '[',
    '}' : '{',
    ')' : '('
}
def solution(s):
    answer = 0
    
    for i in range(len(s)):
        turned_s = s[i:] + s[0:i]
        if is_right_bracket(turned_s):
            answer += 1
    
    return answer

def is_right_bracket(s):
    stack = []
    
    for curr in s:
        if curr in bracket_pair.values():
            stack.append(curr)
        elif len(stack) == 0:
            return False
        else:
            top = stack.pop()
            if bracket_pair[curr] != top:
                return False
            
    if len(stack) > 0:
        return False
    
    return True