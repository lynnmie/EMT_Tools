package cn.edu.buaa.sei.SVI.struct.core.function.impl;

import cn.edu.buaa.sei.SVI.struct.core.function.Context;
import cn.edu.buaa.sei.SVI.struct.core.variable.ReferenceVariable;
import cn.edu.buaa.sei.SVI.struct.core.variable.Variable;
import cn.edu.buaa.sei.SVI.struct.core.variable.impl.VariableFactory;

public class Test {
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
			testAPI();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void testContext() throws Exception{
		Context top = FunctionFactory.createContext(null);
		Variable x = VariableFactory.createBoolean("x");
		Variable y = VariableFactory.createDouble("y");
		
		top.addStruct(x.getName(), x);
		top.addStruct(y.getName(),y);
		
		Context cf = FunctionFactory.createContext(top);
		Variable t = VariableFactory.createInteger("t");
		ReferenceVariable p = VariableFactory.createReference("p");
		p.refer(x);
		Variable x1 = VariableFactory.createFreeVariable("x");
		
		cf.addStruct(t.getName(), t);
		cf.addStruct(p.getName(), p);
		cf.addStruct(x1.getName(),x1);
		
		x = (Variable) cf.getStruct("x");
		y = (Variable) cf.getStruct("y");
		p = (ReferenceVariable) cf.getStruct("p");
		t = (Variable) cf.getStruct("t");
		
		x.assign("Hello, world");
		y.assign(2.55);
		p.assign(true);
		t.assign(1024);
		
		System.out.println(printVariable(x));
		System.out.println(printVariable(y));
		System.out.println(printVariable(p));
		System.out.println(printVariable(t));
		
	}
	
	public static String printVariable(Variable x) throws Exception{
		if(x==null)return null;
		else return x.getName()+": "+x.read();
	}

	public static void testAPI() throws Exception{
		
	}
	
}
