package com.rovio.math;

import java.util.Scanner;

public class VectorAdder {

	public static void main(String[] args) {
		Scanner scan=new Scanner(System.in);
		double l1=scan.nextDouble();
		double a1=scan.nextDouble();
		double l2=scan.nextDouble();
		double a2=scan.nextDouble();
		
		double x1=Math.cos(a1*Math.PI/180)*l1;
		double y1=Math.sin(a1*Math.PI/180)*l1;
		double x2=Math.cos(a2*Math.PI/180)*l2;
		double y2=Math.sin(a2*Math.PI/180)*l2;
		
		double xt=x1+x2;
		double yt=y1+y2;
		
		double lf=Math.sqrt(xt*xt+yt*yt);
		double af=Math.atan(yt/xt)*180/Math.PI;
		
		System.out.println("Ax="+x1);
		System.out.println("Ay="+y1);
		System.out.println("Bx="+x2);
		System.out.println("By="+y2);
		System.out.println("Rx="+xt);
		System.out.println("Ry="+yt);
		System.out.println("R="+lf+" @ "+af);
	}

}
