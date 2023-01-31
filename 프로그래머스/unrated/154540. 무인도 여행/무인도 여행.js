function solution(maps) {
    function bfs(sx, sy) {
        const q = [];
        q.push([sx, sy]);
        let days = 0;
        
        while (!!q.length) {
            const [x, y] = q.shift();
            days += parseInt(maps[x][y]);
            for (let i = 0; i < 4; i++) {
                const nx = x + dx[i];
                const ny = y + dy[i];
                
                if (nx < 0 || nx >= row || ny < 0 || ny >= col)
                    continue;
                
                if (visited[nx][ny] || maps[nx][ny] === 'X')
                    continue;
                
                q.push([nx, ny]);
                visited[nx][ny] = true;
            }
        }
        return days;
    }
    var answer = [];
    const dx = [1, -1, 0, 0];
    const dy = [0, 0, 1, -1];
    const row = maps.length;
    const col = maps[0].length;
    const visited = Array.from(Array(row), () => Array(col).fill(false));
    
    for (let i = 0; i < row; i++) {
        for (let j = 0; j < col; j++) {
            if (visited[i][j] || maps[i][j] === 'X')
                continue;
            
            visited[i][j] = true;
            answer.push(bfs(i, j));
        }
    }
    answer.sort((a, b) => a - b);
    
    return !!answer.length ? answer : [-1];
}