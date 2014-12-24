package cn.edu.buaa.sei.SVI.interpreter.group;

import cn.edu.buaa.sei.SVI.struct.group.Group;
import cn.edu.buaa.sei.SVI.struct.group.GroupVariable;

public interface GroupVariableInterpreter extends GroupInterpreter{
	public Group interpret(GroupVariable variable) throws Exception;
}
