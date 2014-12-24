package cn.edu.buaa.sei.SVI.struct.core.variable.impl.baseType;

import cn.edu.buaa.sei.SVI.struct.core.variable.base.DoubleVariable;
import cn.edu.buaa.sei.SVI.struct.core.variable.impl.TypedVariableImpl;

public class DoubleVariableImpl extends TypedVariableImpl implements DoubleVariable{

	public DoubleVariableImpl(String name) throws Exception {super(name, Double.class);}

	@Override
	public void assign(Double val) throws Exception {this.val=val;}
	@Override
	public Double read() throws Exception {
		if(this.val==null)return null;
		else return (Double) this.val;
	}
	
}
