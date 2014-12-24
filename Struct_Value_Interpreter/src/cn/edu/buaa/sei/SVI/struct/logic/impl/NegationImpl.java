package cn.edu.buaa.sei.SVI.struct.logic.impl;

import cn.edu.buaa.sei.SVI.struct.core.CompositeStruct;
import cn.edu.buaa.sei.SVI.struct.core.Struct;
import cn.edu.buaa.sei.SVI.struct.core.extend.LogicStruct;
import cn.edu.buaa.sei.SVI.struct.logic.Negation;

public class NegationImpl extends LogicOperatorImpl implements Negation{

	LogicStruct operand;
	
	protected NegationImpl(LogicStruct operand,CompositeStruct container) throws Exception {
		super(container);
		if(operand==null)
			throw new Exception("Null Operand is invalid");

		this.setOperand(operand);
	}

	@Override
	public void setOperand(Struct operand) throws Exception {
		if(operand==null)
			throw new Exception("Null Operand is invalid");
		if(!(operand instanceof LogicStruct))
			throw new Exception("Operand of Negation must be LogicStruct");
		
		LogicStruct t = (LogicStruct) operand;
		this.setOperand(t);
	}
	@Override
	public int getDimension() {return 1;}
	@Override
	public LogicStruct getOperand() {return this.operand;}
	@Override
	public void setOperand(LogicStruct operand) throws Exception {
		if(operand==null)
			throw new Exception("Null Operand is invalid");
		
		if(this.operand!=null)this.container.removeChildStruct(this.operand);
		
		this.operand=operand;
		
		this.container.addChildStruct(operand);
	}

	@Override
	public String toString(){
		return "!"+this.operand.toString();
	}
}
