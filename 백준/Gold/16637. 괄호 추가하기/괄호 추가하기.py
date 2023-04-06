global n, expression, ans


def solution():
    global ans
    inputs()

    ans = -1 << 31  # 음의 최댓값 고려해서 설정
    recur(0, 0, '+')  # 처음 sum이 0이기 때문에 op를 +로 해서 재귀 시작
    return ans


def recur(idx, sum, op):
    if idx == n:  # 수식을 전부 탐색했으면 sum과 ans 비교, 갱신
        global ans
        ans = max(ans, sum)
        return

    curr = expression[idx]  # 수식의 현재 인덱스 값
    if curr == '+' or curr == '-' or curr == '*':  # curr가 연산자이면
        recur(idx + 1, sum, curr)  # op만 curr로 바꾸고 다음 idx로 넘어감
    elif idx == n - 1:  # curr가 숫자인데 수식의 마지막이라면
        recur(idx + 1, calc(sum, op, curr), op)  # 괄호 추가할 수 없으므로 그냥 계산
    else:  # curr가 숫자인데 수식의 마지막이 아니면(괄호 추가 가능)
        recur(idx + 1, calc(sum, op, curr), op)  # 괄호 추가하지 않고 계산
        curr = calc(curr, expression[idx + 1], expression[idx + 2])  # 괄호 추가해 curr 값 갱신
        recur(idx + 3, calc(sum, op, curr), op)  # 괄호를 추가해 계산하고 괄호 이후의 idx로 넘어감


def calc(sum, op, curr):
    if op == '+':
        return int(sum) + int(curr)
    elif op == '-':
        return int(sum) - int(curr)
    else:
        return int(sum) * int(curr)


def inputs():
    global n, expression
    n = int(input())
    expression = input()


if __name__ == '__main__':
    print(solution())
