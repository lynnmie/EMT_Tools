package cn.edu.buaa.sei.SVI.struct.logic.test;

import cn.edu.buaa.sei.SVI.interpreter.core.RegisterMachine;
import cn.edu.buaa.sei.SVI.interpreter.logic.Inferencer;
import cn.edu.buaa.sei.SVI.manage.InterpreterRegisterMachine;
import cn.edu.buaa.sei.SVI.struct.core.Struct;

public class Test2 {

	static Constructor constructor;
	static InterpreterRegisterMachine register=RegisterMachine.getRegister();
	
	public static void main(String[] args) {
		try {
			register();
			Struct struct = create();
			printStruct(struct);
			Boolean result = interpret(struct);
			System.out.println("Result: "+result);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	public static void register() throws Exception{
	}
	
	public static Struct create() throws Exception{
		return constructor.create();
	}
	public static void printStruct(Struct struct){
		System.out.println(struct.toString());
	}
	public static Boolean interpret(Struct struct)throws Exception{
		Inferencer inferencer = (Inferencer) register.get(struct);
		System.out.println("Getting Inferencer: "+inferencer.getClass().getName()+" {"+inferencer.hashCode()+"}");
		return (Boolean) inferencer.interpret(struct);
	}
	
}
