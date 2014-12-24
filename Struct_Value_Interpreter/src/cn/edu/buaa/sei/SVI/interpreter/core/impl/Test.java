package cn.edu.buaa.sei.SVI.interpreter.core.impl;

import java.util.Random;

public class Test {

	static int MAX = 16;
	static ContainerRunner[] runners = new ContainerRunner[MAX];
	static Thread[] threads = new Thread[MAX];

	static Random r = new Random();
	
	public static void main(String[] args) {
		
		Thread t = new Thread(new Runnable(){
			@Override
			public void run() {
				for(int i=0;i<MAX;i++){
					runners[i] = new ContainerRunner(r.nextDouble());
					threads[i] = new Thread(runners[i],"THREAD#"+i);
					threads[i].start();
				}
			}
		});
		t.start();
		try {
			t.join();
			Thread.sleep(200);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		for(int i=0;i<MAX;i++)
			System.out.println(threads[i].getName()+": "+threads[i].isAlive());
		
	}

	static void test1(){
		for(int i=0;i<MAX;i++){
			runners[i] = new ContainerRunner(r.nextDouble());
			threads[i] = new Thread(runners[i],"THREAD#"+i);
			threads[i].start();
		}
	}
	
	static class ContainerRunner implements Runnable{
		
		Double x,y;
		
		public ContainerRunner(double x){
			this.x=x;
		}
		
		public Double getInput(){
			return this.x;
		}
		public Double getResult(){
			return this.y;
		}

		@Override
		public void run() {
			try {
				Thread.sleep(1000);
				y = Math.log(this.x*2.0);
			} catch (InterruptedException e) {
				y=0.0;
			}
		}
		
	}
	
}
