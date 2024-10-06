package market;

import java.util.Scanner;

public class Market {
	String 마켓이름 = "집중해주세요";
	Item[] 물건 = new Item[5];
	Scanner sc = new Scanner(System.in);
	
	public Market(String 마켓이름) {
		this.마켓이름 = 마켓이름;

		물건[0] = new Item("사과", 2000, 20);
		물건[1] = new Item("오렌지", 1000, 100);
		물건[2] = new Item("생선", 5000, 30);
	}

	void 판매(Customer 손님){
		System.out.println("물건구매 페이지입니다!");
		System.out.printf("구매할 물건을 선택하세요.");
		
		for(Item i : 물건) {
			if(i==null) {
				System.out.print(">");
				break;
			}
			System.out.printf("%d.%s  ", i.물건번호, i.get물건명());
		}
		// 1.사과  2.오렌지  3.생선
		int 구매번호 = sc.nextInt();
		
		if(구매번호 <= Item.물건명번호) { // 1, 2, 3일때만 만족
			// 몇개를 살지 입력받긔
			Item 구매물건 = 물건[구매번호-1];
			
			System.out.printf("%s 몇개 살건데? > ", 구매물건.get물건명());
			int 몇개 = sc.nextInt();
//			구매물건 = Item("사과", 2000, 20);
			int 금액 = 구매물건.get가격() * 몇개;
			// 사과의 단가*개수가 돈보다 작은지를 판단
			
			if(금액 <= 손님.get돈()) { // 작다. -> 살 수 있음 ->
			// 마켓의 사과는 빼고 
				int 새로운재고 = 구매물건.get재고() - 몇개;
				구매물건.set재고(새로운재고);
			// 고객의 장바구니에 사과 추가
				CItem 산물건 = new CItem(구매물건.get물건명(), 구매물건.get가격(), 몇개);
				Item.물건명번호--;
				
				손님.set장바구니(산물건);
				손님.set돈(손님.get돈()-금액);
				
				
			} else {
				System.out.println("돈없으면 나가세요~");				
			}
		}else {
			System.out.println("물건명 번호를 잘못 입력하셨습니다.");
		}
	}
	
	void 재고관리(){
		System.out.println("재고관리 페이지입니다!");
		boolean 진행 = true;
		int 메뉴;
		
		while(진행) {
			System.out.print("메뉴를 선택하세요.(1)재고현황, (2)재고수정, (3)종료 > ");
			메뉴 = sc.nextInt();
			
			switch (메뉴) {
			case 1: {
				재고현황();
				break;
			}
			case 2: {
				System.out.print("수정할 물건을 선택하세요.\n");
				
				for(Item i : 물건) {
					if(i==null) {
						System.out.print(">");
						break;
					}
					System.out.printf("%d.%s  ", i.물건번호, i.get물건명());
				}
				// 1.사과  2.오렌지  3.생선  > 3
				메뉴 = sc.nextInt();

//				물건[0] = new Item("사과", 2000, 20);
//				물건[1] = new Item("오렌지", 1000, 100);
//				물건[2] = new Item("생선", 5000, 30);
				Item 수정물건 = 물건[메뉴-1];
				
				System.out.printf("< %s > 수정할 항목을 선택하세요. >", 수정물건.get물건명());	
				System.out.printf("(1)물건명 (2)가격 (3)재고 (4)종료 >");
				int 수정항목 = sc.nextInt();
				
				switch(수정항목) {
				case 1:
					System.out.printf("물건명의 현재값: %s, 수정할 물건명 > ", 수정물건.get물건명());
					String 수정할물건명 = sc.next();
					수정물건.set물건명(수정할물건명);
					break;
				case 2:
					System.out.printf("가격의 현재값: %d, 수정할 가격 > ", 수정물건.get가격());
					수정물건.set가격(sc.nextInt());
					break;					
				case 3:
					System.out.printf("재고의 현재값: %d, 수정할 재고 > ", 수정물건.get재고());
					수정물건.set재고(sc.nextInt());
					break;
				case 4:
					System.out.println("수정을 종료합니다.");
					break;
				default:
					System.out.println("값 오류");
				}				
			}
			case 3: {
				진행 = false;
				break;
			}
			default:
				System.out.println("다시 입력하시죠");
			}			
		}
	}
	
	void 재고현황(){
		System.out.println(마켓이름+" 현재 재고는...");
		System.out.println(Item.물건명번호+" 가지의 물건이 있습니다.");
		
		for(Item i : 물건) {
			// i = 0 -> Item("사과", 2000, 20)
			// i = 1 -> Item("오렌지", 1000, 100)
			// i = 2 -> Item("생선", 5000, 30)
			// i = 3 -> null
			// i = 4 -> null
			if(i==null) break;
			System.out.println(i.물건번호);
			System.out.println(i.get물건명());
			System.out.println(i.get가격());
			System.out.println(i.get재고());			
		}
		
	}
}