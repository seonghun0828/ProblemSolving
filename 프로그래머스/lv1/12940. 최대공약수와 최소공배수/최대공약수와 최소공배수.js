function solution(n, m) {
    let a = n;
    let b = m;
    let GCD = 1; // 최대공약수 GCD는 최솟값인 1로 초기화
    let divisor = 2; // 나누는 수를 2부터 시작해서 하나씩 올려가며 두 수를 나눠보기
    
    // 로직을 반복하다가 두 수 중 하나라도 나누는 수보다 작아지면 break
    while (a >= divisor && b >= divisor) {
        // 두 수 모두 나누어 떨어지면 나누는 수가 두 수의 약수이므로 GCD에 곱하기
        if (a % divisor === 0 && b % divisor === 0) {
            GCD *= divisor;
            // 두 수는 해당 약수로 나눠 나온 몫으로 갱신하고 나누는 수도 2로 초기화
            a /= divisor;
            b /= divisor;
            divisor = 2;
            continue;
        }
        // 두 수가 나누어 떨어지지 않으면 나누는 수를 1 증가시켜 반복
        divisor++;
    }
    // 최소공배수 LCM은 GCD * (n을 GCD로 나눈 몫) * (m을 GCD로 나눈 몫)
    const LCM = GCD * (n / GCD) * (m / GCD);
    return [GCD, LCM];
}