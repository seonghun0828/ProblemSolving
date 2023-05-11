global d, n, m, stone_dists


def solution():
    inputs()

    lo = 0
    hi = d  # n, m이 0인 경우에는 최소 거리가 d가 됨
    ans = 0
    while lo <= hi:
        mid = int((lo + hi) / 2)
        if get_step_cnt_by(mid) >= n - m:  # 밟은 돌섬의 수가 n - m 이상
            lo = mid + 1
            ans = mid
        else:  # 밟은 돌섬의 수가 n - m 미만
            hi = mid - 1
    return ans


def get_step_cnt_by(min_dist):  # min_dist 이상 도약할 수 있는 작은 돌섬의 수 반환
    prev = 0
    cnt = 0
    for curr in stone_dists:
        diff = curr - prev  # 현재 돌섬 위치와 이전 돌섬의 위치 차이
        if diff >= min_dist:  # 차이가 min_dist 이상일 때만 카운팅
            prev = curr
            cnt += 1

    return cnt


def inputs():
    global d, n, m, stone_dists
    d, n, m = map(int, input().split(' '))
    stone_dists = []
    for i in range(n):
        stone_dists.append(int(input()))
    stone_dists.sort()


if __name__ == '__main__':
    print(solution())
