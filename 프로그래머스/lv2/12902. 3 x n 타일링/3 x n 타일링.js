function solution(n) {
    if (n % 2 === 1)
        return 0;
    
    const dp = new Array(n + 1);
    dp[0] = 1;
    dp[2] = 3;
    
    for (let i = 4; i <= n; i += 2) {
        dp[i] = (dp[i - 2] * dp[2]);
        for (let j = 0; j <= i - 4; j += 2) {
            dp[i] += (dp[j] * 2);
        }
        dp[i] = dp[i] % 1000000007;
    }
    return dp[n];
}