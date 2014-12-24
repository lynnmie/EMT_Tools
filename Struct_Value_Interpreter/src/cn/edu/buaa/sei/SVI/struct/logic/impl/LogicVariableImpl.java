package cn.edu.buaa.sei.SVI.struct.logic.impl;

import cn.edu.buaa.sei.SVI.struct.core.variable.impl.TypedVariableImpl;
import cn.edu.buaa.sei.SVI.struct.logic.LogicVariable;

public class LogicVariableImpl extends TypedVariableImpl implements LogicVariable{

	protected LogicVariableImpl(String name) throws Exception {
		super(name, Boolean.class);
	}

	@Override
	public void assign(Boolean val) throws Exception {this.val=val;}
	@Override
	public Boolean read() throws Exception {
		if(this.val==null)return null;
		else return (Boolean) this.val;
	}

}
