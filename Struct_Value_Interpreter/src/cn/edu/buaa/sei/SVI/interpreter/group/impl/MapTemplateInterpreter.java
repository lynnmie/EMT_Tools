package cn.edu.buaa.sei.SVI.interpreter.group.impl;

import java.util.Iterator;

import cn.edu.buaa.sei.SVI.interpreter.core.Interpreter;
import cn.edu.buaa.sei.SVI.interpreter.core.RegisterMachine;
import cn.edu.buaa.sei.SVI.interpreter.group.GroupFunctionTemplateInterpreter;
import cn.edu.buaa.sei.SVI.struct.core.Struct;
import cn.edu.buaa.sei.SVI.struct.core.extend.GroupStruct;
import cn.edu.buaa.sei.SVI.struct.core.function.Function;
import cn.edu.buaa.sei.SVI.struct.core.variable.Variable;
import cn.edu.buaa.sei.SVI.struct.group.Group;
import cn.edu.buaa.sei.SVI.struct.group.GroupFunctionTemplate;
import cn.edu.buaa.sei.SVI.struct.group.GroupVariable;
import cn.edu.buaa.sei.SVI.struct.group.extend.MapTemplate;
import cn.edu.buaa.sei.SVI.struct.group.impl.SetGroup;

public class MapTemplateInterpreter implements GroupFunctionTemplateInterpreter{
	@Override
	public Group interpret(GroupStruct input) throws Exception {
		if(input==null)throw new Exception("Null input is invalid");
		if(!(input instanceof MapTemplate))throw new Exception("MapTemplate required");
		MapTemplate template = (MapTemplate) input;
		return this.interpret(template);
	}

	@Override
	public Object interpret(Struct input) throws Exception {
		if(input==null)throw new Exception("Null input is invalid");
		if(!(input instanceof MapTemplate))throw new Exception("MapTemplate required");
		MapTemplate template = (MapTemplate) input;
		return this.interpret(template);
	}

	@Override
	public Group interpret(GroupFunctionTemplate input) throws Exception {
		if(input==null)throw new Exception("Null input is invalid");
		if(!(input instanceof MapTemplate))throw new Exception("MapTemplate required");
		MapTemplate template = (MapTemplate) input;
		return this.interpret(template);
	}

	protected Group interpret(MapTemplate template) throws Exception{
		if(template==null)throw new Exception("Null template is invalid");
		
		GroupVariable g = (GroupVariable) template.getArguments()[0];
		Variable f = template.getArguments()[1];
		
		if(g==null||f==null)
			throw new Exception("Structure Error: null group argument {[0]} + null function argument{[1]}");
		
		Group group = g.read();
		if(group==null)throw new Exception("Null assignment : group");
		Function function = (Function) f.read();
		if(function==null)throw new Exception("Null assignment: function");
		
		Interpreter interpreter = RegisterMachine.getRegister().get(function);
		if(interpreter==null)
			throw new Exception("Interpretation failed: "+function.getClass().getCanonicalName()+" has not been registered");
		
		Iterator<Object> itor = group.iterator();
		Group rg = new SetGroup();
		while(itor.hasNext()){
			Object val = itor.next();
			function.getTemplate().getArguments()[0].assign(val);
			Object result = interpreter.interpret(function);
			rg.add(result);
		}
		template.getOutput().assign(rg);
		
		return rg;
	}
}
