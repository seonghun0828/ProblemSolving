import sys

def fall_blocks():
  for i in range(10, -1, -1):
    for j in range(6):
      k = i + 1
      if field[i][j] == '.' or field[k][j] != '.':
        continue
      while k < 12 and field[i][j] != '.' and field[k][j] == '.':
        k += 1
      k -= 1
      field[k][j] = field[i][j]
      field[i][j] = '.'

def dfs(a, b, visited, char, cnt, bomb_targets):
  visited[a][b] = True
  bomb_targets.append((a, b))
  for k in range(4):
    x = a + dx[k]
    y = b + dy[k]
    if 0 <= x < 12 and 0 <= y < 6 and not visited[x][y] and field[x][y] == char:
      result = dfs(x, y, visited, char, cnt + 1, bomb_targets)
      cnt = result[0]
      visited = result[1]
      bomb_targets = result[2]
  return cnt, visited, bomb_targets

def search_if_continue():
  visited = [[False] * 6 for _ in range(12)]
  hasBomb = False
  for i in range(12):
    for j in range(6):
      if field[i][j] == '.':
        continue
      if visited[i][j]:
        continue
      char = field[i][j]
      result = dfs(i, j, visited, char, 1, [])
      same_color_cnt = result[0]
      visited = result[1]
      if same_color_cnt < 4:
        continue
      bomb_targets = result[2]
      for x, y in bomb_targets:
        field[x][y] = '.'
        hasBomb = True
  if hasBomb:
    fall_blocks()
    return 1
  return 0

input = sys.stdin.readline
dx = [1, -1, 0, 0]
dy = [0, 0, 1, -1]
field = []
for _ in range(12):
  input_str = input().strip()
  temp = []
  for i in range(6):
    temp.append(input_str[i])
  field.append(temp)
continue_cnt = 0
while search_if_continue():
  continue_cnt += 1
print(continue_cnt)