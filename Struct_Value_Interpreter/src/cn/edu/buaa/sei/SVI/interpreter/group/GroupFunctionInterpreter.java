package cn.edu.buaa.sei.SVI.interpreter.group;

import cn.edu.buaa.sei.SVI.struct.group.Group;
import cn.edu.buaa.sei.SVI.struct.group.GroupFunction;

public interface GroupFunctionInterpreter extends GroupInterpreter{
	public Group interpret(GroupFunction function) throws Exception;
}
