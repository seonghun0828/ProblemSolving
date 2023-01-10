let dists, N;

function isPossible(minDist, cnt) {
    let prev = 0;
    
    for (let dist of dists) {
        if (prev > 0) {
            dist += prev;
            prev = 0;
        }
        if (dist < minDist) {
            prev += dist;
            cnt--;
        }
        if (cnt < 0)
            return false;
    }
    return true;
}

function solution(distance, rocks, n) {
    rocks.sort((a, b) => a - b);
    dists = [];
    let prev = 0;
    
    rocks.forEach((rock, idx) => {
        dists.push(rock - prev);
        prev = rock;
    });
    
    dists.push(distance - rocks[rocks.length - 1]);
    
    let st = 0;
    let ed = 1000000000;
    let mid;
    let ans = 0;
    
    while (st <= ed) {
        mid = Math.floor((st + ed) / 2);
        if (isPossible(mid, n)) { // 가능하면 최대를 찾아야하므로 거리(mid) 늘리기
            st = mid + 1;
            ans = Math.max(ans, mid);
        } else { // 불가능하면 거리를 줄여 가능하게 만들기
            ed = mid - 1;
        }
    }
    return ans;    
}