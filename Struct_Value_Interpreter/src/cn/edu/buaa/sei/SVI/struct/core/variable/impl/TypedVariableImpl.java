package cn.edu.buaa.sei.SVI.struct.core.variable.impl;

import cn.edu.buaa.sei.SVI.struct.core.variable.TypedVariable;

public abstract class TypedVariableImpl extends VariableImpl implements TypedVariable{
	Class<?> type;
	
	protected TypedVariableImpl(String name,Class<?> type) throws Exception {
		super(name);
		if(type==null)throw new Exception("Null Type Initialization");
		this.type=type;
	}

	@Override
	public void assign(Object val) throws Exception {
		if(!this.compatible(val))
			throw new ClassCastException("Invalid Assignment: "+this.type.toString()+" := "+val.getClass().toString());
		this.val=val;
	}

	@Override
	public boolean compatible(Object val) {
		if(val==null)return true;
		else return this.type.isInstance(val);}

	@SuppressWarnings("rawtypes")
	@Override
	public Class getType() {return this.type;}

}
