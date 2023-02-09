global n, arr, edges, p


def union(x, y):
    p[find_set(y)] = find_set(x)


def find_set(x):
    global p
    if p[x] != x:
        p[x] = find_set(p[x])
    return p[x]


def solution():
    global p
    inputs()

    p = []
    for i in range(n):  # make set
        p.append(i)

    edges.sort()  # 가중치 간선 오름차순 정렬
    cnt, ans = 0, 0  # 이은 간선 수, 최소 관리 비용
    for w, x, y in edges:  # 비용(가중치) w, 두 행성 x, y
        if find_set(x) == find_set(y):  # 두 행성을 이으면 사이클 형성
            continue
        union(x, y)  # 두 집합 합치기
        cnt += 1  # 간선 수 추가
        ans += w  # 최소 관리 비용에 현재 비용 추가
        if cnt >= n - 1:  # 간선이 행성 수 -1이 되면 다 탐색한 것
            break

    print(ans)


def inputs():
    global n, arr, edges
    n = int(input())
    arr = []
    edges = []  # (가중치, 행성i, 행성j) 튜플 모아 놓은 리스트
    for i in range(n):
        li = list(map(int, input().split(' ')))
        arr.append(li)
        for j in range(n):
            if i >= j:
                continue
            edges.append((arr[i][j], i, j))


if __name__ == '__main__':
    solution()
