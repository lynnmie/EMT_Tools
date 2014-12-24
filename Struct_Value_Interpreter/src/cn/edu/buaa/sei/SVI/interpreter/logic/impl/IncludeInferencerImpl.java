package cn.edu.buaa.sei.SVI.interpreter.logic.impl;

import cn.edu.buaa.sei.SVI.interpreter.core.Interpreter;
import cn.edu.buaa.sei.SVI.interpreter.core.RegisterMachine;
import cn.edu.buaa.sei.SVI.interpreter.group.GroupInterpreter;
import cn.edu.buaa.sei.SVI.interpreter.logic.IncludeInferencer;
import cn.edu.buaa.sei.SVI.struct.core.Struct;
import cn.edu.buaa.sei.SVI.struct.core.extend.GroupStruct;
import cn.edu.buaa.sei.SVI.struct.core.extend.LogicStruct;
import cn.edu.buaa.sei.SVI.struct.group.Group;
import cn.edu.buaa.sei.SVI.struct.group.Include;

public class IncludeInferencerImpl implements IncludeInferencer{

	@Override
	public Boolean interpret(LogicStruct input) throws Exception {
		if(input==null)
			throw new Exception("Null input is invalid");
		if(!(input instanceof Include))
			throw new Exception("Include required");
		
		Include op = (Include) input;
		return this.interpret(op);
	}

	@Override
	public Object interpret(Struct input) throws Exception {
		if(input==null)
			throw new Exception("Null input is invalid");
		if(!(input instanceof Include))
			throw new Exception("Include required");
		
		Include op = (Include) input;
		return this.interpret(op);
	}

	@Override
	public Boolean interpret(Include op) throws Exception {
		if(op==null)
			throw new Exception("Null operator is invalid");
		
		Struct x = op.getLeftOperand();
		GroupStruct y = op.getRightOperand();
		
		if(x==null||y==null)
			throw new Exception("Structure Error: null operands");
		
		Interpreter xi = (Interpreter) RegisterMachine.getRegister().get(x);
		GroupInterpreter yi = (GroupInterpreter) RegisterMachine.getRegister().get(y);
		
		if(xi==null)
			throw new Exception("Left operand: "+x.getClass().getCanonicalName()+" has not been registered");
		if(yi==null)
			throw new Exception("Right operand: "+y.getClass().getCanonicalName()+" has not been registered");
		
		Object xv = xi.interpret(x);
		Group yv = yi.interpret(y);
		
		if(xv==null||yv==null)return null;
		return yv.contains(xv);
	}
}
