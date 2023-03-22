function solution(n, stations, w) {
    var answer = 0;

    for (let i = 1; i < stations.length; i++) {
        let diff = (stations[i] - stations[i - 1] - 2 * w - 1);
        if (diff < 0)
            continue;
        let remainder = diff % (2 * w + 1);
        answer += remainder === 0 ? parseInt(diff / (2 * w + 1)) : parseInt(diff / (2 * w + 1)) + 1;
    }
    
    if (stations[0] - w - 1 > 0) {
        answer += Math.ceil((stations[0] - w - 1) / (2 * w + 1));
    }
    
    if (n - stations[stations.length - 1] - w > 0) {
        answer += Math.ceil((n - stations[stations.length - 1] - w) / (2 * w + 1));
    }
    
    return answer;
}