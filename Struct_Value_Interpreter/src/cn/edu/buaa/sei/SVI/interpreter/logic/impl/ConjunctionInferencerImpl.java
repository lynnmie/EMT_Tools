package cn.edu.buaa.sei.SVI.interpreter.logic.impl;

import cn.edu.buaa.sei.SVI.interpreter.core.Interpreter;
import cn.edu.buaa.sei.SVI.interpreter.core.RegisterMachine;
import cn.edu.buaa.sei.SVI.interpreter.logic.ConjunctionInferencer;
import cn.edu.buaa.sei.SVI.struct.core.Struct;
import cn.edu.buaa.sei.SVI.struct.core.extend.LogicStruct;
import cn.edu.buaa.sei.SVI.struct.logic.Conjunction;

public class ConjunctionInferencerImpl implements ConjunctionInferencer{

	@Override
	public Boolean interpret(LogicStruct input) throws Exception {
		if(input==null)
			throw new Exception("Null input is invalid");
		if(!(input instanceof Conjunction))
			throw new Exception("Conjunction required");
		
		Conjunction op = (Conjunction) input;
		return this.interpret(op);
	}

	@Override
	public Object interpret(Struct input) throws Exception {
		if(input==null)
			throw new Exception("Null input is invalid");
		if(!(input instanceof Conjunction))
			throw new Exception("Conjunction required");
		
		Conjunction op = (Conjunction) input;
		return this.interpret(op);
	}

	@Override
	public Boolean interpret(Conjunction op) throws Exception {
		if(op==null)
			throw new Exception("Null operator is invalid");
		
		LogicStruct[] operands = op.getOperands();
		if(operands==null)
			throw new Exception("Structure Errors");
		
		boolean containNull = false;
		for(int i=0;i<operands.length;i++){
			Interpreter interpreter = RegisterMachine.getRegister().get(operands[i]);
			if(interpreter==null)
				throw new Exception("operands["+i+"]: "+operands[i].getClass().getCanonicalName()+" has not been registered");
			
			Object result = interpreter.interpret(operands[i]);
			if(result==null)containNull=true;
			else if(!(result instanceof Boolean))
				throw new Exception("Error Type: "+result.getClass().getCanonicalName());
			else{
				Boolean r = (Boolean) result;
				if(!r)return false;
			}
		}
		
		if(containNull)return null;
		else return true;
	}

}
