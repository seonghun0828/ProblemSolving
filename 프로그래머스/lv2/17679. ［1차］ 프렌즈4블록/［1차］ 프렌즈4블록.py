def solution(m, n, board):
    answer = 0
    board = list(map(list, board))
    while True:
        myset = set()
        for i in range(m-1):
            for j in range(n-1):
                if board[i][j]==board[i+1][j]==board[i][j+1]==board[i+1][j+1] != '':
                    myset.add((i,j))
                    myset.add((i+1,j))
                    myset.add((i,j+1))
                    myset.add((i+1,j+1))
        myset = sorted(list(myset))
        if len(myset) == 0:
            break
        answer += len(myset)
        for i,j in myset:
            if i == 0:
                board[i][j] = ''
            elif board[i-1][j] == '':
                board[i][j] = ''
            else:
                index = i
                for _ in range(index):
                    board[index][j] = board[index-1][j]
                    board[index-1][j] = ''
                    index -= 1
    return answer