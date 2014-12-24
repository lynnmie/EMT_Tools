package cn.edu.buaa.sei.SVI.interpreter.group;

import cn.edu.buaa.sei.SVI.struct.group.Difference;
import cn.edu.buaa.sei.SVI.struct.group.Group;

public interface DifferenceInterpreter extends GroupInterpreter{
	public Group interpret(Difference op) throws Exception;
}
