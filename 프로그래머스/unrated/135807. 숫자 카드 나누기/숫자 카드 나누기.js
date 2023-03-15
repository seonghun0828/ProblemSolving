function solution(arrayA, arrayB) {
    var answer = 0;
    answer = Math.max(answer, getGCD(arrayA, arrayB));
    answer = Math.max(answer, getGCD(arrayB, arrayA));
    return answer;
}

function getGCD(arrayA, arrayB) {
    let gcdA = arrayA[0];
    for (let i = 1; i < arrayA.length; i++) {
        gcdA = gcd(gcdA, arrayA[i]);
    }
    let isDivide = true;
    for (let i = 0; i < arrayB.length; i++) {
        if (arrayB[i] % gcdA === 0) {
            isDivide = false;
            break;
        }
    }
    return isDivide ? gcdA : 0;
}

function gcd(a, b) {
    if (a < b) {
        const tmp = a;
        a = b;
        b = tmp;
    }
    let n;
    while (b > 0) {
        n = a % b;
        a = b;
        b = n;
    }
    return a;
}