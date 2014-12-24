package cn.edu.buaa.sei.SVI.interpreter.logic.impl;

import java.util.Set;

import cn.edu.buaa.sei.SVI.interpreter.core.MemoryReader;
import cn.edu.buaa.sei.SVI.interpreter.core.RegisterMachine;
import cn.edu.buaa.sei.SVI.interpreter.logic.AtLeastInferencer;
import cn.edu.buaa.sei.SVI.interpreter.logic.Inferencer;
import cn.edu.buaa.sei.SVI.struct.core.Struct;
import cn.edu.buaa.sei.SVI.struct.core.extend.LogicStruct;
import cn.edu.buaa.sei.SVI.struct.logic.AtLeast;
import cn.edu.buaa.sei.SVI.struct.logic.DiscourseDomain;

public class AtLeastInferencerImpl implements AtLeastInferencer{

	@Override
	public Boolean interpret(LogicStruct input) throws Exception {
		if(input==null)
			throw new Exception("Null input is invalid");
		if(!(input instanceof AtLeast))
			throw new Exception("AtLeast required");
		
		AtLeast op = (AtLeast) input;
		return this.interpret(op);
	}

	@Override
	public Object interpret(Struct input) throws Exception {
		if(input==null)
			throw new Exception("Null input is invalid");
		if(!(input instanceof AtLeast))
			throw new Exception("AtLeast required");
		
		AtLeast op = (AtLeast) input;
		return this.interpret(op);
	}

	@SuppressWarnings("rawtypes")
	@Override
	public Boolean interpret(AtLeast op) throws Exception {
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
		
		// for specialized
		if(set.size()<op.getLowerBound())return false;
		
		int count = 0;
		int null_count = 0;
		for(Object val:set){
			if(count>=op.getLowerBound())
				return true;
			
			domain.getIterator().assign(val);
			Boolean r = inferencer.interpret(scope);
			
			if(r==null)null_count++;
			else if(r)count++;
		}
		
		if(count>=op.getLowerBound())return true;
		else if(count+null_count<op.getLowerBound())return false;
		else return null;
	}

}
