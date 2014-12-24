package cn.edu.buaa.sei.SVI.struct.numeric.impl;

import cn.edu.buaa.sei.SVI.struct.core.CompositeStruct;
import cn.edu.buaa.sei.SVI.struct.core.extend.NumericStruct;
import cn.edu.buaa.sei.SVI.struct.numeric.Mod;

public class ModImpl extends BinaryNumericOperatorImpl implements Mod{

	ModImpl(NumericStruct left, NumericStruct right, CompositeStruct container)
			throws Exception {
		super(left, right, container);
	}

	@Override
	public String toString(){
		return this.left.toString()+" +% "+this.right.toString();
	}
}
