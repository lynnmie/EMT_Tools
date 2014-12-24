package cn.edu.buaa.sei.SVI.interpreter.group.impl;

import java.util.Iterator;

import cn.edu.buaa.sei.SVI.interpreter.core.RegisterMachine;
import cn.edu.buaa.sei.SVI.interpreter.group.GroupFunctionTemplateInterpreter;
import cn.edu.buaa.sei.SVI.interpreter.logic.Inferencer;
import cn.edu.buaa.sei.SVI.struct.core.Struct;
import cn.edu.buaa.sei.SVI.struct.core.extend.GroupStruct;
import cn.edu.buaa.sei.SVI.struct.core.variable.FreeVariable;
import cn.edu.buaa.sei.SVI.struct.group.Group;
import cn.edu.buaa.sei.SVI.struct.group.GroupFunctionTemplate;
import cn.edu.buaa.sei.SVI.struct.group.GroupVariable;
import cn.edu.buaa.sei.SVI.struct.group.extend.FilterTemplate;
import cn.edu.buaa.sei.SVI.struct.group.impl.SetGroup;
import cn.edu.buaa.sei.SVI.struct.logic.LogicFunction;

public class FilterTemplateInterpreter implements GroupFunctionTemplateInterpreter{

	@Override
	public Group interpret(GroupStruct input) throws Exception {
		if(input==null)throw new Exception("Null input is invalid");
		if(!(input instanceof FilterTemplate))throw new Exception("FilterTemplate required");
		FilterTemplate template = (FilterTemplate) input;
		return this.interpret(template);
	}

	@Override
	public Object interpret(Struct input) throws Exception {
		if(input==null)throw new Exception("Null input is invalid");
		if(!(input instanceof FilterTemplate))throw new Exception("FilterTemplate required");
		FilterTemplate template = (FilterTemplate) input;
		return this.interpret(template);
	}

	@Override
	public Group interpret(GroupFunctionTemplate template) throws Exception {
		if(template==null)throw new Exception("Null input is invalid");
		if(!(template instanceof FilterTemplate))throw new Exception("FilterTemplate required");
		FilterTemplate t = (FilterTemplate) template;
		return this.interpret(t);
	}
	
	protected Group interpret(FilterTemplate template) throws Exception {
		if(template==null)throw new Exception("Null template is invalid");
		
		GroupVariable g = (GroupVariable) template.getArguments()[0];
		FreeVariable c = (FreeVariable) template.getArguments()[1];
		
		if(g==null||c==null)
			throw new Exception("Structure Error: null a[0]{group argument} + null a[1]{condition argument}");
		
		Group group = g.read();
		if(group==null)
			throw new Exception("Null assignment of argument: group");
		LogicFunction condition = (LogicFunction) c.read();
		if(condition==null)
			throw new Exception("Null assignment of argument: condition");
		
		Inferencer inferencer = (Inferencer) RegisterMachine.getRegister().get(condition);
		
		Iterator<Object> itor = group.iterator();
		Group rg = new SetGroup();
		while(itor.hasNext()){
			Object val = itor.next();
			condition.getTemplate().getArguments()[0].assign(val);
			Boolean result = inferencer.interpret(condition);
			if(result!=null&&result)
				rg.add(val);
		}
		template.getOutput().assign(rg);
		
		return rg;
	}

}
