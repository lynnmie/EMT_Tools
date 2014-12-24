package cn.edu.buaa.sei.SVI.struct.core.variable.impl.baseType;

import cn.edu.buaa.sei.SVI.struct.core.variable.base.BooleanVariable;
import cn.edu.buaa.sei.SVI.struct.core.variable.impl.TypedVariableImpl;

public class BooleanVariableImpl extends TypedVariableImpl implements BooleanVariable{

	public BooleanVariableImpl(String name) throws Exception {super(name, Boolean.class);}

	@Override
	public void assign(Boolean val) throws Exception {this.val=val;}
	@Override
	public Boolean read() throws Exception{
		if(this.val==null)return null;
		else return (Boolean) this.val;
	}
	

}
