package cn.edu.buaa.sei.SVI.interpreter.numeric;

import cn.edu.buaa.sei.SVI.struct.numeric.NumericFunctionTemplate;

public interface FunctionTemplateComputer extends Computer{
	public Number interpret(NumericFunctionTemplate template) throws Exception;
}
