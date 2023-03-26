import sys
global r, c, table
input = sys.stdin.readline


def solution():
    global table
    inputs()
    turn_left_90()
    table = turn_left_90()  # 기존 table을 왼쪽으로 90도 돌림
    cnt = 0
    words = set()  # 단어들의 집합
    for row_idx in range(1, r):  # 시작 열을 지정. 1부터 r-1까지
        words.clear()
        for i in range(c):  # 단어를 c개 만듦
            word = table[i][row_idx:]  # 돌린 table에서 row_idx부터 시작하는 단어를 뽑아냄
            if word in words:  # 단어가 words 안에 있으면 중복
                return cnt
            words.add(word)  # 없으면 words에 추가
        cnt += 1
    return cnt


def turn_left_90():
    arr = []
    for i in range(c):
        word = ''
        for j in range(r):
            word += table[j][c - i - 1]
        arr.append(word)
    return arr


def inputs():
    global r, c, table
    r, c = map(int, input().split(' '))
    table = []
    for _ in range(r):
        table.append(input().strip())


if __name__ == '__main__':
    print(solution())
