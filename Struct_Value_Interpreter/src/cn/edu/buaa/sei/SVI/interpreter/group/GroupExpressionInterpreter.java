package cn.edu.buaa.sei.SVI.interpreter.group;

import cn.edu.buaa.sei.SVI.struct.group.Group;
import cn.edu.buaa.sei.SVI.struct.group.GroupExpression;

public interface GroupExpressionInterpreter extends GroupInterpreter{
	public Group interpret(GroupExpression expr) throws Exception;
}
