package cn.edu.buaa.sei.SVI.struct.group.impl;

import cn.edu.buaa.sei.SVI.struct.core.CompositeStruct;
import cn.edu.buaa.sei.SVI.struct.core.Struct;
import cn.edu.buaa.sei.SVI.struct.core.extend.GroupStruct;
import cn.edu.buaa.sei.SVI.struct.group.Intersection;

public class IntersectionImpl extends GroupOperatorImpl implements Intersection{
	
	GroupStruct[] operands;

	IntersectionImpl(GroupStruct[] operands,CompositeStruct container) throws Exception {
		super(container);
		this.setOperands(operands);
	}

	@Override
	public void setOperands(Struct[] operands) throws Exception {
		if(operands==null)
			throw new Exception("Null Operations are invalid");
		if(operands.length<2)
			throw new Exception("At least 2 operands are required");
		
		GroupStruct[] xs = new GroupStruct[operands.length];
		for(int i=0;i<operands.length;i++){
			if(operands[i]==null)
				throw new Exception("operands["+i+"] is null");
			else if(!(operands[i] instanceof GroupStruct))
				throw new Exception("operands["+i+"] is not GroupStruct");
			else
				xs[i]=(GroupStruct) operands[i];
		}
		
		this.setOperands(xs);
	}
	@Override
	public GroupStruct[] getOperands() {return this.operands;}
	@Override
	public void setOperands(GroupStruct[] operands) throws Exception {
		if(operands==null)
			throw new Exception("Null Operations are invalid");
		if(operands.length<2)
			throw new Exception("At least 2 operands are required");
		
		for(int i=0;i<operands.length;i++)
			if(operands[i]==null)
				throw new Exception("operands["+i+"] is null");
		
		this.clearOperands();
		this.operands=operands;
		this.addOperands();
	}

	protected void clearOperands() throws Exception{
		if(this.operands==null)return;
		
		for(int i=this.operands.length-1;i>=0;i--)
			this.container.removeChildStruct(this.operands[i]);
	}
	protected void addOperands() throws Exception{
		if(this.operands==null)return;
		
		for(int i=0;i<this.operands.length;i++)
			this.container.addChildStruct(this.operands[i]);
	}
	
	@Override
	public String toString(){
		StringBuilder code = new StringBuilder();
		
		for(int i=0;i<this.operands.length;i++){
			code.append(operands[i].toString());
			if(i!=this.operands.length-1)
				code.append(" * ");
		}
		
		return code.toString();
	}
	
}
