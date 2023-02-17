global n, m, ops, p


def solution():
    global p
    inputs()

    p = []
    for i in range(n + 1):  # make set
        p.append(i)

    for op, a, b in ops:
        if op == 0:  # 합집합
            union(a, b)
        elif find_set(a) == find_set(b):  # 두 원소가 같은 집합에 포함
            print("YES")
        else:  # 두 원소가 다른 집합
            print("NO")


def union(x, y):  # 합집합
    px = find_set(x)
    py = find_set(y)
    if px != py:
        p[py] = px


def find_set(x):  # x의 부모 찾기
    if p[x] == x:
        return x
    p[x] = find_set(p[x])
    return p[x]


def inputs():
    global n, m, ops
    n, m = map(int, input().split(' '))
    ops = []
    for _ in range(m):
        ops.append(list(map(int, input().split(' '))))


if __name__ == '__main__':
    solution()
