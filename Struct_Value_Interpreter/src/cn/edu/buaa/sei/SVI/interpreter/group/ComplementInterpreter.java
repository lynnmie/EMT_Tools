package cn.edu.buaa.sei.SVI.interpreter.group;

import cn.edu.buaa.sei.SVI.struct.group.Complement;
import cn.edu.buaa.sei.SVI.struct.group.Group;

public interface ComplementInterpreter extends GroupInterpreter{
	public Group interpret(Complement op) throws Exception;
}
