import sys
from collections import deque

global n, m, arr, virus_place, ans, dx, dy, selected, empty


def bfs(empty_cnt):
    global arr, ans
    copied = []  # 함수 끝날 때 arr 초기화 용 copy 함수 만들기
    for i in range(n):
        copied.append([])
        for j in range(n):
            copied[i].append(arr[i][j])

    q = deque([])
    max_time = 0  # 해당 탐색에서 가장 큰 시간
    for j in range(len(virus_place)):
        if selected[j]:  # 조합에서 선택된 인덱스
            a, b = virus_place[j]  # 바이러스의 좌표
            arr[a][b] = 0  # 배열에 바이러스 0으로 초기화
            q.append((a, b))  # 큐에 넣기
            empty_cnt -= 1  # 바이러스 놓을 때마다 빈 공간 -1
    while len(q) > 0:
        x, y = q.popleft()
        for i in range(4):
            nx = x + dx[i]
            ny = y + dy[i]
            if nx < 0 or nx >= n or ny < 0 or ny >= n:
                continue  # 범위에 벗어나면 pass
            if arr[nx][ny] >= -1:
                continue  # 방문했거나(0 이상) 벽이면(-1) pass
            arr[nx][ny] = arr[x][y] + 1  # 다음 좌표 시간 +1
            q.append((nx, ny))  # 다음 좌표 q에 넣기
            max_time = max(max_time, arr[nx][ny])  # 최고 시간 갱신
            empty_cnt -= 1  # 빈 공간 수 감소

    if empty_cnt == 0:  # 탐색 끝나고 빈 공간이 없으면
        ans = min(ans, max_time)  # 정답 갱신
    arr = copied  # arr 초기화


def combination(idx, cnt):
    if cnt == m:  # 바이러스 수만큼 조합 끝났으면
        bfs(empty)  # 탐색 시작
        return
    for i in range(idx, len(virus_place)):  # 조합 구하기
        selected[i] = True
        combination(i + 1, cnt + 1)
        selected[i] = False


def inputs():
    global n, m, arr, virus_place, empty
    n, m = map(int, input().split(' '))
    virus_place = []  # 바이러스 놓기가 가능한 빈 공간 좌표 리스트
    empty = 0  # 빈 공간의 수
    arr = []  # -1: 벽, -2: 빈 공간, 바이러스 가능 공간 포함
    for i in range(n):
        arr.append([])
        line = list(map(int, input().split(' ')))
        for j in range(n):
            arr[i].append(line[j] - 2)
            if line[j] == 1:  # 벽이면 pass
                continue
            empty += 1  # 빈 공간 카운트
            if arr[i][j] == 0:  # 바이러스 가능 공간
                virus_place.append((i, j))
                arr[i][j] -= 2


def solution():
    global ans, dx, dy, selected
    inputs()

    ans = sys.maxsize
    dx = [1, -1, 0, 0]
    dy = [0, 0, 1, -1]
    # 바이러스가 가능한 조합들을 idx로 저장해 놓는 배열
    selected = [False] * len(virus_place)
    combination(0, 0)
    if ans == sys.maxsize:  # 모든 칸에 바이러스 퍼뜨리기가 불가능 
        return -1
    return ans


if __name__ == '__main__':
    print(solution())
