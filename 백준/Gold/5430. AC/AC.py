def solution(p, n, arr):
    left = 0  # 배열의 가장 왼쪽 끝 인덱스
    right = n  # 배열의 가장 오른쪽 끝 인덱스
    is_reversed = False  # 배열이 뒤집혔는지 여부를 나타내는 변수

    for op in p:
        if op == 'R':  # R이면 reversed 변수 토글
            is_reversed = not is_reversed
            continue

        if is_reversed:  # D인데 거꾸로면
            right -= 1  # 오른쪽 인덱스 -1
        else:  # D인데 정상 순서면
            left += 1  # 왼쪽 인덱스 +1

        if left > right:  # 배열이 비어 있는데 삭제 했으면
            return 'error'  # 에러 출력

    # left~right 인덱스까지 배열을 자르고 거꾸로 돼 있으면 배열 뒤집기
    arr = arr[left:right][::-1] if is_reversed else arr[left:right]
    return '[' + ','.join(arr) + ']'  # 형식 맞춰 출력


def inputs():
    t = int(input())
    for _ in range(t):
        p = input()
        n = int(input())
        arr = input()[1:-1].split(',')
        print(solution(p, n, arr))


if __name__ == '__main__':
    inputs()
