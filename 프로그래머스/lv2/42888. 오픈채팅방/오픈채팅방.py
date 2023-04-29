def solution(record):
    answer = []
    results = []  # record를 순서대로 기록하는 결과 배열
    dic = dict()  # { 아이디 : 닉네임 } dict로 계속 변경, 저장
    
    for rec in record:  # record 순서대로 uid 입퇴실을 results에 기록
        tmp = rec.split(' ')
        if tmp[0] == 'Enter':
            results.append([0, tmp[1]])  # Enter면 [0, uid] 추가
            dic[tmp[1]] = tmp[2]  # dic에 { 아이디 : 닉네임 } 저장
        elif tmp[0] == 'Leave':
            results.append([1, tmp[1]])  # Leave면 [1, uid] 추가
        else:
            dic[tmp[1]] = tmp[2]  # Change면 dic만 갱신
            
    for result in results:  # 기록한 results 순회
        if result[0] == 0:  # 0이면 채팅방에 들어옴
            answer.append('{0}님이 들어왔습니다.'.format(dic[result[1]]))
        else:  # 1이면 채팅방에서 나감
            answer.append('{0}님이 나갔습니다.'.format(dic[result[1]]))
    return answer