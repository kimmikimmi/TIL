# 그리디 알고리즘

눈앞의 이익만 우선 추구하는 알고리즘

    do {
        가장 좋아보이는 선택을 한다.
    } until (해 구성 완료)



예시) MST (Prim, Kruskal), Dijkstra's shortest path, 허프만 코딩 알고리즘

<br />

### 회의실 배정 문제 

회사에 1개의 회의실이 있다. 여러 부서에서 회의실을 사용하므로 미리 신청을 받아 스케쥴링한다. 회의실을 사용하고자 하는 부서는 원하는 회의 시작 시간과 종료 시간을 명시해서 신청서를 제출한다. 겹치는 회의가 없게 하면서 가장 많은 수의 회의를 예약 하는 방법.

> 종료 시간 순으로 소팅하여 종료시간이 빠른 것부터 넣는다.

>왜?
 정렬 결과 가장 종료시간을 포함하지 않는 최적해 T 가 있다고 하자. 그 T 는 가장 빠른 종료 시간을 포함하는 일정을 넣을 수 있다. 