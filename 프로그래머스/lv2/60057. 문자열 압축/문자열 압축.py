def solution(s):
    answer = len(s)
    
    for k in range(1, int(len(s) / 2) + 1):  # 자르는 단위 1 ~ len/2 까지
        leng = get_compression_length(s, k)  # k로 나눠 압축한 길이
        answer = min(answer, leng)
    
    return answer

def get_compression_length(s, k):  # 문자열 s를 k로 나눠 압축한 문자열의 길이 반환
    compressed = ""  # 압축한 문자열
    prev = ""  # 이전 문자열
    prev_cnt = 1  # 이전 문자열이 반복된 수
    st, ed = 0, len(s)  # st 인덱스를 0에서 시작해 k만큼 이동시키기
    
    while st <= ed - k:  # st 인덱스는 ed - k까지만 증가
        curr = s[st : st + k]  # 현재 인덱스 만큼 자른 문자열
        if prev == curr:  # 이전 문자열과 현재 문자열이 같음
            prev_cnt += 1
        else:  # 이전 문자열과 현재 문자열이 다름
            # prev_cnt가 1 초과하면 압축 문자열에 반영하고 1이면 생략
            prev_cnt_str = str(prev_cnt) if prev_cnt > 1 else ""
            compressed += prev_cnt_str + prev
            prev = curr
            prev_cnt = 1
        st += k
    
    # s 끝까지 문자열이 반복된 경우 아직 추가 안된 prev를 압축 문자열에 반영
    prev_cnt_str = str(prev_cnt) if prev_cnt > 1 else ""
    compressed += prev_cnt_str + prev
    compressed += s[st : ed]  # 남은 문자열이 있다면 압축 문자열에 그대로 반영
    return len(compressed)
        
        
        
        
        