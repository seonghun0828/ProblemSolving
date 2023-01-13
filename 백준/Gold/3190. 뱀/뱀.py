from collections import deque

global n, k, arr, l, directions, dx, dy, snake, hx, hy, sec


def keep_gaming(d):
    global hx, hy, dx, dy, snake, sec
    sec += 1
    nx = hx + dx[d]
    ny = hy + dy[d]  # 다음 칸으로 이동

    if nx < 0 or nx >= n or ny < 0 or ny >= n or arr[nx][ny] == 1:
        return False  # 벽이나 자기 몸에 부딪히면 False 리턴

    snake.append((nx, ny))  # 다음 칸을 뱀 큐에 넣기
    hx, hy = nx, ny  # 머리를 다음 칸으로 갱신
    if arr[nx][ny] == 0:  # 다음 칸이 빈 칸이면
        tx, ty = snake.popleft()  # snake 큐에서 꼬리 칸을 pop 해서
        arr[tx][ty] = 0  # 꼬리 칸 없애기

    arr[nx][ny] = 1  # 다음 칸을 배열에서 뱀으로 갱신

    return True


def inputs():
    global n, k, arr, l, directions
    n = int(input())
    k = int(input())
    arr = [[0 for _ in range(n)] for _ in range(n)]
    for i in range(k):
        x, y = map(int, input().split(' '))
        arr[x - 1][y - 1] = 2
    # 0 : 빈 칸, 1 : 뱀, 2 : 사과

    l = int(input())
    directions = []
    for _ in range(l):
        x, c = map(str, input().split(' '))
        directions.append((int(x), c))


def solution():
    global dx, dy, snake, hx, hy, sec
    inputs()

    dx = [0, 1, 0, -1]
    dy = [1, 0, -1, 0]

    sec = 0  # 현재 경과한 시간 초
    d, d_idx = 0, 0  # dx, dy의 인덱스 d / directions의 인덱스 d_idx
    hx, hy = 0, 0  # 머리의 x, y 좌표
    snake = deque([])  # 뱀의 좌표들을 저장하는 큐
    snake.append((0, 0))  # 시작 좌표를 저장
    arr[0][0] = 1  # 시작 좌표를 1(뱀)으로 초기화

    while keep_gaming(d):
        if directions[d_idx][0] != sec:
            continue  # 현재 초가 방향이 바뀌는 시간이 아니면 continue
        if directions[d_idx][1] == 'L':  # L이면 왼쪽으로 90도 꺾기
            d = (d + 3) % 4
        else:  # D이면 오른쪽으로 90도 꺾기
            d = (d + 1) % 4
        d_idx += 1  # 방향이 바뀌었으니 directions의 인덱스 1 추가
        if d_idx == len(directions):
            d_idx -= 1  # d_idx가 배열을 초과하면 1 감소

    print(sec)


if __name__ == '__main__':
    solution()
