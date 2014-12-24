package cn.edu.buaa.sei.SVI.interpreter.logic;

import cn.edu.buaa.sei.SVI.interpreter.core.Interpreter;
import cn.edu.buaa.sei.SVI.struct.core.extend.LogicStruct;

public interface Inferencer extends Interpreter{
	public Boolean interpret(LogicStruct input) throws Exception;
}
