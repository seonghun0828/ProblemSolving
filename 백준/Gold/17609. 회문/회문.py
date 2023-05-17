global n, words


def solution():
    inputs()

    for word in words:
        if is_palindrome(word):
            print(0)
        elif is_sudo_palindrome(word):
            print(1)
        else:
            print(2)


def is_palindrome(word):
    size = len(word)
    half = int(size / 2)
    if size % 2 == 0:  # 짝수일 때 반을 나눈 게 일치 하는지
        if word[:half] == word[-1:half - 1:-1]:
           return True
    else:  # 홀수일 때 가운데 빼고 반을 나눈 게 일치 하는지
        if word[:half] == word[-1:half:-1]:
            return True
    return False  # 어느 것도 아니면 팰린드롬이 아님


def is_sudo_palindrome(word):
    size = len(word)
    half = int(size / 2)
    if size % 2 == 0:  # 짝수
        left = word[:half - 1]  # 가운데 왼쪽 글자를 빼고 두 단어로 나눔
        right = word[-1:half - 1:-1]
        if has_a_difference(left, right):  # 두 단어가 하나의 차이만 있음
            return True
        left = word[:half]  # 가운데 오른쪽 글자를 빼고 두 단어로 나눔
        right = word[-1:half:-1]
        if has_a_difference(left, right):  # 두 단어가 하나의 차이만 있음
            return True
    else:  # 홀수
        left = word[:half]  # 절반 - 1 : 절반 의 두 단어로 나눔
        right = word[-1:half - 1:-1]
        if has_a_difference(left, right):
            return True
        left = word[:half + 1]  # 절반 : 절반 - 1 의 두 단어로 나눔
        right = word[-1:half:-1]
        if has_a_difference(left, right):
            return True

    return False  # 어느 것도 아니라면 유사 회문도 아님


def has_a_difference(word1, word2):
    already_different = False  # 이미 두 단어에 차이가 존재 하는지
    idx1 = len(word1) - 1  # 두 단어의 끝 인덱스 부터 시작
    idx2 = len(word2) - 1

    while idx1 >= 0 and idx2 >= 0:  # 두 인덱스 모두 0 이상일 때 반복
        if word1[idx1] != word2[idx2]:  # 두 단어의 인덱스 글자가 다를 때
            if already_different:  # 이미 다른 인덱스에서 글자 차이가 있었음
                return False
            already_different = True  # 첫 글자 차이
            if idx1 > idx2:  # 더 인덱스가 큰(글자가 긴) 인덱스를 -1
                idx1 -= 1
            else:
                idx2 -= 1
        else:  # 두 단어의 인덱스 글자가 같으면 두 인덱스 모두 -1
            idx1 -= 1
            idx2 -= 1
    return True  # 글자 차이가 하나만 남


def inputs():
    global n, words
    n = int(input())
    words = [input() for _ in range(n)]


if __name__ == '__main__':
    solution()
