def is_ppap(string):
    stack = [string[0]]  # 문자열 첫 번째 값 스택에 넣고 시작 
    for i in range(1, len(string)):  # 두 번째 값부터 탐색
        top = stack[-1]
        if top == "A":  # 스택의 top에 A가 있을 때
            if string[i] != "P":  # 현재 i 값이 P가 아니면
                return False  # AA 형태가 되기 때문에 불가능
            for j in range(3):  # A 포함해 3번 POP
                if len(stack) == 0:
                    return False  # 그 과정에서 꺼낼게 없으면 불가능
                stack.pop()
        stack.append(string[i])  # 현재 i 값을 스택에 추가
    if len(stack) == 1 and stack[-1] == "P":
        return True  # 문자열 순회 후 스택에 P 하나만 남았으면 가능
    return False  # 그외 경우는 불가능


def solution():
    string = input()
    print("PPAP" if is_ppap(string) else "NP")


if __name__ == '__main__':
    solution()
    