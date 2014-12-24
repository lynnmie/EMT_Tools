package cn.edu.buaa.sei.SVI.struct.numeric.impl;

import cn.edu.buaa.sei.SVI.struct.core.variable.impl.TypedVariableImpl;
import cn.edu.buaa.sei.SVI.struct.numeric.RationalVariable;

public class RationalVariableImpl extends TypedVariableImpl implements RationalVariable{

	protected RationalVariableImpl(String name) throws Exception {
		super(name, Rational.class);
	}

	@Override
	public void assign(Number val) throws Exception {
		if(val==null||val instanceof Rational)this.val=val;
		else throw new Exception("Rational value required");
	}

	@Override
	public void assign(Rational val) throws Exception {
		this.val=val;
	}
	
	@Override
	public Rational read() throws Exception {
		if(this.val==null)return null;
		else return (Rational) this.val;
	}
}
