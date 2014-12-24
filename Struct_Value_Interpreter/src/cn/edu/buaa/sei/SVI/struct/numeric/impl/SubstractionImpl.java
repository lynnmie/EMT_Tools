package cn.edu.buaa.sei.SVI.struct.numeric.impl;

import cn.edu.buaa.sei.SVI.struct.core.CompositeStruct;
import cn.edu.buaa.sei.SVI.struct.core.extend.NumericStruct;
import cn.edu.buaa.sei.SVI.struct.numeric.Substraction;

public class SubstractionImpl extends BinaryNumericOperatorImpl implements Substraction{
	SubstractionImpl(NumericStruct left, NumericStruct right,
			CompositeStruct container) throws Exception {
		super(left, right, container);
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString(){
		return this.left.toString()+" - "+this.right.toString();
	}
}
