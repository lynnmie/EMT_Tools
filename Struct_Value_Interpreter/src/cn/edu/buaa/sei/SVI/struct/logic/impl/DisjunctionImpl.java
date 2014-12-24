package cn.edu.buaa.sei.SVI.struct.logic.impl;

import cn.edu.buaa.sei.SVI.struct.core.CompositeStruct;
import cn.edu.buaa.sei.SVI.struct.core.Struct;
import cn.edu.buaa.sei.SVI.struct.core.extend.LogicStruct;
import cn.edu.buaa.sei.SVI.struct.logic.Disjunction;

public class DisjunctionImpl extends LogicOperatorImpl implements Disjunction{
	
	LogicStruct[] operands;

	protected DisjunctionImpl(LogicStruct[] operands,CompositeStruct container) throws Exception {
		super(container);
		
		if(operands==null)
			throw new Exception("Null Operands are invalid");
		this.operands=operands;
		this.addChildren();
	}
	
	@Override
	public void setOperands(Struct[] operands) throws Exception {
		if(operands==null)throw new Exception("Null operands is invalid");
		if(this.operands.length<2)throw new Exception("Conjunction requires at least 2 operands");
		
		LogicStruct[] ros = new LogicStruct[operands.length];
		
		for(int i=0;i<operands.length;i++){
			if(!(operands[i] instanceof LogicStruct))
				throw new Exception("Operand of disjunction must be LogicStructr");
			ros[i] = (LogicStruct) operands[i];
		}
		
		this.setOperands(ros);
	}
	@Override
	public LogicStruct[] getOperands() {
		return this.operands;
	}
	@Override
	public void setOperands(LogicStruct[] operands) throws Exception {
		if(operands==null)throw new Exception("Null operands is invalid");
		if(this.operands.length<2)throw new Exception("Conjunction requires at least 2 operands");
		
		this.clearOperands();
		
		this.operands=operands;
		
		this.addChildren();
	}
	
	protected void clearOperands() throws Exception{
		for(int i=0;i<this.operands.length;i++)
			this.container.removeChildStruct(this.operands[i]);
	}
	protected void addChildren() throws Exception{
		for(int i=0;i<this.operands.length;i++)
			this.container.addChildStruct(this.operands[i]);
	}
	
	@Override
	public String toString(){
		StringBuilder code = new StringBuilder();
		
		for(int i=0;i<this.operands.length;i++){
			code.append(this.operands[i].toString());
			if(i!=this.operands.length-1)
				code.append("||");
		}
		
		return code.toString();
	}
}
