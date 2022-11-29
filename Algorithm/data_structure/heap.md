# Heap
 Heap 은 이진 트리로서 맨 아래층을 제외하고는 완전히 채워져 있고 맨 아래층은 왼쪽부터 꽉 채워져 있다. 힙의 모든 노드는 하나씩의 값을 갖고 있는데 다음 성질을 만족한다.

> 각 노드의 값은 자기 자식의 값보다 작다. (Min heap; 중복이 있는 경우 작다 대신 작거나 같다.)

리프 노드는 자식이 없으므로 논리상 이 성질이 자동 만족된다. 


## Height of heap
 With n entries, $h = logn$ 

## ADT
Common Priority Queue ADT | java.util.PriorityQueue Class | 
--- |:---:| 
insert(k, v) | add(new SimpleEntry(k,v))
min() | peek()
removeMin() | remove()
size() | size()
isEmpty() | isEmpty()


## Heap 만들기 
 트리에서 부모자식 간의 관계를 나타내기 위해 보통의 트리를 표현할때 쓰는 링크나 포인터 같은 것을 사용해서 구현할 수도 있으나 꽉 찬 이진트리이기 때문에 배열을 이용하여 간단하게 구현할 수 있다.

 > A[k] 의 자식은 A[$2k$], A[$2k+1$] 이다.


 ## Implementation
 [link](../java/heap/Heap.java)