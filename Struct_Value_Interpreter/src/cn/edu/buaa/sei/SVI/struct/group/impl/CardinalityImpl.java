package cn.edu.buaa.sei.SVI.struct.group.impl;

import cn.edu.buaa.sei.SVI.struct.core.CompositeStruct;
import cn.edu.buaa.sei.SVI.struct.core.Struct;
import cn.edu.buaa.sei.SVI.struct.core.extend.GroupStruct;
import cn.edu.buaa.sei.SVI.struct.group.Cardinality;
import cn.edu.buaa.sei.SVI.struct.numeric.impl.NumericOperatorImpl;

public class CardinalityImpl extends NumericOperatorImpl implements Cardinality{

	GroupStruct operand;
	
	CardinalityImpl(GroupStruct operand,CompositeStruct container) throws Exception {
		super(container);
		
		if(operand==null)
			throw new Exception("Null Operand is invalid");
		
		this.operand=operand;
		this.addChildStruct(this.operand);
	}

	@Override
	public void setOperand(Struct operand) throws Exception {
		if(operand==null)
			throw new Exception("Null Operand is invalid");
		if(!(operand instanceof GroupStruct))
			throw new Exception("GrouStruct Operand is required");
		
		GroupStruct t = (GroupStruct) operand;
		this.setOperand(t);
	}
	@Override
	public void setOperand(GroupStruct operand) throws Exception {
		if(operand==null)
			throw new Exception("Null Operand is invalid");
		
		this.container.removeChildStruct(this.operand);
		this.operand=operand;
		this.container.addChildStruct(this.operand);
	}

	@Override
	public int getDimension() {return 1;}

	@Override
	public GroupStruct getOperand() {
		return this.operand;
	}

	@Override
	public String toString(){
		return "|"+this.operand.toString()+"|";
	}

}
