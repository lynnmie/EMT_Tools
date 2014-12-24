package cn.edu.buaa.sei.SVI.interpreter.numeric;

import cn.edu.buaa.sei.SVI.interpreter.core.Interpreter;
import cn.edu.buaa.sei.SVI.struct.core.extend.NumericStruct;

public interface Computer extends Interpreter{
	public Number interpret(NumericStruct input) throws Exception;
}
