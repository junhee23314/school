package market;

public class Customer {
	private String 이름;
	private int 돈;
	private Item[] 장바구니;
	private int 물건개수;
	
	public Customer(String 이름, int 돈) {
		this.이름 = 이름;
		this.돈 = 돈;
		this.장바구니 = new Item[2];
		this.물건개수 = 0;
	}

	public String get이름() {
		return 이름;
	}
	public void set이름(String 이름) {
		this.이름 = 이름;
	}
	public int get돈() {
		return 돈;
	}
	public void set돈(int 돈) {
		this.돈 = 돈;
	}
	public Item[] get장바구니() {
		return 장바구니;
	}
	public void set장바구니(Item 장바구니) {
		물건개수++;
		this.장바구니[물건개수] = 장바구니;
	}

	void 자기소개() {
		System.out.printf("이름: %s  돈: %d \n", 이름, 돈);
		System.out.printf("장바구니: ");
		for(Item i : 장바구니) {
			if(i==null) {
				System.out.print(">");
				break;
			}
			System.out.printf("%d.%s  ", i.물건번호, i.get물건명());
		}
		System.out.println();
	}
}
