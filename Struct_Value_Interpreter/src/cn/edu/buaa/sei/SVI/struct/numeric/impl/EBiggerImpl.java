package cn.edu.buaa.sei.SVI.struct.numeric.impl;

import cn.edu.buaa.sei.SVI.struct.core.CompositeStruct;
import cn.edu.buaa.sei.SVI.struct.core.extend.NumericStruct;
import cn.edu.buaa.sei.SVI.struct.numeric.logic.EBigger;

public class EBiggerImpl extends CompareOperatorImpl implements EBigger{

	protected EBiggerImpl(NumericStruct left, NumericStruct right,
			CompositeStruct container) throws Exception {
		super(left, right, container);
	}

	@Override
	public String toString(){
		return this.left.toString()+" >= "+this.right.toString();
	}
}
