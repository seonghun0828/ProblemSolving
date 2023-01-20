global n, arr


def inputs():
    global n, arr
    n = int(input())
    arr = list(map(int, input().split(' ')))


def compare(x, y):  # 두 수 중 0과 더 가까운 수를 리턴
    return x if abs(x) < abs(y) else y


def solution():
    inputs()

    left, right = 0, n - 1  # 양 끝에서 시작해 투 포인터로 범위 좁히기
    liquid_sum = arr[left] + arr[right]  # 양 끝의 합으로 시작
    ans = liquid_sum  # 초기 sum값을 ans로 초기화

    while left < right - 1:  # left와 right이 같아지기 직전까지 반복
        if liquid_sum == 0:  # 합이 0이면 더 탐색할 필요가 없음
            break
        elif liquid_sum < 0:  # 합이 음수면 left를 이동시켜 합을 더 크게
            left += 1
        else:  # 합이 양수면 right를 이동시켜 합을 더 작게
            right -= 1
        liquid_sum = arr[left] + arr[right]  # 합을 다시 구하기
        ans = compare(ans, liquid_sum)  # ans와 합 중 더 0에 가까운 걸로 갱신

    return ans


if __name__ == '__main__':
    print(solution())
