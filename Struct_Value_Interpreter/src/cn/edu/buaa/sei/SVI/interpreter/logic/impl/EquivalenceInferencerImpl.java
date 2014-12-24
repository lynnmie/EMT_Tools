package cn.edu.buaa.sei.SVI.interpreter.logic.impl;

import cn.edu.buaa.sei.SVI.interpreter.core.RegisterMachine;
import cn.edu.buaa.sei.SVI.interpreter.logic.EquivalenceInferencer;
import cn.edu.buaa.sei.SVI.interpreter.logic.Inferencer;
import cn.edu.buaa.sei.SVI.struct.core.Struct;
import cn.edu.buaa.sei.SVI.struct.core.extend.LogicStruct;
import cn.edu.buaa.sei.SVI.struct.logic.Equivalence;

public class EquivalenceInferencerImpl implements EquivalenceInferencer{

	@Override
	public Boolean interpret(LogicStruct input) throws Exception {
		if(input==null)
			throw new Exception("Null input is invalid");
		if(!(input instanceof Equivalence))
			throw new Exception("Equivalence required");
		
		Equivalence op = (Equivalence) input;
		
		return this.interpret(op);
	}

	@Override
	public Object interpret(Struct input) throws Exception {
		if(input==null)
			throw new Exception("Null input is invalid");
		if(!(input instanceof Equivalence))
			throw new Exception("Equivalence required");
		
		Equivalence op = (Equivalence) input;
		
		return this.interpret(op);
	}

	@Override
	public Boolean interpret(Equivalence op) throws Exception {
		if(op==null)
			throw new Exception("Null operator is invalid");
		
		LogicStruct left = op.getLeftOperand();
		LogicStruct right = op.getRightOperand();
		
		if(left==null||right==null)
			throw new Exception("Structure Error: null operands");
		
		Inferencer li = (Inferencer) RegisterMachine.getRegister().get(left);
		Inferencer ri = (Inferencer) RegisterMachine.getRegister().get(right);
		
		if(li==null)
			throw new Exception("Left operand: "+left.getClass().getCanonicalName()+" has not been registered");
		if(ri==null)
			throw new Exception("Right operand: "+right.getClass().getCanonicalName()+" has not been registered");
		
		Boolean r1 = li.interpret(left);
		if(r1==null)return null;
		Boolean r2 = ri.interpret(right);
		if(r2==null)return null;
		else return r1==r2;
	}

}
