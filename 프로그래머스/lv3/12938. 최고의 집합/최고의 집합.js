function solution(n, s) {
    const share = Math.floor(s / n);
    const remainder = s % n;
    if (share === 0) {
        return [-1];
    }
    
    const answer = [];
    for (let i = 0; i < n; i++) {
        answer.push(share);
    }
    let idx = n - 1;
    for (let i = 0; i < remainder; i++) {
        answer[idx--]++;
    }
    return answer;
}