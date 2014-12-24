package cn.edu.buaa.sei.SVI.interpreter.logic.impl;

import cn.edu.buaa.sei.SVI.interpreter.core.Interpreter;
import cn.edu.buaa.sei.SVI.interpreter.core.RegisterMachine;
import cn.edu.buaa.sei.SVI.interpreter.logic.NegationInferencer;
import cn.edu.buaa.sei.SVI.struct.core.Struct;
import cn.edu.buaa.sei.SVI.struct.core.extend.LogicStruct;
import cn.edu.buaa.sei.SVI.struct.logic.Negation;

public class NegationInferencerImpl implements NegationInferencer{

	@Override
	public Boolean interpret(LogicStruct input) throws Exception {
		if(input==null)
			throw new Exception("Null input is invalid");
		if(!(input instanceof Negation))
			throw new Exception("Negation required");
		
		Negation op = (Negation) input;
		return this.interpret(op);
	}
	@Override
	public Object interpret(Struct input) throws Exception {
		if(input==null)
			throw new Exception("Null input is invalid");
		if(!(input instanceof Negation))
			throw new Exception("Negation required");
		
		Negation op = (Negation) input;
		return this.interpret(op);
	}
	@Override
	public Boolean interpret(Negation op) throws Exception {
		if(op==null)
			throw new Exception("Null argument is invalid");
		
		LogicStruct operand = op.getOperand();
		if(operand==null)
			throw new Exception("Structure Error: null operand");
		
		Interpreter interpreter = RegisterMachine.getRegister().get(operand);
		if(interpreter==null)
			throw new Exception("operand: "+operand.getClass().getCanonicalName()+" has not been registered");
		
		Object result = interpreter.interpret(operand);
		if(result==null)return null;
		else if(!(result instanceof Boolean))
			throw new Exception("Invalid Result: "+result.getClass().getCanonicalName());
		else{
			Boolean r = (Boolean) result;
			return !r;
		}
	}

}
