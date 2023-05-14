global n, r, relations, selected, candidate_keys

def solution(relation):
    global n, r, relations, selected, candidate_keys
    n = len(relation[0])  # 속성의 개수
    relations = relation
    selected = [False] * (n)  # 현재 선택된 속성 배열
    candidate_keys = set()
    for num in range(1, n + 1):
        r = num
        combi(0, 0)  # n개의 속성 중 r개의 속성 조합 찾기 (nCr)
    
    return len(candidate_keys)

def combi(idx, cnt):
    global selected
    if cnt == r:  # r개의 조합을 찾음
        identify_candidate()  # 후보 키를 확인
        return
    
    for i in range(idx, n):  # 뽑는 속성의 개수 r은 1부터 n까지
        selected[i] = True
        combi(i + 1, cnt + 1)
        selected[i] = False
        # combi(i + 1, cnt)

def identify_candidate():
    global ans, candidate_keys
    keys = []  # 현재 선택한 키들의 인덱스 배열
    for i in range(n):
        if selected[i]:
            keys.append(i)
    keys = tuple(keys)  # set에 추가하기 위해 tuple로 변경
    if is_unique(keys) and is_minimal(keys):  # 해당 keys가 유일성과 최소성을 만족
        candidate_keys.add(keys)  # 후보 키에 keys를 추가
        
def is_minimal(keys):  # keys가 최소성을 만족하는지
    for candidate_key in candidate_keys:  # 후보 키들 중 하나의 후보 키 순회
        is_subset = True  # candidate_key가 keys의 부분 집합인지
        for candidate in candidate_key:  # candidate_key의 개별 원소 
            if candidate not in keys:  # 개별 원소가 keys에 없으면 부분 집합이 아님
                is_subset = False
                break
        if is_subset:  # candidate_keys 중 하나라도 keys의 부분 집합이면 최소성을 만족하지 못함
            return False
    return True  # keys는 candidate_keys의 어느 키와도 부분 집합이 아니기 때문에 최소성 만족
        
def is_unique(keys):  # keys가 유일성을 만족하는지
    my_set = set()  # 중복 여부를 확인하기 위해 set을 선언
    for row in relations:  # relation의 한 줄(row)를 확인
        value = ''  # 해당 row 중 key에 해당하는 튜플 값
        for key in keys:
            value += row[key]
        if value in my_set:  # value가 set에 존재하면 유일하지 않음
            return False
        my_set.add(value)  # value가 set에 없으면 추가
    return True  # keys는 모든 relation row에 유일함
        
        
        
        
        
        
        
        
        
        