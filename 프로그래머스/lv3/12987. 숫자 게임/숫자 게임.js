function solution(A, B) {
    var answer = 0;
    let aIdx = 0;
    let bIdx = 0;
    const len = A.length;
    
    A.sort((a, b) => a - b);
    B.sort((a, b) => a - b);
    
    while (aIdx < len && bIdx < len) {
        if (A[aIdx] < B[bIdx]) {
            aIdx++;
            bIdx++;
            answer++;
        } else {
            bIdx++;
        }
    }
    return answer;
}