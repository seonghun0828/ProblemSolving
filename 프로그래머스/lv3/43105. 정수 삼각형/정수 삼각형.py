def solution(triangle):
    answer = triangle[0][0];
    n = len(triangle);
    dp = [[0] * i for i in range(1, n + 1)]
    dp[0][0] = triangle[0][0]
    
    for i in range(n - 1): # 0 ~ n-1 까지, 마지막 줄 선택 x
        for j in range(i + 1):
            dp[i+1][j] = max(dp[i+1][j], dp[i][j] + triangle[i+1][j])
            dp[i+1][j+1] = max(dp[i+1][j+1], dp[i][j]+triangle[i+1][j+1])
            answer = max(answer, dp[i+1][j], dp[i+1][j+1])
    return answer