package cn.edu.buaa.sei.SVI.interpreter.logic.impl;

import java.util.Set;

import cn.edu.buaa.sei.SVI.interpreter.core.MemoryReader;
import cn.edu.buaa.sei.SVI.interpreter.core.RegisterMachine;
import cn.edu.buaa.sei.SVI.interpreter.logic.AtMostInferencer;
import cn.edu.buaa.sei.SVI.interpreter.logic.Inferencer;
import cn.edu.buaa.sei.SVI.struct.core.Struct;
import cn.edu.buaa.sei.SVI.struct.core.extend.LogicStruct;
import cn.edu.buaa.sei.SVI.struct.logic.AtMost;
import cn.edu.buaa.sei.SVI.struct.logic.DiscourseDomain;

public class AtMostInferencerImpl implements AtMostInferencer{

	@Override
	public Boolean interpret(LogicStruct input) throws Exception {
		if(input==null)
			throw new Exception("Null input is invalid");
		if(!(input instanceof AtMost))
			throw new Exception("AtMost required");
		
		AtMost op = (AtMost) input;
		return this.interpret(op);
	}

	@Override
	public Object interpret(Struct input) throws Exception {
		if(input==null)
			throw new Exception("Null input is invalid");
		if(!(input instanceof AtMost))
			throw new Exception("AtMost required");
		
		AtMost op = (AtMost) input;
		return this.interpret(op);
	}

	@SuppressWarnings("rawtypes")
	@Override
	public Boolean interpret(AtMost op) throws Exception {
		// for quantifier operator
		if(op==null)
			throw new Exception("Null operator is invalid");
				
		DiscourseDomain domain = op.getDomain();
		LogicStruct scope = op.getScope();
				
		if(domain==null||scope==null)
			throw new Exception("Structure Error: null operands");
				
		MemoryReader reader = (MemoryReader) RegisterMachine.getRegister().get(domain);
		Inferencer inferencer = (Inferencer) RegisterMachine.getRegister().get(scope);
				
		Set set = (Set) reader.interpret(domain);
		if(set==null)return null;
		
		// specialized
		if(set.size()<=op.getUpperBound())return true;
		
		int count = 0;
		int null_count = 0;
		
		for(Object val:set){
			if(count>op.getUpperBound())return false;
			
			domain.getIterator().assign(val);
			Boolean r = inferencer.interpret(scope);
			
			if(r==null)null_count++;
			else if(r)count++;
		}
		
		if(count>op.getUpperBound())return false;
		else if(count+null_count<=op.getUpperBound())return true;
		else return null;
	}

}
