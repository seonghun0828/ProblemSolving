global n, buildings


def solution():
    inputs()
    ans = 0
    stack = []

    for i in range(n - 1, -1, -1):  # n - 1번 째에서 역순으로 빌딩 순회
        curr = buildings[i]  # 현재 빌딩
        cnt = 1  # 현재 빌딩에서 볼 수 있는 옥상의 수 (본인 포함)
        while len(stack) > 0:  # stack이 빌 때까지 반복
            top = stack.pop()  # stack에서 꺼낸 top
            if curr > top[0]:  # curr가 top의 높이보다 크면 해당 빌딩 옥상 확인 가능
                ans += top[1]  # 정답에 top의 cnt 추가
                cnt += top[1]  # 현재 cnt에 top의 cnt 추가
            else:  # curr가 top 이하이면 더 이상 확인 불가
                stack.append(top)  # curr보다 높은 top 그대로 다시 stack에 추가
                break

        stack.append([curr, cnt])  # stack에 [현재 빌딩의 높이, cnt] 넣기

    return ans


def inputs():
    global n, buildings
    n = int(input())
    buildings = [int(input()) for _ in range(n)]


if __name__ == '__main__':
    print(solution())
