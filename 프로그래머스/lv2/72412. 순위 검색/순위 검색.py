def solution(info, query):
    answer = []
    info_dict = dict()
    parse(info_dict, info)

    for curr_query in query:
        tmp = curr_query.split(' and ')
        tmp1 = tmp[3].split(' ')
        q = tmp[0] + tmp[1] + tmp[2] + tmp1[0]  # 찾아야 할 이번 차례 쿼리
        score = int(tmp1[1])  # 이번 차례 점수
        if q not in info_dict:  # q가 info_dict에 없으면 answer에 0 삽입
            answer.append(0)
            continue
        # 쿼리에 맞는 배열 info_dict[q]에서 score의 인덱스를 찾아냄
        idx = get_idx_by_lower_bound(info_dict[q], score)
        answer.append(len(info_dict[q]) - idx)  # 전체 길이 - idx = score 이상을 받은 사람 수
    return answer


def get_idx_by_lower_bound(arr, score):
    st, ed = 0, len(arr) - 1
    while st <= ed:
        mid = int((st + ed) / 2)
        if arr[mid] >= score:
            ed = mid - 1
        else:
            st = mid + 1
    return st


def parse(info_dict, info):
    for curr_info in info:
        tmp = curr_info.split(' ')
        for i in range(16):  # 0 ~ 15까지 숫자 16개가 곧 비트마스킹으로 길이 4짜리 순열 그 자체
            key = ""
            for j in range(4):  # tmp의 0 ~ 3번째 자리
                # 순열 i의 j번째 자리가 1이면 tmp[j]를, 아니면 -를 key에 추가
                key += tmp[j] if (i & 1 << j) > 0 else '-'
            val = info_dict.get(key)
            if val is None:
                info_dict[key] = [int(tmp[4])]
            else:
                val.append(int(tmp[4]))
    for key in info_dict:  # info_dict 모든 value(score 배열)를 오름차순 정렬
        info_dict[key].sort()