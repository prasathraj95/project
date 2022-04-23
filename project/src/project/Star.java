package project;

import java.util.Scanner;

public class Star {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
Scanner sc =new Scanner(System.in);
System.out.println("enter the number");
int num=sc.nextInt();
		for(int i=0; i<=num; i++) {
			for(int j=num-1; j>=i; j--)
			{
				System.out.print(" ");
				
			}
			for(int k=1; k<=i; k++) {
				System.out.print(" *");
			}
			System.out.println();
		
//		for(int i=0; i<=num; i++) {
//			for(int j=num; j>i; j--)
//			{
//				System.out.print("*");
//			}
//			System.out.println();
//		}
	}

}}
