package cn.edu.buaa.sei.SVI.struct.core.variable.impl.baseType;

import cn.edu.buaa.sei.SVI.struct.core.variable.base.LongVariable;
import cn.edu.buaa.sei.SVI.struct.core.variable.impl.TypedVariableImpl;

public class LongVariableImpl extends TypedVariableImpl implements LongVariable{

	public LongVariableImpl(String name) throws Exception {super(name, Long.class);}

	@Override
	public void assign(Long val) throws Exception {this.val=val;}
	@Override
	public Long read() throws Exception {
		if(this.val==null)return null;
		else return (Long) this.val;
	}
}
