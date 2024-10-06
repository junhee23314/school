package market;

import java.util.Scanner;

public class MarketMain {

	public static void main(String[] args) {
		Market 마켓 = new Market("성일마켓");
		Customer 손님 = new Customer("김준희", 10000);
		Scanner sc = new Scanner(System.in);
		boolean 진행 = true;
		
		while(진행) {
			System.out.println("*****************************");
			System.out.println("*****************************");
			System.out.println("*           성일마켓          *");
			System.out.println("*****************************");
			System.out.println("*****************************");
			System.out.printf("당신은 누구신가요 (1)관리자  (2)호구 >");
			
			if(sc.nextInt() == 1) {
				// 관리자
				while(true) {
					System.out.println();
					System.out.println("** 관리자모드 **");
					System.out.printf("(1)재고관리 (2)재고현황 (3)종료> ");
					
					int 관리자선택지 = sc.nextInt();
					
					if(관리자선택지==1) {
						마켓.재고관리();
					} else if(관리자선택지==2) {
						마켓.재고현황();
					} else {
						System.out.println();
						break;
					}					
				}
			}else {
				// 호구
				while(true) {
					System.out.println();
					System.out.println("** 고객모드 **");
					System.out.printf("(1)구매하기 (2)자기소개 (3)종료> ");
					
					int 호구선택지 = sc.nextInt();
					
					if(호구선택지==1) {
						마켓.판매(손님);
					} else if(호구선택지==2) {
						손님.자기소개();
					} else {
						System.out.println();
						break;
					}	
				}
			}
			
		}
		
		
		
		
	}

}
