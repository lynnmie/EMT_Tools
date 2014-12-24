package cn.edu.buaa.sei.SVI.interpreter.group.impl;

import cn.edu.buaa.sei.SVI.interpreter.group.GroupVariableInterpreter;
import cn.edu.buaa.sei.SVI.struct.core.Struct;
import cn.edu.buaa.sei.SVI.struct.core.extend.GroupStruct;
import cn.edu.buaa.sei.SVI.struct.group.Group;
import cn.edu.buaa.sei.SVI.struct.group.GroupVariable;

public class GroupVariableInterpreterImpl implements GroupVariableInterpreter{

	@Override
	public Group interpret(GroupStruct input) throws Exception {
		if(input==null)
			throw new Exception("Null input is invalid");
		if(!(input instanceof GroupVariable))
			throw new Exception("GroupVariable required");
		
		GroupVariable variable = (GroupVariable) input;
		return this.interpret(variable);
	}
	@Override
	public Object interpret(Struct input) throws Exception {
		if(input==null)
			throw new Exception("Null input is invalid");
		if(!(input instanceof GroupVariable))
			throw new Exception("GroupVariable required");
		
		GroupVariable variable = (GroupVariable) input;
		return this.interpret(variable);
	}
	@Override
	public Group interpret(GroupVariable variable) throws Exception {
		if(variable==null)
			throw new Exception("Null variable is invalid");
		return variable.read();
	}
	
}
