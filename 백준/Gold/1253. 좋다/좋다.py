global n, arr


def solution():
    inputs()
    arr.sort()  # 순서대로 탐색하기 위해 오름차순 정렬
    cnt = 0

    for i in range(n):  # 모든 인덱스 투포인터로 순회
        num = arr[i]  # num은 음수도 가능!!!
        st = 0  # 투포인터 중 왼쪽 인덱스
        ed = n - 1  # 투포인터 중 오른쪽 인덱스
        while st < ed:
            if st == i:  # 왼쪽 인덱스가 i와 같다면
                st += 1
                continue
            if ed == i:  # 오른쪽 인덱스가 i와 같다면
                ed -= 1
                continue
            tmp = arr[st] + arr[ed]
            if tmp == num:
                cnt += 1
                break
            elif tmp < num:
                st += 1
            else:
                ed -= 1
    print(cnt)


def inputs():
    global n, arr
    n = int(input())
    arr = list(map(int, input().split(' ')))


if __name__ == '__main__':
    solution()
