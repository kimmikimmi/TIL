# 알고리즘 분석

## 점근적 표기 
 > 점근적 분석은 입력의 크기가 충분히 큰 경우에 대한 분석이다 

  고교시절에 배운 $\lim$ 를 이용한 분석은 점근적 분석의 한 예다. 아래의 점근적 표기법도 $\lim$ 와 유사하다. 차이가 있다면, $\lim$ 표기법에서는 함수의 차수와 계수를 모두 중시하지만 아래의 표기법에서 계수는 중요하지 않다. n 이 커짐에 따라 계수가 미치는 영향은 미미하기 때문이다.
  
  \
  앞으로 취급하는 모든 함수는 양의 값을 갖는다고 가정한다. 알고리즘의 소요 시간이나 자원의 소모량을 표현하는 함수이므로 음의 시간은 의미가 없기 때문이다.

<br />
<br />
  
  
### $\Theta$-표기법
 **알고리즘의 소요시간이 입력의 크기 n에 대해 $\Theta(n^2)$ 이라면 대략 $n^2$ 에 비례하는 시간이 소요됨을 뜻한다.** 즉 $\Theta(f(n))$ 은 점근적 증가율이 $f$(n)과 일치하는 모든 함수의 집합이다.

 $\Theta$-표기는 집합으로 정의되므로 $\Theta(n^2) = n^2+3$ 으로 표현하는 것이 아니라 $n^2+3 \in \Theta(n^2)$ 으로 표현하는 것이 맞다. 하지만 방향을 바꾸어 $n^2+3 = \Theta(n^2)$ 로 표현하는 것이 더 보편적이다.


  >*같은 기울기를 같는다, 가장 업밀하다*

<br />

### O-표기법
 **알고리즘의 소요시간이 입력의 크기 n에 대해 $O(n^2)$ 이라면 기껏해야 $n^2$ 에 비례하는 시간이 소요됨을 뜻한다.** 즉 $O(f(n))$ 은 점근적 증가율이 $f$(n)$ 을 넘지 않는 모든 함수의 집합이다. 예를들어, $n^2+5 = O(n^2)$ 그리고  $n+7 = O(n^2)$ 모두 맞는 표현이다. 즉 $O$-표기는 함수의 점근적 상한을 나타낸다.

 또한 $O(g(n))$ = { $f(n)$ | 충분히 큰 모든 n 에 대해서 $f(n) \leq cg(n)$ 인 양의 상수 c가 존재한다 }
   로 표현할 수 있다. 즉 $O(g(n))$ 은 충분히 큰 n에 대하여 g(n)에 상수만 곱하면 g(n)이 누를 수 있는(더 크거나 같아질 수 있는) 모든 함수의 집합이다.

<br />

### $\Omega$-표기법
 **알고리즘의 소요시간이 입력의 크기 n에 대해 $O(n^2)$ 이라면 적어도 $n^2$ 에 비례하는 시간이 소요됨을 뜻한다**. 즉 $O(f(n))$ 은 점근적 증가율이 적어도 $f$(n)$ 이 되는 모든 함수의 집합이다. 예를들어, $n^2+5 = \Omega(n^2)$ 그리고  $n^3 = O(n^2)$ 모두 맞는 표현이다. 즉 $\Omega$-표기는 함수의 점근적 하한을 나타낸다.


 또한 $Omega(g(n))$ = { $f(n)$ | 충분히 큰 모든 n 에 대해서 $cg(n) \leq f(n)$ 인 양의 상수 c가 존재한다 }
   로 표현할 수 있다. 즉 $O(g(n))$ 은 충분히 큰 n에 대하여 g(n)에 상수만 곱하면 g(n)이 질 수 있는(더 작거나 같아질 수 있는) 모든 함수의 집합이다.

<br />

 >여기서 $\Theta(g(n)) = O(g(n))$ $\bigcup$  $\Omega(g(n))$ 임을 알 수 있다.

<br />

### o-표기법
 $o$-표기는(*리틀오* 라고 읽음) 는 함수의 증가율이 점근적 의미에서 어느 한계보다 더 작다는 것을 표현하고자 할때 사용한다. 예를들어 함수 $5n=o(n^2)$이다. $5n$ 의 증가율은 $n^2$의 증가율보다 작다. 그렇지만 $0.5n^2$ 은 $o(n^2)$에 속하지 않는다. 두 증가속도가 점근적으로 동일하기 때문이다.
  
  >*같은 기울기를 포함하지 않는다!*

<br />

### $\omega$-표기법
 $\omega$-표기는(*리틀오메가* 라고 읽음) 는 함수의 증가율이 점근적 의미에서 어느 한계보다 더 크다는 것을 표현하고자 할때 사용한다. 예를들어 함수 $5n^3=\omega(n^2)$이다. $5n$ 의 증가율은 $n^2$의 증가율보다 크다. 그렇지만 $2n^2$ 은 $o(n^2)$에 속하지 않는다. 두 증가속도가 점근적으로 동일하기 때문이다.
  
  >*같은 기울기를 포함하지 않는다!*


<br />


## 점화식의 점근적 분석 방법

### 반복대치
 > $T(n)$ 을 $T(n-1)$ 로 대치하고, $T(n-1)$ 을 $T(n-2)$ 로 대치하고 ... $T(1)$ 까지 반복해서 대치해가는 것을 이용해 점근적 복잡도를 구한다.

<br />

### 추정 후 증명
 > 식의 모양을 보고 점근적 복잡도를 추정한 다음 그것이 옳음을 귀납적으로 증명하는 방법이다.

<br />

### 마스터 정리
 > 특정한 모양을 가진 재귀식에서 바로 결과를 알 수 있는 아주 유용한 정리다.
  
 > $T(n)$ = $a T({n \over b}) +f(n)$

 > 즉 입력의 크기가 $n$ 인 문제를 풀기 위해 입력의 크기가 $n/b$인 문제를 $a$개 풀고, 나머지 f(n) 의 오버헤드가 필요한 알고리즘들이 해당된다. 


마스터 정리의 근사버전.

0. $h(n)$ = $n^{\log{_b}{a}}$ 이다.
1. $\lim_{x \to \infty} {f(n)\over h(n)} = 0$ 이면 T(n) = $\Theta(h(n))$ 이다.
2. $\lim_{x \to \infty} {f(n)\over h(n)} = \infty$ 이고 충분히 큰 모든 $n$ 에 대해 $a f({n \over b}) < f(n)$ 이면 T(n) = $\Theta(f(n))$ 이다.
3. $\lim_{x \to \infty} {f(n)\over h(n)} = \Theta(1)$ 이면 T(n) = $\Theta(h(n)\log n)$ 이다.

즉 
1. $h(n)$이 더 무거우면 $h(n)$ 이 수행시간을 결정한다.
2. $f(n)$ 이 더 무거우면 $f(n)$이 수행시간을 결정한다.
3. 둘이 같은 무게이면 $h(n)*\log n$ 이 수행시간이 된다.

<br />

**e.g.**

>  $T(n)$ = $2T({n \over 3}) + c$ 인 경우
 a = 2, b = 3, $h(n) = n^{\log{_3}{2}}$, f(n) = c
  
> 즉 $f(n) \over h(n)$ = $0$ 이므로 $T(n) = \Theta(n^{\log{_3}{2}})$ 이다. 
