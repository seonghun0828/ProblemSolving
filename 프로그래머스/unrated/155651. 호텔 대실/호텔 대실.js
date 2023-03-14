function solution(book_time) {
    book_time.sort();  // 계산 편의를 위해 시작 시간 순으로 오름차순 정렬
    const rooms = [];
    book_time.forEach((curr, idx) => {
        const [st, ed] = convert(curr);
        if (idx === 0) {
            rooms.push(ed);
            return;
        }
        
        if (rooms[0] > st) {  // 현재 가장 일찍 끝나는 시간보다 먼저 시작하면
            rooms.push(ed);  // 새로운 room이 필요하므로 rooms에 현재 끝나는 시간 추가
            rooms.sort((a, b) => a - b);
        }
        else {  // rooms 순회하며 들어갈 곳이 있는지 확인
            for (let j = rooms.length - 1; j >= 0; j--) {
                if (rooms[j] <= st) {
                    rooms[j] = ed;
                    rooms.sort((a, b) => a - b);
                    break;
                }
            }
        }
    });
    return rooms.length;
}

function convert(curr) {
    let tmp = curr[0].split(':');
    let st = parseInt(tmp[0]) * 60 + parseInt(tmp[1]);
    tmp = curr[1].split(':');
    let ed = parseInt(tmp[0]) * 60 + parseInt(tmp[1]);
    return [st, ed + 10];  // 끝나는 시간에 청소 시간 포함
}