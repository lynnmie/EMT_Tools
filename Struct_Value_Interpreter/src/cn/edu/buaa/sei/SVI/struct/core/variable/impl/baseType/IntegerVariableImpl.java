package cn.edu.buaa.sei.SVI.struct.core.variable.impl.baseType;

import cn.edu.buaa.sei.SVI.struct.core.variable.base.IntegerVariable;
import cn.edu.buaa.sei.SVI.struct.core.variable.impl.TypedVariableImpl;

public class IntegerVariableImpl extends TypedVariableImpl implements IntegerVariable{

	public IntegerVariableImpl(String name) throws Exception {super(name, Integer.class);}

	@Override
	public void assign(Integer val) throws Exception {this.val=val;}
	@Override
	public Integer read() throws Exception {
		if(this.val==null)return null;
		else return (Integer) this.val;
	}

}
