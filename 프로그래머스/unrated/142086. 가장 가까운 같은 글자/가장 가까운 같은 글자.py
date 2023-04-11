def solution(s):
    ans = []
    map = dict()

    for i in range(len(s)):
        char = s[i]
        if map.get(char) is not None:
            ans.append(i - map[char])
        else:
            ans.append(-1)
        map[char] = i

    return ans