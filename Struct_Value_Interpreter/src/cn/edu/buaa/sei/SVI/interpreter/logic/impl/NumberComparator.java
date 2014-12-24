package cn.edu.buaa.sei.SVI.interpreter.logic.impl;

public class NumberComparator {
	static double E = Math.pow(2, -15);
	
	public static int compare(Number x,Number y){
		double xv = x.doubleValue();
		double yv = y.doubleValue();
		
		double ex = xv-yv;
		if(ex>E)return 1;
		else if(ex<-E)return -1;
		else return 0;
	}
}
