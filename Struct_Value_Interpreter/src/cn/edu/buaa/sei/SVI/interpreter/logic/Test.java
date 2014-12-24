package cn.edu.buaa.sei.SVI.interpreter.logic;

import cn.edu.buaa.sei.SVI.interpreter.core.Interpreter;
import cn.edu.buaa.sei.SVI.interpreter.core.RegisterMachine;
import cn.edu.buaa.sei.SVI.manage.InterpreterRegisterMachine;
import cn.edu.buaa.sei.SVI.struct.core.variable.Bindable;
import cn.edu.buaa.sei.SVI.struct.core.variable.Variable;
import cn.edu.buaa.sei.SVI.struct.logic.LogicExpression;
import cn.edu.buaa.sei.SVI.struct.logic.LogicVariable;
import cn.edu.buaa.sei.SVI.struct.logic.impl.LogicFactory;

public class Test {

	static InterpreterRegisterMachine register;
	
	public static void main(String[] args) {
		try {
			register();
			test2();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static InterpreterRegisterMachine register() throws Exception{
		register = RegisterMachine.getRegister();
		return register;
	}

	public static void test1() throws Exception{
		LogicVariable var = LogicFactory.createLogicVariable("a");
		Variable b = var;
		Bindable a = b;
		
		System.out.println(var.getClass().getCanonicalName());
		System.out.println(b.getClass().getCanonicalName());
		System.out.println(a.getClass().getCanonicalName());
	}
	
	public static void test2() throws Exception{
		LogicVariable a = LogicFactory.createLogicVariable("a");
		LogicVariable b = LogicFactory.createLogicVariable("b");
		LogicExpression e = LogicFactory.createImplication(a, b);
		
		a.assign(false); b.assign(true);
		
		Inferencer ai = (Inferencer) register.get(a);
		Inferencer bi = (Inferencer) register.get(b);
		Inferencer ei = (Inferencer) register.get(e);
		
		System.out.println(e.toString());
		System.out.println(printInterpreter(ai));
		System.out.println(printInterpreter(bi));
		System.out.println(printInterpreter(ei));
		
		System.out.println("a: "+ai.interpret(a));
		System.out.println("b: "+bi.interpret(b));
		System.out.println("e: "+ei.interpret(e));
	}
	
	public static String printInterpreter(Interpreter interpreter){
		if(interpreter==null)return null;
		else return interpreter.getClass().getName()+"{"+interpreter.hashCode()+"}";
	}
}
