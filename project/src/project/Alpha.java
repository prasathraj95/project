package project;

public class Alpha {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		int alpha =65;
		for(int i=0; i<=67; i++) {
			for(int j=0; j<=i; j++)
			{
				System.out.print((char)(alpha+j) + " ");
				
			}
			System.out.println();
		}
	}

}
