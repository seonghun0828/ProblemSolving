import sys
from collections import deque


def makeFresh():
    temp = [[0] * C for _ in range(R)]
    for i in range(R):
        for j in range(C):
            if room[i][j] < 0:
                continue
            if j == 0 and i < machine[0][0]:
                temp[i + 1][j] = room[i][j]
            elif j == C - 1 and i <= machine[0][0] and i != 0:
                temp[i - 1][j] = room[i][j]
            elif (i == 0 and j != 0) or (i == R - 1 and j != 0):
                temp[i][j - 1] = room[i][j]
            elif j == 0 and i > machine[1][0]:
                temp[i - 1][j] = room[i][j]
            elif j == C - 1 and i >= machine[1][0] and i != R - 1:
                temp[i + 1][j] = room[i][j]
            elif (i == machine[0][0]) or (i == machine[1][0]):
                if j != 0 and j != C - 1:
                    temp[i][j + 1] = room[i][j]
            else:
                temp[i][j] = room[i][j]
    for a, b in machine:
        temp[a][b] = -1
    return temp


def spread():
    q = deque()
    temp = [[0] * C for _ in range(R)]
    for a, b in machine:
        temp[a][b] = -1
    for i in range(R):
        for j in range(C):
            if room[i][j] > 0:
                q.append((i, j))
    while q:
        x, y = q.popleft()
        giveNum = room[x][y] // 5
        cnt = 0
        for i in range(4):
            nx = x + dx[i]
            ny = y + dy[i]
            if 0 <= nx < R and 0 <= ny < C and room[nx][ny] != -1:
                temp[nx][ny] += giveNum
                cnt += 1
        temp[x][y] += room[x][y] - (cnt * giveNum)
    return temp


input = sys.stdin.readline
R, C, T = map(int, input().split())
room = []
machine = []
for i in range(R):
    row = list(map(int, input().split()))
    room.append(row)
    for j in range(C):
        if row[j] == -1:
            machine.append((i, j))
dx = [1, -1, 0, 0]
dy = [0, 0, 1, -1]
sec = 0
while sec != T:
    room = spread()
    room = makeFresh()
    sec += 1
summ = 0
for i in range(R):
    for j in range(C):
        summ += room[i][j]
print(summ + 2)