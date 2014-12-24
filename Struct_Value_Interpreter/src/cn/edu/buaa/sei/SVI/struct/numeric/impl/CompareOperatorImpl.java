package cn.edu.buaa.sei.SVI.struct.numeric.impl;

import cn.edu.buaa.sei.SVI.struct.core.CompositeStruct;
import cn.edu.buaa.sei.SVI.struct.core.Struct;
import cn.edu.buaa.sei.SVI.struct.core.extend.NumericStruct;
import cn.edu.buaa.sei.SVI.struct.logic.impl.LogicOperatorImpl;
import cn.edu.buaa.sei.SVI.struct.numeric.logic.CompareOperator;

public class CompareOperatorImpl extends LogicOperatorImpl implements CompareOperator{

	NumericStruct left,right;
	
	protected CompareOperatorImpl(NumericStruct left, NumericStruct right, CompositeStruct container) throws Exception {
		super(container);
		
		if(left==null||right==null)
			throw new Exception("Null Operands are invalid");
		this.left=left; this.right=right;
		
		this.addChildStruct(this.left);
		this.addChildStruct(this.right);
	}

	@Override
	public void setOperands(Struct left, Struct right) throws Exception {
		if(left==null||right==null)
			throw new Exception("Null Operands are invalid");
		if(!(left instanceof NumericStruct)||!(right instanceof NumericStruct))
			throw new Exception("Operands of CompareOperator must be NumericStruct");
		
		NumericStruct t1 = (NumericStruct) left;
		NumericStruct t2 = (NumericStruct) right;
		this.setOperands(t1, t2);
	}
	@Override
	public void setOperands(NumericStruct left, NumericStruct right)
			throws Exception {
		if(left==null||right==null)
			throw new Exception("Null Operands are invalid");

		this.removeChildStruct(this.right);
		this.removeChildStruct(this.left);
		
		this.left=left;
		this.right=right;
		
		this.addChildStruct(this.left);
		this.addChildStruct(this.right);
	}

	@Override
	public int getDimension() {return 2;}

	@Override
	public NumericStruct getLeftOperand() {return this.left;}
	@Override
	public NumericStruct getRightOperand() {return this.right;}

}
