package cn.edu.buaa.sei.SVI.struct.core.variable.impl;

import cn.edu.buaa.sei.SVI.struct.core.variable.ReferenceVariable;
import cn.edu.buaa.sei.SVI.struct.core.variable.Variable;

public class ReferenceVariableImpl implements ReferenceVariable{
	protected String name;
	protected Variable ref;
	
	ReferenceVariableImpl(String name,Variable ref){
		this.name=name; this.ref=ref;
	}

	@Override
	public String getName() {return this.name;}

	@Override
	public Object read() throws Exception {
		if(this.ref==null)
			throw new Exception("Null refer is invalid to read its value.");
		return this.ref.read();
	}

	@Override
	public void assign(Object val) throws Exception {
		if(this.ref==null)
			throw new Exception("Null refer is invalid to write its referring value.");
		this.ref.assign(val);
	}

	@Override
	public void refer(Variable variable){
		this.ref=variable;
	}
	@Override
	public Variable getRefer(){return this.ref;}

}
