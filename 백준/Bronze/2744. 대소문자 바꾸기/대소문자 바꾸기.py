def solution():
    word = input()
    ans = ''
    for char in word:
        if char.isupper():
            ans += char.lower()
        else:
            ans += char.upper()
    print(ans)


if __name__ == '__main__':
    solution()
