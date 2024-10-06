package sw0409;

public class Change10to2 {

	public static void main(String[] args) {
		int num = 18;
		int[] arr = new int[10];

		arr[0] = num % 2; // arr[0] = 0
		num = num / 2; // num = 9
		
		arr[1] = num % 2; // arr[1] = 1
		num = num / 2; // num = 4

		arr[2] = num % 2; // arr[2] = 0
		num = num / 2; // num = 2

		arr[3] = num % 2; // arr[3] = 0
		num = num / 2; // num = 1

		arr[4] = num % 2; // arr[4] = 1
		num = num / 2; // num = 0

		System.out.print(arr[4]);
		System.out.print(arr[3]);
		System.out.print(arr[2]);
		System.out.print(arr[1]);
		System.out.print(arr[0]);
		
		
	}

}
