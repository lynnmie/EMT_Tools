package cn.edu.buaa.sei.SVI.struct.logic.impl;

import cn.edu.buaa.sei.SVI.struct.core.CompositeStruct;
import cn.edu.buaa.sei.SVI.struct.core.Struct;
import cn.edu.buaa.sei.SVI.struct.core.extend.LogicStruct;
import cn.edu.buaa.sei.SVI.struct.logic.Implication;

public class ImplicationImpl extends LogicOperatorImpl implements Implication{

	LogicStruct left,right;
	
	protected ImplicationImpl(LogicStruct left,LogicStruct right,CompositeStruct container) throws Exception {
		super(container);
		if(left==null||right==null)
			throw new Exception("Null Operands are invalide");
		this.left=left;
		this.right=right;
		
		this.container.addChildStruct(left);
		this.container.addChildStruct(right);
	}

	@Override
	public void setOperands(Struct left, Struct right) throws Exception {
		if(left==null||right==null)
			throw new Exception("Null Operands are invalid");
		if(!(left instanceof LogicStruct)||!(right instanceof LogicStruct))
			throw new Exception("Operands of Implication must be LogicStruct");
		
		LogicStruct t1 = (LogicStruct) left;
		LogicStruct t2 = (LogicStruct) right;
		this.setOperands(t1, t2);
	}

	@Override
	public int getDimension() {return 2;}

	@Override
	public LogicStruct getLeftOperand() {return this.left;}

	@Override
	public LogicStruct getRightOperand() {return this.right;}

	@Override
	public void setOperands(LogicStruct left, LogicStruct right)
			throws Exception {
		if(left==null||right==null)
			throw new Exception("Null Operands are invalid");
		
		this.container.removeChildStruct(this.left);
		this.container.removeChildStruct(this.right);
		
		this.left=left;
		this.right=right;
		
		this.container.addChildStruct(left);
		this.container.addChildStruct(right);
	}

	@Override
	public String toString(){
		return this.left.toString()+"-->"+this.right.toString();
	}
}
