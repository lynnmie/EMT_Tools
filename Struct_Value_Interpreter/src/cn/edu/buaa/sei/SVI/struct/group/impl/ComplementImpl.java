package cn.edu.buaa.sei.SVI.struct.group.impl;

import cn.edu.buaa.sei.SVI.struct.core.CompositeStruct;
import cn.edu.buaa.sei.SVI.struct.core.Struct;
import cn.edu.buaa.sei.SVI.struct.core.extend.GroupStruct;
import cn.edu.buaa.sei.SVI.struct.group.Complement;

public class ComplementImpl extends GroupOperatorImpl implements Complement{
	
	GroupStruct operand,domain;

	/**
	 * Children: [operand, domain]
	 * */
	ComplementImpl(GroupStruct operand,GroupStruct domain,CompositeStruct container) throws Exception {
		super(container);
		
		if(operand==null||domain==null)
			throw new Exception("Null Operand|Domain is invalid");
		this.operand=operand;
		this.domain = domain;
		
		this.container.addChildStruct(operand);
		this.container.addChildStruct(domain);
	}

	@Override
	public void setOperand(Struct operand) throws Exception {
		if(operand==null)
			throw new Exception("Null operand is invalid");
		if(!(operand instanceof GroupStruct))
			throw new Exception("GroupStruct is required in operand");
		
		GroupStruct o = (GroupStruct) operand;
		this.setOperand(o);
	}
	@Override
	public void setOperand(GroupStruct operand) throws Exception {
		if(operand==null)
			throw new Exception("Null operand is invalid");
		
		this.container.removeChildStruct(this.operand);
		this.operand=operand;
		this.container.addChildStruct(this.operand);
	}

	@Override
	public int getDimension() {return 1;}
	@Override
	public GroupStruct getOperand() {return this.operand;}
	
	@Override
	public String toString(){return "~"+this.operand.toString();}

	@Override
	public GroupStruct getDomain() {
		return this.domain;
	}
}
