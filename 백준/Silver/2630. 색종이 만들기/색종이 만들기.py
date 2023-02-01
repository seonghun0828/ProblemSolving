global input_n, arr


def check_color(n, sx, sy):  # arr의 해당 범위에서 white, blue 갯수 구하기
    has_white, has_blue = False, False  # white, blue가 있는지 boolean
    for i in range(sx, sx + n):
        for j in range(sy, sy + n):
            if arr[i][j] == 0 and not has_white:
                has_white = True
            if arr[i][j] == 1 and not has_blue:
                has_blue = True
    return int(has_white), int(has_blue)  # boolean을 int 형으로 변환해 반환


def divide_from(n, sx, sy):
    has_white, has_blue = check_color(n, sx, sy)
    if not has_white:  # white가 없으면 blue만 있으니 white: 0, blue: 1 반환
        return 0, 1
    if not has_blue:  # blue가 없으면 white만 있으니 white: 1, blue: 0 반환
        return 1, 0
    n = int(n / 2)  # n을 절반 값으로 갱신
    white_sum, blue_sum = 0, 0  # 하위 그룹 white, blue 갯수의 sum 값
    for i in range(2):  # 0, 1
        for j in range(2):  # 0, 1
            # 현재 종이를 4등분 해 white, blue 갯수 구하기
            tmp_white, tmp_blue = divide_from(n, sx + n * i, sy + n * j)
            white_sum += tmp_white
            blue_sum += tmp_blue
    return white_sum, blue_sum  # 현재 종이의 white, blue 갯수 합 반환


def inputs():
    global input_n, arr
    input_n = int(input())
    arr = []
    for i in range(input_n):
        arr.append(list(map(int, input().split(' '))))


def solution():
    inputs()
    ans = divide_from(input_n, 0, 0)
    print(ans[0])
    print(ans[1])


if __name__ == '__main__':
    solution()
