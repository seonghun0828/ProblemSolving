function solution(stones, k) {
    let lo = 1;
    let hi = 200000000;
    let mid;
    let ans;
    
    while (lo <= hi) {
        mid = Math.floor((lo + hi) / 2);
        if (canCross(stones, k, mid)) {
            lo = mid + 1;
        } else {
            hi = mid - 1;
            ans = mid;
        }
    }
    return ans;
}

function canCross(stones, k, mid) {
    let cnt = 0;
    for (let i = 0; i < stones.length; i++) {
        if (stones[i] <= mid) {
            cnt++;
            if (cnt >= k) {
                return false;
            }
        } else {
            cnt = 0;
        }
    }
    return true;
}