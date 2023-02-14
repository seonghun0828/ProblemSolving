global N, M, connection


def solution():
    inputs()

    dp = [0] * (M + 1)
    ans = 0
    for i in range(1, M + 1):  # A 위치 1 ~ 500 순회
        if connection[i] == 0:
            continue
        for j in range(1, i):  # i 위치 기준 1 ~ (i - 1) 순회
            if connection[j] == 0 or connection[i] < connection[j]:
                continue  # 이전 j 전깃줄이 현재 i 전깃줄과 교차
            dp[i] = max(dp[i], dp[j])  # 교차하지 않는 j 중 가장 큰 값 dp[i]에 저장
        dp[i] += 1  # 가장 큰 dp[j] 값이 저장되어 있는 dp[i] 값에 i 전깃줄 추가
        ans = max(ans, dp[i])
    print(N - ans)  # 전체 전깃줄 - 교차하지 않는 가장 많은 전깃줄 수 = 가장 적게 제거하는 전깃줄 수


def inputs():
    global N, M, connection
    N = int(input())
    M = 500
    connection = [0] * (M + 1)
    for _ in range(N):
        a, b = map(int, input().split(' '))
        connection[a] = b


if __name__ == '__main__':
    solution()
