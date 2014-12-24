package cn.edu.buaa.sei.SVI.interpreter.logic.impl;

import cn.edu.buaa.sei.SVI.interpreter.logic.VariableInferencer;
import cn.edu.buaa.sei.SVI.struct.core.Struct;
import cn.edu.buaa.sei.SVI.struct.core.extend.LogicStruct;
import cn.edu.buaa.sei.SVI.struct.core.variable.Bindable;
import cn.edu.buaa.sei.SVI.struct.logic.LogicVariable;

public class VariableInferencerImpl implements VariableInferencer{

	@Override
	public Boolean interpret(LogicStruct input) throws Exception {
		if(input==null)
			throw new Exception("Null input is invalid");
		if(!(input instanceof LogicVariable))
			throw new Exception("LogicVariable required");
		
		LogicVariable var = (LogicVariable) input;
		return this.interpret(var);
	}

	@Override
	public Object interpret(Struct input) throws Exception {
		if(input==null)
			throw new Exception("Null input is invalid");
		if(!(input instanceof LogicVariable))
			throw new Exception("LogicVariable required");
		
		LogicVariable var = (LogicVariable) input;
		return this.interpret(var);
	}

	@Override
	public Object interpret(Bindable input) throws Exception {
		if(input==null)
			throw new Exception("Null input is invalid");
		if(!(input instanceof LogicVariable))
			throw new Exception("LogicVariable required");
		
		LogicVariable var = (LogicVariable) input;
		return this.interpret(var);
	}

	@Override
	public Boolean interpret(LogicVariable var) throws Exception {
		if(var==null)
			throw new Exception("Null variable is invalid");
		return var.read();
	}
}
