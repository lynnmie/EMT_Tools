package cn.edu.buaa.sei.SVI.interpreter.logic.impl;

import cn.edu.buaa.sei.SVI.interpreter.core.RegisterMachine;
import cn.edu.buaa.sei.SVI.interpreter.logic.ImplicationInferencer;
import cn.edu.buaa.sei.SVI.interpreter.logic.Inferencer;
import cn.edu.buaa.sei.SVI.struct.core.Struct;
import cn.edu.buaa.sei.SVI.struct.core.extend.LogicStruct;
import cn.edu.buaa.sei.SVI.struct.logic.Implication;

public class ImplicationInferencerImpl implements ImplicationInferencer{

	@Override
	public Boolean interpret(LogicStruct input) throws Exception {
		if(input==null)
			throw new Exception("Null input is invalid");
		if(!(input instanceof Implication))
			throw new Exception("Implication required");
		
		Implication op = (Implication) input;
		return this.interpret(op);
	}

	@Override
	public Object interpret(Struct input) throws Exception {
		if(input==null)
			throw new Exception("Null input is invalid");
		if(!(input instanceof Implication))
			throw new Exception("Implication required");
		
		Implication op = (Implication) input;
		return this.interpret(op);
	}

	@Override
	public Boolean interpret(Implication op) throws Exception {
		if(op==null)
			throw new Exception("Null operator is invalid");
		
		LogicStruct left = op.getLeftOperand();
		LogicStruct right = op.getRightOperand();
		
		if(left==null||right==null)
			throw new Exception("Structure Errors: null operands");
		
		Inferencer li = (Inferencer) RegisterMachine.getRegister().get(left);
		Inferencer ri = (Inferencer) RegisterMachine.getRegister().get(right);
		
		if(li==null)
			throw new Exception("Left operand: "+left.getClass().getCanonicalName()+" has not been registered");
		if(ri==null)
			throw new Exception("Right operand: "+right.getClass().getCanonicalName()+" has not been registered");
		
		Boolean lr = li.interpret(left);
		if(lr==null){
			Boolean rr = ri.interpret(right);
			if(rr!=null&&rr==true)return true;
			else return null;
		}
		else if(lr){
			Boolean rr = ri.interpret(right);
			return rr;
		}
		else{
			return true;
		}
	}

}
