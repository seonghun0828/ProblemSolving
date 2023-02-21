global n, arr, lis


def solution():
    global lis
    inputs()

    lis = [arr[0]]  # Longest Increasing Subsequence.
    for i in range(1, n):  # 첫번째 값은 lis에 포함. 두번째 값부터 순회
        port = arr[i]
        if port > lis[-1]:  # 이번 포트가 lis 최대값보다 크면
            lis.append(port)
            continue
        idx = find_idx(port)  # 이번 포트가 들어갈 idx를 찾기
        lis[idx] = port  # 찾은 idx 자리에 port로 대체
    print(len(lis))  # LIS의 길이가 곧 겹치지 않는 최대 연결 개수


def find_idx(port):
    st = 0
    ed = len(lis) - 1
    while st <= ed:  # 이분 탐색 (lower bound)
        mid = int((st + ed) / 2)
        if lis[mid] >= port:
            ed = mid - 1
        else:
            st = mid + 1
    return st


def inputs():
    global n, arr
    n = int(input())
    arr = list(map(int, input().split(' ')))


if __name__ == '__main__':
    solution()
