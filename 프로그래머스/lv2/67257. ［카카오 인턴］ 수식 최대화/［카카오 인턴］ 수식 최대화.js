function solution(expression) {
    var answer = 0;
    
    const combi = [['+', '-', '*'], ['+', '*', '-'], ['-', '+', '*'],
                   ['-', '*', '+'], ['*', '+', '-'], ['*', '-', '+']]
    const originalNums = [];
    const originalOprs = [];
    
    parse(expression, originalNums, originalOprs);
    
    combi.forEach((order) => {
        let nums = [...originalNums];
        let oprs = [...originalOprs];
        order.forEach((opr) => {
            const stack = [nums[0]];  // 계산되는 숫자 배열을 저장할 스택
            let numIdx = 0;  // 숫자 배열의 현재 차례 인덱스
            oprs = oprs.filter((curr, idx) => {  // 남은 연산자 배열 filter
                if (opr === curr) {  // 남은 연산자 배열의 현재 연산자가 opr과 같으면 계산
                    const result = calc(stack.pop(), nums[++numIdx], opr);
                    stack.push(result);  // 계산 결과 스택에 추가
                    return false;  // 계산한 연산자는 배열에서 제거
                }
                stack.push(nums[++numIdx]);  // 계산하지 않았으면 숫자 배열의 인덱스 그대로 스택에 추가
                return true;  // 계산하지 않은 연산자는 배열에 그대로 두기
            });
            nums = stack;  // 계산된 결과 스택을 nums 배열로 갱신
            if (nums.length === 1) {  // nums 배열이 하나만 남았으면 연산이 끝난 것이므로 answer 갱신
                answer = Math.max(answer, Math.abs(nums[0]));   
            }
        });
    });
    
    return answer;
}

function calc(a, b, opr) {  // 숫자 a, b와 연산자가 들어오면 계산 값을 반환
    switch (opr) {
        case '+':
            return a + b;
        case '-':
            return a - b;
        case '*':
            return a * b;
    }
}

function parse(expression, originalNums, originalOprs) {  // expression을 숫자, 연산자 배열로 파싱
    let prev = "";
    for (let i = 0; i < expression.length; i++) {
        if (expression[i] === '+' || expression[i] === '-' || expression[i] === '*') {
            originalOprs.push(expression[i]);
            if (!!prev) {
                originalNums.push(parseInt(prev));
                prev = "";
            }
        } else {
            prev += expression[i];
        }
    }
    if (!!prev) {
        originalNums.push(parseInt(prev));
        prev = "";
    }
}