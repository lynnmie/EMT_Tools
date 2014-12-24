package cn.edu.buaa.sei.SVI.struct.numeric.impl;

import cn.edu.buaa.sei.SVI.struct.core.CompositeStruct;
import cn.edu.buaa.sei.SVI.struct.core.Struct;
import cn.edu.buaa.sei.SVI.struct.core.extend.NumericStruct;
import cn.edu.buaa.sei.SVI.struct.numeric.BinaryNumericOperator;

public abstract class BinaryNumericOperatorImpl extends NumericOperatorImpl implements BinaryNumericOperator{

	NumericStruct left,right;
	/**
	 * Children: [left,right]
	 * */
	BinaryNumericOperatorImpl(NumericStruct left,NumericStruct right,CompositeStruct container) throws Exception {
		super(container);
		if(left==null||right==null)
			throw new Exception("Null Operands are invalid");
		
		this.left=left;
		this.right=right;
		this.container.addChildStruct(left);
		this.container.addChildStruct(right);
	}
	
	@Override
	public void setOperands(Struct left, Struct right) throws Exception {
		if(left==null||right==null)
			throw new Exception("Null Operands is invalid");
		if(!(left instanceof NumericStruct)||!(right instanceof NumericStruct))
			throw new Exception("NumericStruct required");
		
		this.container.removeChildStruct(this.left);
		this.container.removeChildStruct(this.right);
		
		this.left=(NumericStruct) left;
		this.right=(NumericStruct) right;
		
		this.container.addChildStruct(this.left);
		this.container.addChildStruct(this.right);
	}
	@Override
	public int getDimension() {return 2;}
	@Override
	public NumericStruct getLeftOperand() {return this.left;}
	@Override
	public NumericStruct getRightOperand() {return this.right;}
	@Override
	public void setOperands(NumericStruct left, NumericStruct right)
			throws Exception {
		if(left==null||right==null)
			throw new Exception("Null Operands is invalid");
		
		this.container.removeChildStruct(this.left);
		this.container.removeChildStruct(this.right);
		
		this.left=left;
		this.right=right;
		
		this.container.addChildStruct(this.left);
		this.container.addChildStruct(this.right);
	}

}
