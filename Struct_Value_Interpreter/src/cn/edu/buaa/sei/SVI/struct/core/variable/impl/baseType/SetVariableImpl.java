package cn.edu.buaa.sei.SVI.struct.core.variable.impl.baseType;

import java.util.Set;

import cn.edu.buaa.sei.SVI.struct.core.variable.base.SetVariable;
import cn.edu.buaa.sei.SVI.struct.core.variable.impl.TypedVariableImpl;

public class SetVariableImpl extends TypedVariableImpl implements SetVariable{

	public SetVariableImpl(String name) throws Exception {
		super(name, Set.class);
	}

	@SuppressWarnings("rawtypes")
	@Override
	public void assign(Set val) throws Exception {
		this.val=val;
	}
	@SuppressWarnings("rawtypes")
	@Override
	public Set read() throws Exception {
		if(this.val==null)return null;
		else return (Set) this.val;
	}
	
}
