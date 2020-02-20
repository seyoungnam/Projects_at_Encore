package model;

import java.util.Scanner;

public class InOut {
	InOut strInOut = new InOut();
	public static String strInOut() {
		Scanner scan = new Scanner(System.in);
		String input = scan.nextLine();
		return input;
	}
	
	InOut intInOut = new InOut();
	public static int intInOut() {
		Scanner scan = new Scanner(System.in);
		int input = scan.nextInt();
		return input;
	}
	
	InOut engInOut = new InOut();
	public static String engInOut() {
		Scanner scan = new Scanner(System.in);
		String input = scan.nextLine();
		return input;
	}
	
	InOut korInOut = new InOut();
	public static String korInOut() {
		Scanner scan = new Scanner(System.in);
		String input = scan.nextLine();
		return input;
	}
	
	InOut dateInOut = new InOut();
	public static int dateInOut() {
		Scanner scan = new Scanner(System.in);
		int input = scan.nextInt();
		return input;
	}
}
