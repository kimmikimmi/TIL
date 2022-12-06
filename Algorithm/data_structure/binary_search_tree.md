# Binary Search Tree

## 특징 
1. 이진 검색 트리의 각 노드는 키 값을 하나씩 갖는다. 각 노드의 키 값은 모두 달라야 한다.
2. 최상위 레벨에 루트 노드가 있고, 각 노드는 최대 두 개의 자식 노드를 갖는다.
3. 임의의 노드의 키 값은 자신의 왼쪽에 있는 모든 노드의 키 값보다 크고, 오른쪽에 있는 노드의 키 값보다 적다.


```
Algorithm TreeSearch(p, k): 
    if p is external then
        return p
    else if k == key(p) then
        return p
    else if k < key(p) then
        return TreeSearch(left(p), k)
    else 
        return TreeSearch(right(p), k)
```

```
Algorithm TreeInsert(k, v):
    p = TreeSearch(root(), k)
    if k == key(p) then
        Change p's value to (v)
    else 
        expandExternal(p, (k, v)) // Stores entry e at the external position p, and expands p to be internal, having two new leaves as children
```