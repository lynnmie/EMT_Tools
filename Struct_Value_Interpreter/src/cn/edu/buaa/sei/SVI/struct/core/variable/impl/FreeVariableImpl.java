package cn.edu.buaa.sei.SVI.struct.core.variable.impl;

import cn.edu.buaa.sei.SVI.struct.core.variable.FreeVariable;

public class FreeVariableImpl extends VariableImpl implements FreeVariable{

	protected FreeVariableImpl(String name) {super(name);}
	@Override
	public void assign(Object val) throws Exception {this.val=val;}
}
