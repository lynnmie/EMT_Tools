package cn.edu.buaa.sei.SVI.interpreter.numeric;

import cn.edu.buaa.sei.SVI.struct.numeric.NumericFunction;

public interface FunctionComputer extends Computer{
	public Number interpret(NumericFunction function) throws Exception;
}
