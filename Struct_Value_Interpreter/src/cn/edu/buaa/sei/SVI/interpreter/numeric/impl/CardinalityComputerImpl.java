package cn.edu.buaa.sei.SVI.interpreter.numeric.impl;

import cn.edu.buaa.sei.SVI.interpreter.core.RegisterMachine;
import cn.edu.buaa.sei.SVI.interpreter.group.GroupInterpreter;
import cn.edu.buaa.sei.SVI.interpreter.numeric.CardinalityComputer;
import cn.edu.buaa.sei.SVI.struct.core.Struct;
import cn.edu.buaa.sei.SVI.struct.core.extend.GroupStruct;
import cn.edu.buaa.sei.SVI.struct.core.extend.NumericStruct;
import cn.edu.buaa.sei.SVI.struct.group.Cardinality;
import cn.edu.buaa.sei.SVI.struct.group.Group;

public class CardinalityComputerImpl implements CardinalityComputer{

	@Override
	public Number interpret(NumericStruct input) throws Exception {
		if(input==null)throw new Exception("Null input is invalid");
		if(!(input instanceof Cardinality))throw new Exception("Cardinality required");
		
		Cardinality op = (Cardinality) input;
		return this.interpret(op);
	}

	@Override
	public Object interpret(Struct input) throws Exception {
		if(input==null)throw new Exception("Null input is invalid");
		if(!(input instanceof Cardinality))throw new Exception("Cardinality required");
		
		Cardinality op = (Cardinality) input;
		return this.interpret(op);
	}

	@Override
	public Number interpret(Cardinality op) throws Exception {
		if(op==null)throw new Exception("Null operator is invalid");
		GroupStruct operand = op.getOperand();
		
		if(operand==null)
			throw new Exception("Structure Error: null operand");
		
		GroupInterpreter interpreter = (GroupInterpreter) RegisterMachine.getRegister().get(operand);
		if(interpreter==null)
			throw new Exception("Operand: "+operand.getClass().getCanonicalName()+" has not been registered");
		
		Group v = interpreter.interpret(operand);
		if(v==null)return null;
		else return v.size();
	}

}
