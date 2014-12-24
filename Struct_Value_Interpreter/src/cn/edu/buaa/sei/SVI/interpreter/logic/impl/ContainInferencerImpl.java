package cn.edu.buaa.sei.SVI.interpreter.logic.impl;

import java.util.Iterator;

import cn.edu.buaa.sei.SVI.interpreter.core.RegisterMachine;
import cn.edu.buaa.sei.SVI.interpreter.group.GroupInterpreter;
import cn.edu.buaa.sei.SVI.interpreter.logic.ContainInferencer;
import cn.edu.buaa.sei.SVI.struct.core.Struct;
import cn.edu.buaa.sei.SVI.struct.core.extend.GroupStruct;
import cn.edu.buaa.sei.SVI.struct.core.extend.LogicStruct;
import cn.edu.buaa.sei.SVI.struct.group.Contain;
import cn.edu.buaa.sei.SVI.struct.group.Group;

public class ContainInferencerImpl implements ContainInferencer{

	@Override
	public Boolean interpret(LogicStruct input) throws Exception {
		if(input==null)
			throw new Exception("Null input is invalid");
		if(!(input instanceof Contain))
			throw new Exception("Contain required");
		
		Contain op = (Contain) input;
		return this.interpret(op);
	}

	@Override
	public Object interpret(Struct input) throws Exception {
		if(input==null)
			throw new Exception("Null input is invalid");
		if(!(input instanceof Contain))
			throw new Exception("Contain required");
		
		Contain op = (Contain) input;
		return this.interpret(op);
	}

	@Override
	public Boolean interpret(Contain op) throws Exception {
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
		
		// check whether x is in y
		Iterator<Object> itor = xv.iterator();
		while(itor.hasNext()){
			Boolean in = yv.contains(itor.next());
			if(in==null)return null;
			else if(in==false)return false;
		}
		return true;
	}
}
