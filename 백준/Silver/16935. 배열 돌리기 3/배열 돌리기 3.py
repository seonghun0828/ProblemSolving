global n, m, r, arr, ops


def solution():
    global arr
    inputs()

    for op in ops:
        if op == 1:
            arr = op1()
        elif op == 2:
            arr = op2()
        elif op == 3:
            arr = op3()
        elif op == 4:
            arr = op4()
        elif op == 5:
            arr = op5()
        else:
            arr = op6()

    for i in range(n):
        for j in range(m):
            print(arr[i][j], end=' ')
        print()


def op6():
    new_arr = make_empty_arr(n, m)
    half_n, half_m = int(n / 2), int(m / 2)
    start_tuple_list = [(0, 0), (0, half_m), (half_n, half_m), (half_n, 0)]
    multi_tuple_list = [(1, 0), (0, 1), (1, 0), (0, 1)]
    for k in range(4):
        x, y = start_tuple_list[k]
        dx, dy = multi_tuple_list[k]
        for i in range(half_n):
            for j in range(half_m):
                new_arr[(x + i + dx * half_n) % n][(y + j + dy * half_m) % m] = arr[x + i][y + j]
    return new_arr


def op5():
    new_arr = make_empty_arr(n, m)
    half_n, half_m = int(n / 2), int(m / 2)
    start_tuple_list = [(0, 0), (0, half_m), (half_n, half_m), (half_n, 0)]
    multi_tuple_list = [(0, 1), (1, 0), (0, 1), (1, 0)]
    for k in range(4):
        x, y = start_tuple_list[k]
        dx, dy = multi_tuple_list[k]
        for i in range(half_n):
            for j in range(half_m):
                new_arr[(x + i + dx * half_n) % n][(y + j + dy * half_m) % m] = arr[x + i][y + j]
    return new_arr


def op4():
    global n, m
    new_arr = make_empty_arr(m, n)
    for i in range(m):
        for j in range(n):
            new_arr[i][j] = arr[j][m - i - 1]
    tmp = m
    m = n
    n = tmp
    return new_arr


def op3():
    global n, m
    new_arr = make_empty_arr(m, n)
    for i in range(m):
        for j in range(n):
            new_arr[i][j] = arr[n - j - 1][i]
    tmp = m
    m = n
    n = tmp
    return new_arr

def op2():
    new_arr = []
    for i in range(n):
        new_arr.append(arr[i][::-1])
    return new_arr


def op1():
    new_arr = make_empty_arr(n, m)
    for i in range(n):
        for j in range(m):
            new_arr[i][j] = arr[n - i - 1][j]
    return new_arr


def make_empty_arr(row, col):
    empty_arr = [[0 for _ in range(col)] for _ in range(row)]
    return empty_arr


def inputs():
    global n, m, r, arr, ops
    n, m, r = map(int, input().split(' '))
    arr = []
    for i in range(n):
        arr.append(list(map(int, input().split(' '))))
    ops = list(map(int, input().split(' ')))


if __name__ == '__main__':
    solution()
