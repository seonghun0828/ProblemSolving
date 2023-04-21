def solution(n):
    ans = 0
    # 끝(n)에서부터 시작
    while n > 0:  # n이 자연수이면 반복
        if n % 2 == 0:  # 짝수면 순간이동
            n = n / 2
        else:  # 홀수면 한 칸 앞으로 점프
            n -= 1
            ans += 1

    return ans