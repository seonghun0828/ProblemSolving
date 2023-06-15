global n, arr


def solution():
    inputs()
    max_val = 1000 * 1000
    ans = max_val

    for i in range(3):  # 어디서 시작하는지 기억하기 위해 3개 색에서 시작
        dp = [[max_val, max_val, max_val] for _ in range(n)]
        dp[0][i] = arr[0][i]  # 0번 집은 i번 색에서 시작

        for j in range(1, n):
            # j번째 집이 0번 색이면, 0번 색 칠하는 비용 + j-1번째 집의 1, 2번 색 중 더 낮은 비용
            dp[j][0] = arr[j][0] + min(dp[j - 1][1], dp[j - 1][2])
            dp[j][1] = arr[j][1] + min(dp[j - 1][0], dp[j - 1][2])
            dp[j][2] = arr[j][2] + min(dp[j - 1][0], dp[j - 1][1])

        for j in range(3):  # 마지막 집의 색을 확인하기 위해 3개 색 순회
            if i == j:  # 마지막 집의 색(j)이 0번 집의 색(i)과 같으면 pass
                continue
            ans = min(dp[n - 1][j], ans)  # 0번 집 색과 다른 마지막 집의 최종 비용 갱신

    return ans


def inputs():
    global n, arr
    n = int(input())
    arr = []
    for _ in range(n):
        arr.append(list(map(int, input().split(' '))))


if __name__ == '__main__':
    print(solution())