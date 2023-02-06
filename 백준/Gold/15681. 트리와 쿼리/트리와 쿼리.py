import sys
global n, r, q, tree, queries, subtree_cnt
sys.setrecursionlimit(10**9)


def solution():
    global subtree_cnt
    inputs()

    subtree_cnt = [0] * (n + 1)  # 각 인덱스를 루트로 하는 서브트리의 노드 수
    search(-1, r)  # 전체 트리의 루트를 시작으로 탐색
    for query in queries:
        print(subtree_cnt[query])  # query에 맞는 서브트리 노드 수 출력


def search(prev, curr):
    if prev > -1 and len(tree[curr]) == 1:  # 루트 노드가 아닌 리프 노드면 1 할당하고 1 반환
        subtree_cnt[curr] = 1
        return 1

    for next_node in tree[curr]:  # 현재 노드에 연결되어 있는 노드들 순회
        if next_node == prev:  # 방문 처리
            continue

        # 현재 서브트리 cnt에 하위 서브트리들 재귀 탐색해서 값 추가
        subtree_cnt[curr] += search(curr, next_node)
    subtree_cnt[curr] += 1  # 루트 갯수 1 추가
    return subtree_cnt[curr]  # 현재 서브트리의 노드 수 출력


def inputs():
    global n, r, q, tree, queries
    read = sys.stdin.readline
    n, r, q = map(int, read().split(' '))
    tree = [[] for _ in range(n + 1)]
    for _ in range(n - 1):
        u, v = map(int, read().split(' '))
        tree[u].append(v)
        tree[v].append(u)
    queries = []
    for _ in range(q):
        query = int(read())
        queries.append(query)


if __name__ == '__main__':
    solution()
