package cn.edu.buaa.sei.SVI.interpreter.numeric.impl;

import cn.edu.buaa.sei.SVI.interpreter.core.RegisterMachine;
import cn.edu.buaa.sei.SVI.interpreter.numeric.Computer;
import cn.edu.buaa.sei.SVI.interpreter.numeric.FunctionComputer;
import cn.edu.buaa.sei.SVI.struct.core.Struct;
import cn.edu.buaa.sei.SVI.struct.core.extend.NumericStruct;
import cn.edu.buaa.sei.SVI.struct.core.function.FunctionBody;
import cn.edu.buaa.sei.SVI.struct.core.function.FunctionBodyAPI;
import cn.edu.buaa.sei.SVI.struct.numeric.NumericFunction;
import cn.edu.buaa.sei.SVI.struct.numeric.NumericFunctionTemplate;

public class FunctionComputerImpl implements FunctionComputer{

	@Override
	public Number interpret(NumericStruct input) throws Exception {
		if(input==null)
			throw new Exception("Null input is invalid");
		if(!(input instanceof NumericFunction))
			throw new Exception("NumericFunction required");
		
		NumericFunction function = (NumericFunction) input;
		return this.interpret(function);
	}

	@Override
	public Object interpret(Struct input) throws Exception {
		if(input==null)
			throw new Exception("Null input is invalid");
		if(!(input instanceof NumericFunction))
			throw new Exception("NumericFunction required");
		
		NumericFunction function = (NumericFunction) input;
		return this.interpret(function);
	}

	@Override
	public Number interpret(NumericFunction function) throws Exception {
		if(function==null)
			throw new Exception("Null function is invalid");
		
		NumericFunctionTemplate template = function.getTemplate();
		if(template==null)
			throw new Exception("Structure Error: null template");
		
		Computer computer=null;
		try{computer=(Computer) RegisterMachine.getRegister().get(template);}
		catch(Exception ex){computer=null;}
		
		if(computer==null){
			FunctionBody body = function.getBody();
			if(body==null)
				throw new Exception("Not a native method: body required");
			
			if(body instanceof FunctionBodyAPI){
				((FunctionBodyAPI) body).execute();
			}
			else{
				throw new Exception("Unknown FunctionBody: "+body.getClass().getCanonicalName());
			}
		}
		else{
			computer.interpret(template);
		}
		return this.getResult(template);
	}
	
	protected Number getResult(NumericFunctionTemplate template) throws Exception{
		if(template.getOutput()==null)return null;
		else return template.getOutput().read();
	}

}
