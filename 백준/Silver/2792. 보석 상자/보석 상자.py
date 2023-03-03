import sys
input = sys.stdin.readline
global n, m, jewelry


def solution():
    inputs()
    max_jewelry = max(jewelry)
    st, ed = 1, max_jewelry + 1
    ans = max_jewelry
    while st <= ed:
        mid = int((st + ed) / 2)  # mid 값을 질투심으로 이분탐색을 통해 학생 수를 구함
        cnt = get_student_cnt(mid)
        if cnt > n:  # 학생 수가 n보다 크면 학생 수를 줄이기 위해 mid 값 올리기
            st = mid + 1
        else:  # 학생 수가 n 이하면 학생 수를 늘리기 위해 mid 값 내리기
            ed = mid - 1
            ans = mid  # ans 계속 갱신하기
    return ans


def get_student_cnt(grid):
    cnt = 0
    for jewel in jewelry:
        # 보석을 질투심으로 나눈 나머지가 0이면 몫이 곧 그 보석을 할당 받은 학생 수
        # 나눠 떨어지지 않으면 몫 + 1이 그 보석을 할당 받은 학생 수
        cnt += jewel // grid if jewel % grid == 0 else jewel // grid + 1
    return cnt


def inputs():
    global n, m, jewelry
    n, m = map(int, input().split(' '))
    jewelry = sorted([int(input()) for _ in range(m)])


if __name__ == '__main__':
    print(solution())
