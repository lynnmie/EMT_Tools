package cn.edu.buaa.sei.SVI.interpreter.numeric.impl;

import cn.edu.buaa.sei.SVI.interpreter.numeric.VariableComputer;
import cn.edu.buaa.sei.SVI.struct.core.Struct;
import cn.edu.buaa.sei.SVI.struct.core.extend.NumericStruct;
import cn.edu.buaa.sei.SVI.struct.core.variable.Bindable;
import cn.edu.buaa.sei.SVI.struct.numeric.NumericVariable;

public class VariableComputerImpl implements VariableComputer{
	@Override
	public Object interpret(Bindable input) throws Exception {
		if(input==null)throw new Exception("Null input is invalid");
		if(!(input instanceof NumericVariable))throw new Exception("NumericVariable required");
		
		NumericVariable var = (NumericVariable) input;
		return this.interpret(var);
	}

	@Override
	public Number interpret(NumericStruct input) throws Exception {
		if(input==null)throw new Exception("Null input is invalid");
		if(!(input instanceof NumericVariable))throw new Exception("NumericVariable required");
		
		NumericVariable var = (NumericVariable) input;
		return this.interpret(var);
	}

	@Override
	public Object interpret(Struct input) throws Exception {
		if(input==null)throw new Exception("Null input is invalid");
		if(!(input instanceof NumericVariable))throw new Exception("NumericVariable required");
		
		NumericVariable var = (NumericVariable) input;
		return this.interpret(var);
	}

	@Override
	public Number interpret(NumericVariable var) throws Exception {
		if(var==null)throw new Exception("Null variable is invalid");
		return var.read();
	}
}
