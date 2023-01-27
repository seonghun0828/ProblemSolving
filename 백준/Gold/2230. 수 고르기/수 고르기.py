global n, m, arr


def inputs():
    global n, m, arr
    n, m = map(int, input().split(' '))
    arr = []
    for _ in range(n):
        arr.append(int(input()))


def solution():
    global arr
    inputs()
    arr.sort()  # 이분 탐색 위해 오름차순 정렬
    min_sub = arr[-1] - arr[0]  # 배열 최댓값 - 최솟값 = 가능한 최대 차이

    for i in range(n):  # 배열 순회
        num = arr[i]  # 현재 차례의 수. 차이를 계산할 다른 수는 이분 탐색으로 찾기
        left = i + 1 if i < n - 1 else i  # 현재 인덱스 바로 다음 인덱스. 현재 인덱스가 배열의 마지막이면 같게.  
        right = len(arr) - 1  # 배열의 마지막 인덱스
        while left <= right:  # 이분 탐색 시작
            mid = int((left + right) / 2)  # left, right의 중간 인덱스
            if abs(num - arr[mid]) >= m:  # 현재 차례의 수와 중간 인덱스 배열 값의 차이가 m 이상이면 
                right = mid - 1  # 최솟값을 구해야 하므로 더 작은 값 찾기
                min_sub = min(min_sub, abs(num - arr[mid]))  # 최소 차이 갱신
            else:  # 두 값의 차이가 m보다 작으면
                left = mid + 1  # 차이를 키워야 하기 때문에 더 큰 값 찾기
                
    print(min_sub)  # 순회 종료하고 최종 최소 차이 출력


if __name__ == '__main__':
    solution()