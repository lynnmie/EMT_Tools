package cn.edu.buaa.sei.SVI.interpreter.logic.impl;

import java.util.Iterator;

import cn.edu.buaa.sei.SVI.interpreter.core.RegisterMachine;
import cn.edu.buaa.sei.SVI.interpreter.group.GroupInterpreter;
import cn.edu.buaa.sei.SVI.interpreter.logic.GroupEqualInferencer;
import cn.edu.buaa.sei.SVI.struct.core.Struct;
import cn.edu.buaa.sei.SVI.struct.core.extend.GroupStruct;
import cn.edu.buaa.sei.SVI.struct.core.extend.LogicStruct;
import cn.edu.buaa.sei.SVI.struct.group.Group;
import cn.edu.buaa.sei.SVI.struct.group.GroupEqual;

public class GroupEqualInferencerImpl implements GroupEqualInferencer{

	@Override
	public Boolean interpret(LogicStruct input) throws Exception {
		if(input==null)throw new Exception("Null input is invalid");
		if(!(input instanceof GroupEqual))throw new Exception("GroupEqual required");
		GroupEqual op = (GroupEqual) input;
		return this.interpret(op);
	}

	@Override
	public Object interpret(Struct input) throws Exception {
		if(input==null)throw new Exception("Null input is invalid");
		if(!(input instanceof GroupEqual))throw new Exception("GroupEqual required");
		GroupEqual op = (GroupEqual) input;
		return this.interpret(op);
	}

	@Override
	public Boolean interpret(GroupEqual op) throws Exception {
		if(op==null)
			throw new Exception("Null operator is invalid");
		
		GroupStruct x = op.getLeftOperand();
		GroupStruct y = op.getRightOperand();
		
		if(x==null||y==null)
			throw new Exception("Structure Error: null operands");
		
		GroupInterpreter xi = (GroupInterpreter) RegisterMachine.getRegister().get(x);
		GroupInterpreter yi = (GroupInterpreter) RegisterMachine.getRegister().get(y);
		
		if(xi==null)
			throw new Exception("Left operand: "+x.getClass().getCanonicalName()+" has not been registered");
		if(yi==null)
			throw new Exception("Right operand: "+y.getClass().getCanonicalName()+" has not been registered");
		
		Group xv = xi.interpret(x);
		Group yv = yi.interpret(y);
		
		if(xv==null||yv==null)return null;
		
		if(xv.size()!=yv.size())return false;
		
		Iterator<Object> xitor = xv.iterator();
		Iterator<Object> yitor = yv.iterator();
		
		boolean containNull = false;
		while(xitor.hasNext()){
			Boolean in = yv.contains(xitor.next());
			if(in==null)containNull=true;
			else if(in==false)return false;
		}
		
		if(containNull)return null;
		
		while(yitor.hasNext()){
			Boolean in = xv.contains(yitor.next());
			if(in==null)containNull=true;
			else if(in==false)return false;
		}
		
		if(containNull)return null;
		
		return true;
	}
}
