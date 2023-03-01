const dx = [1, -1, 0, 0];
const dy = [0, 0, 1, -1];
let n, m;
let arr = [];
function solution(maps) {
    n = maps.length;
    m = maps[0].length;
    let sx, sy, lx, ly;
    
    for (let i = 0; i < n; i++) {
        arr.push([]);
        for (let j = 0; j < m; j++) {
            arr[i].push(maps[i][j]);
            if (maps[i][j] === "S") {
                sx = i;
                sy = j;
            }
            if (maps[i][j] === "L") {
                lx = i;
                ly = j;
            }
        }
    }
    const timeToLever = bfs(sx, sy, 'L');
    if (timeToLever === -1) {
        return -1;
    }
    
    const timeToExit = bfs(lx, ly, 'E');
    if (timeToExit === -1) {
        return -1;
    }
    
    return timeToLever + timeToExit;
}

function bfs(sx, sy, target) {
    const q = [];
    const visited = Array.from(Array(n), () => Array(m).fill(-1));
    q.push([sx, sy]);
    visited[sx][sy] = 0;
    
    while (q.length) {
        const [x, y] = q.shift();
        for (let i = 0; i < 4; i++) {
            const nx = x + dx[i];
            const ny = y + dy[i];
                        
            if (nx < 0 || nx >= n || ny < 0 || ny >= m)
                continue;
            
            if (visited[nx][ny] >= 0 || arr[nx][ny] === 'X')
                continue;
                        
            if (arr[nx][ny] === target)  {
                return visited[x][y] + 1;
            }
            
            q.push([nx, ny]);
            visited[nx][ny] = visited[x][y] + 1;
        }
    }
    return -1;
}