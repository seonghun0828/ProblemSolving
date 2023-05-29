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

function canCross(stones, k, friendsCnt) {  // 
    let cnt = 0; // 한 번에 뛰어야 하는 징검다리 길이
    for (let i = 0; i < stones.length; i++) {
        if (stones[i] <= friendsCnt) { // 현재 디딤돌 숫자보다 친구들 수가 작거나 같음
            cnt++; // 현재 디딤돌 사라졌으니 한 번에 뛰어야 하는 징검다리 길이 + 1
            if (cnt >= k) { // 한 번에 뛰어야 하는 징검다리 길이가 k 이상이면 불가능
                return false;
            }
        } else {  // 친구들 수로 현재 디딤돌 다 건너도 디딤돌이 사라지지 않음
            cnt = 0;  // 현재 디딤돌에서 한 번에 뛰어야 하는 징검다리 길이는 0으로 초기화
        }
    }
    return true;
}