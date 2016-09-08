package com.rovio.math;

import java.util.Scanner;

public class MarbleRollingCalculator {

	public static void main(String[] args) {
		
		double gravity=9.8;
		
		double height1;
		double distance1;
		double height2;
		double distance2;
		double launchVelocity;
		
		Scanner scan=new Scanner(System.in);
		p("Enter the height of the first launch:");
		height1=scan.nextDouble();
		p("Enter the distance traveled on the first launch:");
		distance1=scan.nextDouble();
		launchVelocity=distance1*Math.sqrt((2*height1)/gravity);
		p("Launch velocity: "+launchVelocity);
		p("Enter the height of the second launch:");
		height2=scan.nextDouble();
		distance2=launchVelocity*Math.sqrt((2*height2)/gravity);
		p("Distance: "+distance2);
		scan.close();
	}
	
	public static void p(String s) {
		System.out.println(s);
	}

}
