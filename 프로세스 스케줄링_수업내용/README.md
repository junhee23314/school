## 포로세스 스케줄링

스케줄링(Scheduling)의 목적

* 시스템의 성능(performance) 향상

* 대표적 시스템 성능 지표(index)
	* 응답시간(response time)
		작업 요청(submisson)으로부터 응답을 받을때까지의 시간
	* 작업 처리량(throughput)
		단위 시간 동안 완료된 작업의 수
	* 자원 활용도(resource utilization)
		주어진 시간(Tc) 동안 자원이 활용된 시간(Tr)

* 목적에 맞는 지표를 고려하여 스케줄링 기법을 선택



#### 08/12 수업
![수업내용문제](https://github.com/junhee23314/school/blob/main/%ED%94%84%EB%A1%9C%EC%84%B8%EC%8A%A4%20%EC%8A%A4%EC%BC%80%EC%A4%84%EB%A7%81_%EC%88%98%EC%97%85%EB%82%B4%EC%9A%A9/img/2024-0812_%EC%88%98%EC%97%85%EB%82%B4%EC%9A%A9%EB%AC%B8%EC%A0%9C.png)

문제는 보시다시피 가장 효율적인 프로세스 스케줄링을 순서대로 나열하는 것이다.



##### [FCFS]
일단 먼저 "FCFS"를 보자면

![스케줄링문제풀이(FCFS)](https://github.com/junhee23314/school/blob/main/%ED%94%84%EB%A1%9C%EC%84%B8%EC%8A%A4%20%EC%8A%A4%EC%BC%80%EC%A4%84%EB%A7%81_%EC%88%98%EC%97%85%EB%82%B4%EC%9A%A9/img/%EC%8A%A4%EC%BC%80%EC%A4%84%EB%A7%81%EB%AC%B8%EC%A0%9C%ED%92%80%EC%9D%B4(FCFS).jpg)

```
위 사진과 같이 풀이를 정리 해보면

1. 대기시간 (Waiting Time)

p1: 0
p2: 0
p3: 0
p4: 4
p5: 4

2. 응답시간 (Response Time)

p1: 0
p2: 4
p3: 5
p4: 7
p5: 8
응답 시간의 총합: ( 2 + 4 + 5 + 7 + 8 = 26 )

평균 응답 시간 
평균 응답 시간 = 총 응답 시간 / 프로세스 수

= ( 26 / 5 )

평균 응답 시간이  26 / 5 라는 걸 알수있다.
```

##### [TQ (time quantum)]
그리고 tq:1 보면

![스케줄링문제풀이(tq1)](https://github.com/junhee23314/school/blob/main/%ED%94%84%EB%A1%9C%EC%84%B8%EC%8A%A4%20%EC%8A%A4%EC%BC%80%EC%A4%84%EB%A7%81_%EC%88%98%EC%97%85%EB%82%B4%EC%9A%A9/img/%EC%8A%A4%EC%BC%80%EC%A4%84%EB%A7%81%EB%AC%B8%EC%A0%9C%ED%92%80%EC%9D%B4(tq1).jpg)

```
위 사진과 같이 풀이를 정리 해보면

1. 대기시간 (Waiting Time)

p1: 0
p2: 0
p3: 6
p4: 3
p5: 4

2. 응답시간 (Response Time)

p1: 2
p2: 4
p3: 11
p4: 6
p5: 8
응답 시간의 총합: ( 2 + 4 + 11 + 6 + 8 = 31 )

평균 응답 시간 
평균 응답 시간 = 총 응답 시간 / 프로세스 수

= ( 31 / 5 )

평균 응답 시간이  31 / 5 라는 걸 알수있다.
```
이와 같이 tq:2, tq:3도 평균응답시간를 구해보면
tq:2의 평균응답시간은 ` 33 / 5 `,
tq:3의 평균응답시간은 ` 27 / 5 `이라고 구해볼수있다.


따라서 가장 효율적인 프로세스 스케줄링을 순서대로 나열하자면
첫번째는 `FCFS(26/5)` , 두번째는 `tq:3(27/5)`, 셋번째는 `tq:1(31/5)`, 마지막은 `tq:2(33/5)`이다.


#### 08/13 수업

기본 스케줄링 알고리즘

┌
   * FCFS (First-Come-First-Service)             Fairness
   * RR (Round-Robin)			┘  	  (공평성)

┌
   * SPN (Shortest-Process-Next)
   * SRTN (Shortest Remaining Time Next)   	Efficieney/Performance
   * HRRN (High-Response-Ratio-Next)   ┘ 	(효율성, 성능)
								
* MLQ(Multi-level Queue)
* MFQ (Multi-level Feedback Queue)


MLQ(Multi-level Queue)

* 작업(or 우선순위) 별 별도의 ready queue를 가짐
	
	1)최초 배정 된 queue를 벗어나지 못함

  	2)각각의 queue는 자신만의 스케쥴링 기법 사용

* Queue 사이에는 우선순위 기반의 스케줄링 사용

[장점]
  * 빠른 응답시간(?)
[단점]
  * 여러 개의 Queue 관리 등 스케쥴링 overhead
  
  * 우선순위가 낮은 queue는 starvation 현상 발생 가능
