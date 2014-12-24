package cn.edu.buaa.sei.SVI.interpreter.group;

import cn.edu.buaa.sei.SVI.struct.group.Group;
import cn.edu.buaa.sei.SVI.struct.group.Union;

public interface UnionInterpreter extends GroupInterpreter{
	public Group interpret(Union op) throws Exception;
}
