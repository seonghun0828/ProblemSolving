global n, arr


def inputs():
    global n, arr
    n = int(input())
    arr = list(map(int, input().split(' ')))


def solution():
    inputs()
    left, right = 0, n - 1  # 양 끝 두 인덱스
    closest = abs(arr[left] + arr[right])  # 현재 0에 가장 가까운 합의 절댓값 
    closest_left, closest_right = left, right  # closest일 때의 left, right

    while left < right - 1:  # left가 right보다 작을 때 계속해서 반복
        tmp_sum = arr[left] + arr[right]  # left, right 두 값의 합
        if tmp_sum == 0:  # 합이 0이면 갱신하고 반복문 탈출
            closest_left, closest_right = left, right
            break
        elif tmp_sum > 0:  # 합이 양수이면 sum 줄이기 위해 right--
            right -= 1
        else:  # 합이 음수이면 sum 늘리기 위해 left++
            left += 1

        tmp_sum = abs(arr[left] + arr[right])  # 바뀐 left, right 합의 절댓값
        if tmp_sum < closest:  # 새로운 합이 기존 closest보다 0에 더 가까우면
            closest = tmp_sum  # closest와 그 left, right 값 갱신
            closest_left, closest_right = left, right

    print(arr[closest_left], arr[closest_right])


if __name__ == '__main__':
    solution()
