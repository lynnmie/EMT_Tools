package cn.edu.buaa.sei.SVI.struct.core.variable.impl.baseType;


import java.util.List;

import cn.edu.buaa.sei.SVI.struct.core.variable.base.ListVariable;
import cn.edu.buaa.sei.SVI.struct.core.variable.impl.TypedVariableImpl;

public class ListVariableImpl extends TypedVariableImpl implements ListVariable{

	public ListVariableImpl(String name) throws Exception {super(name, List.class);}

	@SuppressWarnings("rawtypes")
	@Override
	public void assign(List val) throws Exception {this.val=val;}
	@SuppressWarnings("rawtypes")
	@Override
	public List read() throws Exception {
		if(this.val==null)return null;
		else return (List) this.val;
	}
}
