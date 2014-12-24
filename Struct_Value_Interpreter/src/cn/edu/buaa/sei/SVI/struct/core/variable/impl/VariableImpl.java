package cn.edu.buaa.sei.SVI.struct.core.variable.impl;

import cn.edu.buaa.sei.SVI.struct.core.variable.Variable;

public abstract class VariableImpl implements Variable{
	protected String name;
	protected Object val;
	
	protected VariableImpl(String name){this.name=name;}
	
	@Override
	public Object read() throws Exception {return this.val;}

	@Override
	public String getName() {return this.name;}
	
	@Override
	public String toString(){return this.name;}

}
