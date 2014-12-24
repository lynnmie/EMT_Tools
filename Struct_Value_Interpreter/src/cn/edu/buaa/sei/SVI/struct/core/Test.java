package cn.edu.buaa.sei.SVI.struct.core;

import cn.edu.buaa.sei.SVI.struct.core.variable.impl.VariableFactory;

public class Test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
			test1();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void test1() throws Exception{
		CompositeStruct a = new StructArray();
		a.addChildStruct(VariableFactory.createBoolean("x1"));
		a.addChildStruct(VariableFactory.createCharacter("x2"));
		a.addChildStruct(VariableFactory.createCharacter("x3"));
		a.addChildStruct(VariableFactory.createCharacter("x4"));
		a.addChildStruct(VariableFactory.createCharacter("x5"));
		a.addChildStruct(VariableFactory.createCharacter("x6"));
		a.addChildStruct(VariableFactory.createCharacter("x7"));
		a.addChildStruct(VariableFactory.createCharacter("x8"));
		a.addChildStruct(VariableFactory.createBoolean("x1"));
		a.addChildStruct(VariableFactory.createCharacter("x2"));
		a.addChildStruct(VariableFactory.createCharacter("x3"));
		a.addChildStruct(VariableFactory.createCharacter("x4"));
		a.addChildStruct(VariableFactory.createCharacter("x5"));
		a.addChildStruct(VariableFactory.createCharacter("x6"));
		a.addChildStruct(VariableFactory.createCharacter("x7"));
		a.addChildStruct(VariableFactory.createCharacter("x8"));
		a.addChildStruct(VariableFactory.createBoolean("x1"));
		a.addChildStruct(VariableFactory.createCharacter("x2"));
		a.addChildStruct(VariableFactory.createCharacter("x3"));
		a.addChildStruct(VariableFactory.createCharacter("x4"));
		a.addChildStruct(VariableFactory.createCharacter("x5"));
		a.addChildStruct(VariableFactory.createCharacter("x6"));
		a.addChildStruct(VariableFactory.createCharacter("x7"));
		a.addChildStruct(VariableFactory.createCharacter("x8"));
		
		
		Struct[] children = a.getChildrenStructs();
		for(int i=0;i<a.getChildrenStructSize();i++)
			System.out.println(children[i].hashCode());
		System.out.println();
		
		a.removeChildStruct(children[0]);
		a.removeChildStruct(children[0]);
		a.removeChildStruct(children[1]);
		
		for(int i=0;i<a.getChildrenStructSize();i++)
			System.out.println(children[i].hashCode());
		System.out.println(children.length+"\n");
	}
	
}
