package cn.edu.buaa.sei.SVI.interpreter.group.impl;

import cn.edu.buaa.sei.SVI.interpreter.core.RegisterMachine;
import cn.edu.buaa.sei.SVI.interpreter.group.GroupFunctionInterpreter;
import cn.edu.buaa.sei.SVI.interpreter.group.GroupInterpreter;
import cn.edu.buaa.sei.SVI.struct.core.Struct;
import cn.edu.buaa.sei.SVI.struct.core.extend.GroupStruct;
import cn.edu.buaa.sei.SVI.struct.core.function.FunctionBody;
import cn.edu.buaa.sei.SVI.struct.core.function.FunctionBodyAPI;
import cn.edu.buaa.sei.SVI.struct.group.Group;
import cn.edu.buaa.sei.SVI.struct.group.GroupFunction;
import cn.edu.buaa.sei.SVI.struct.group.GroupFunctionTemplate;

public class GroupFunctionInterpreterImpl implements GroupFunctionInterpreter{

	@Override
	public Group interpret(GroupStruct input) throws Exception {
		if(input==null)throw new Exception("Null input is invalid");
		if(!(input instanceof GroupFunction))throw new Exception("GroupFunction required");
		GroupFunction function = (GroupFunction) input;
		return this.interpret(function);
	}

	@Override
	public Object interpret(Struct input) throws Exception {
		if(input==null)throw new Exception("Null input is invalid");
		if(!(input instanceof GroupFunction))throw new Exception("GroupFunction required");
		GroupFunction function = (GroupFunction) input;
		return this.interpret(function);
	}

	@Override
	public Group interpret(GroupFunction function) throws Exception {
		if(function==null)throw new Exception("Null function is invalid");
		
		GroupFunctionTemplate template = function.getTemplate();
		if(template==null)throw new Exception("Structure Error: null template");
		
		GroupInterpreter interpreter = null;
		try{
			interpreter = (GroupInterpreter) RegisterMachine.getRegister().get(template);
		}
		catch(Exception ex){
			interpreter = null;
		}
		
		if(interpreter==null){
			FunctionBody body = function.getBody();
			if(body==null)
				throw new Exception("Not a native function: Body required");
			
			if(body instanceof FunctionBodyAPI){
				((FunctionBodyAPI) body).execute();
			}
			else{
				throw new Exception("Unknown FunctionBody: "+body.getClass().getCanonicalName());
			}
		}
		else{
			interpreter.interpret(template);
		}
		return this.getResult(template);
	}
	
	protected Group getResult(GroupFunctionTemplate template) throws Exception{
		if(template.getOutput()==null)return null;
		else return template.getOutput().read();
	}

}
