global n, k, scores


def solution():
    inputs()
    max_val = 2000001
    st, ed, ans = 0, max_val, max_val  # ed는 가능한 총 점수 (10만 * 20점) + 1
    while st <= ed:
        mid = int((st + ed) / 2)
        if get_group_cnt(mid) >= k:  # mid 점수로 그룹을 나눴을 때 갯수와 k 비교
            st = mid + 1  # k 이상이면 그룹 수를 줄이기 위해 mid 키우기
            ans = mid  # 매번 정교해지는 mid를 ans로 갱신
        else:
            ed = mid - 1  # k 미만이면 그룹 수를 늘리기 위해 mid 줄이기
    return ans


def get_group_cnt(mid):
    tmp, cnt = 0, 0
    for score in scores:
        tmp += score  # 점수를 더해가며 임시 합을 키우기
        if tmp >= mid:  # 임시 합이 mid 이상이 되면
            tmp = 0  # 임시 합을 0으로 초기화하고
            cnt += 1  # cnt + 1
    return cnt


def inputs():
    global n, k, scores
    n, k = map(int, input().split(' '))
    scores = list(map(int, input().split(' ')))


if __name__ == '__main__':
    print(solution())
