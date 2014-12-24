package cn.edu.buaa.sei.SVI.struct.core.variable.impl.baseType;

import cn.edu.buaa.sei.SVI.struct.core.variable.base.StringVariable;
import cn.edu.buaa.sei.SVI.struct.core.variable.impl.TypedVariableImpl;

public class StringVariableImpl extends TypedVariableImpl implements StringVariable{

	public StringVariableImpl(String name) throws Exception {super(name, String.class);}

	@Override
	public void assign(String val) throws Exception {this.val=val;}
	@Override
	public String read() throws Exception {
		if(this.val==null)return null;
		else return (String) this.val;
	}

}
