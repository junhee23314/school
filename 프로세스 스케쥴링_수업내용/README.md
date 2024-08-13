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
![수업내용문제](https://github.com/junhee23314/school/blob/main/%ED%94%84%EB%A1%9C%EC%84%B8%EC%8A%A4%20%EC%8A%A4%EC%BC%80%EC%A5%B4%EB%A7%81_%EC%88%98%EC%97%85%EB%82%B4%EC%9A%A9/2024-0812_%EC%88%98%EC%97%85%EB%82%B4%EC%9A%A9%EB%AC%B8%EC%A0%9C.png)

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
