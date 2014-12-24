package cn.edu.buaa.sei.SVI.interpreter.logic.impl;

import java.util.Set;

import cn.edu.buaa.sei.SVI.interpreter.core.MemoryReader;
import cn.edu.buaa.sei.SVI.interpreter.core.RegisterMachine;
import cn.edu.buaa.sei.SVI.interpreter.logic.ExistentialInferencer;
import cn.edu.buaa.sei.SVI.interpreter.logic.Inferencer;
import cn.edu.buaa.sei.SVI.struct.core.Struct;
import cn.edu.buaa.sei.SVI.struct.core.extend.LogicStruct;
import cn.edu.buaa.sei.SVI.struct.logic.DiscourseDomain;
import cn.edu.buaa.sei.SVI.struct.logic.Existential;

public class ExistentialInferencerImpl implements ExistentialInferencer{

	@Override
	public Boolean interpret(LogicStruct input) throws Exception {
		if(input==null)
			throw new Exception("Null input is invalid");
		if(!(input instanceof Existential))
			throw new Exception("Existential required");
		
		Existential op = (Existential) input;
		return this.interpret(op);
	}

	@Override
	public Object interpret(Struct input) throws Exception {
		if(input==null)
			throw new Exception("Null input is invalid");
		if(!(input instanceof Existential))
			throw new Exception("Existential required");
		
		Existential op = (Existential) input;
		return this.interpret(op);
	}

	@SuppressWarnings("rawtypes")
	@Override
	public Boolean interpret(Existential op) throws Exception {
		if(op==null)
			throw new Exception("Null operator is invalid");
		
		DiscourseDomain domain = op.getDomain();
		LogicStruct scope = op.getScope();
		
		if(domain==null||scope==null)
			throw new Exception("Structure Error: null domain|scope");
		
		MemoryReader reader = (MemoryReader) RegisterMachine.getRegister().get(domain);
		Inferencer inferencer = (Inferencer) RegisterMachine.getRegister().get(scope);
		
		Set set = (Set) reader.interpret(domain);
		if(set==null)return null;
		
		boolean containNull = false;
		for(Object val:set){
			domain.getIterator().assign(val);
			Boolean result = inferencer.interpret(scope);
			if(result==null)
				containNull=true;
			else if(result)
				return true;
		}
		
		if(containNull)return null;
		else return false;
	}
	
}
