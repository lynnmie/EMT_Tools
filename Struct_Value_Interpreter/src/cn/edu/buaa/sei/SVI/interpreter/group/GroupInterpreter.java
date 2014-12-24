package cn.edu.buaa.sei.SVI.interpreter.group;

import cn.edu.buaa.sei.SVI.interpreter.core.Interpreter;
import cn.edu.buaa.sei.SVI.struct.core.extend.GroupStruct;
import cn.edu.buaa.sei.SVI.struct.group.Group;

public interface GroupInterpreter extends Interpreter{
	public Group interpret(GroupStruct input) throws Exception;
}
