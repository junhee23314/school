## 프로세스 스케줄링✨

**{스케줄링(Scheduling)의 목적}**

시스템의 성능 향상:

*대표적 시스템 성능 지표

응답시간(짧은): <br>
 작업요청으로부터 응답을 받을때까지의 시간 <br>
작업 처리량(많은): <br>
 단위 시간 동안 완료된 작업의 수 <br>
자원 활용도(높은): <br>
 주어진 시간(Tc) 동안 자원이 활용된 시간(Tr) <br>

**{스케줄링 기준(Criteria)}**

스케줄링 기법이 고려하는 항목들

● 프로세스(process)의 특성 <br>
● 시스템 특성 -> 목적 <br>
● 프로세스의 긴급성(urgency) -> 기준 <br>
● 프로세스 우선순위(priority) <br>
● 프로세스 총 실행시간(total service time) <br>
● ...


### 08/12 수업
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
tq:2, tq:3도 평균응답시간를 구해보면
tq:2의 평균응답시간은 ` 33 / 5 `,
tq:3의 평균응답시간은 ` 27 / 5 `이라고 구해볼수있다.


따라서 가장 효율적인 프로세스 스케줄링을 순서대로 나열하자면
첫번째는 `FCFS(26/5)` , 두번째는 `tq:3(27/5)`, 
셋번째는 `tq:1(31/5)`, 마지막번째는 `tq:2(33/5)`이다.


### 08/13 수업

*배운 내용 총 정리💡*

###### 기본 스케줄링 알고리즘
```
┌
   * FCFS (First-Come-First-Service)             Fairness
   * RR (Round-Robin)			┘  	  (공평성)

┌
   * SPN (Shortest-Process-Next)
   * SRTN (Shortest Remaining Time Next)   	Efficieney/Performance
   * HRRN (High-Response-Ratio-Next)   ┘ 	(효율성, 성능)
								
* MLQ(Multi-level Queue)
* MFQ (Multi-level Feedback Queue)
```
###### MLQ(Multi-level Queue)
```
* 작업(or 우선순위) 별 별도의 ready queue를 가짐
	
	1)최초 배정 된 queue를 벗어나지 못함
	2)각각의 queue는 자신만의 스케쥴링 기법 사용

* Queue 사이에는 우선순위 기반의 스케줄링 사용

[장점]
  * 빠른 응답시간(?)
[단점]
  * 여러 개의 Queue 관리 등 스케쥴링 overhead
  * 우선순위가 낮은 queue는 starvation 현상 발생 가능
```
###### MFQ (Multi-level Feedback Queue)
```
* 프로세스의 Queue간 이동이 허용된 MLQ
* Feedback을 통해 우선 순위 조정
	- 현재까지의 프로세서 사용 정보(패턴) 활용

* 특성
	- Dynamic priority
	- preemptive scheduling
	- Favor short burst-time processes
	- Favor I/O bounded processes
	- Improve adaptability

* 프로세스에 대한 사전 정보 없이 SPN,SRTN,HRRN
기법의 효과를 볼 수 있음

* 단점
	* 설계 및 구현이 복잡, 스케줄링 overhead가 큼
	* Starvation 문제 등
```

###### Preemptive/Non-preemptive scheduling
```
Non-preemptive scheduling(화장실)
	
 	할당받을 자원을 스스로 반납할 때까지 사용
		예) system call,I/O,Etc
	
	장점 - Context switch overhead가 적음
	단점 - 잦은 우선순위 역전, 평균 응답 시간 증가

Preemptive scheduling(응급실)
	
	타의에 의해 자원을 빼앗길 수 있음
		예) 할당 시간 종료, 우선순위가 높은 프로세스 등장
	
Context switch overhead가 큼
	Time-sharing system, real-time system 등에 적합

```
###### Priority 프로세스의 중요도
```
Static priority(정적 우선순위) (도장)
	프로세스 생성시 결정된 priority가 유지됨
	장 - 구현이 쉽고, overhead가 적음
	단 - 시스템 환경 변화에 대한 대응이 어려움

Dynamic priority(동적 우선순위) (연필과 지우개)
	프로세스의 상태 변화에 따라 priority 변경
	단 - 구현이 복잡, priority 재계산 overhead가 큼
	장 - 시스템 환경 변화에 유연한 대응 가능
```
###### *FCFS (Frist-Come-First-Service)*
```
Non-preemptive scheduling
스케줄링 기준(Criteria)
	도착 시간(ready queue 기준)
	먼저 도착한 프로세스를 먼저 처리

자원을 효율적으로 사용 가능
	- high resource utilization / why?
Batch system에 적합, interactive system에 부적합
장점 
	- 간단(구현)
단점
	- Convoy effect
		하나의 수행시간이 긴 프로세스에 의해
		다른 프로세스들이 긴 대기시간을 갖게
		되는 현상(대기시간>>실행시간)
	- 긴 평균 응답시간(response time) 

```
###### *RR (Round-Robin)*
```
* preemptive scheduling
* 스케줄링 기준(Criteria)	
	- 도착 시간 (ready queue 기준)
	- 먼저 도착한 프로세스를 먼저 처리
* 자원 사용 제한 시간(time quantum)이 있음

	- System parameter
	- 프로세스는 할당된 시간이 지나면 자원 반납
		- Timer-runout
	- 특정 프로세스의 자원 독점(monopoly) 방지
	- Context switch overhead가 큼
* 대화형, 시분할 시스템에 적합


* Time quantum이 시스템 
  성능을 결정하는 핵심요소

	- Very large (infinite) δ → FCFS
	- Very small time quantum → processor sharing
		- 사용자는 모든 프로세스가 각각의 프로세스 위에서
		  실행되는 것처럼 느낌
			- 체감 프로세서 속도 = 실제 프로세서 성능의 1/n
		- High context switch overhead
```
###### SPN (Shortest-Process-Next)
```
* Non-preemptive scheduling
* 스케줄링 기준 (Criteria)
	- 실행시간(burst time기준)
	- Burst time 가장 작은 프로세스를 먼저 처리
		- SJF(Shortest Job First) scheduling

* 장점
	- 평균 대기시간(WT) 최소화
	- 시스템 내 프로세스 수 최소화
		- 스케줄링 부하 감소, 메모리 절약 → 시스템 효율 향상
	- 많은 프로세스들에게 빠른에게 빠른 응답 시간 제공
* 단점
	- Starvation (무한대기)현상 발생
		- BT가 긴 프로세스는 자원을 할당 받지 못 할 수 있음
			- Aging등으로 해결(e.g., HRRN)
	- 정확한 실행시간을 알 수 없음
		- 실행시간 예측 기법이 필요
```
###### SRTN (Shortest Remaining Time Next)
```
* SPN의 변형
* Preemptive scheduling
	- 잔여 실행 시간이 더 적은 프로세스가 ready 상태가 되면
	  선점됨

* 장점
	- SPN의 장점 극대화
* 단점
	- 프로세스 생성시, 총 실행 시간 예측이 필요함
	- 잔여 실행을 계속 추적해야 함 = overhead
	- Context switching overhead
```
###### HRRN (High-Response-Ratio-Next)
```
* SPN의 변형
	- SPN+Aging concepts, Non-preemptive scheduling
* Aging concepts
	- 프로세스의 대기 시간(WT)을 고려하여 기회를 제공
* 스케줄링 기준 (Criteria)
	- Response ratio가 높은 프로세스 우선
* Response ratio = WT+BT/BT(응답률)
	- SPN의 장점 + Starvation 방지
	- 실행 시간 예측 기법 필요 (overhead)

```
