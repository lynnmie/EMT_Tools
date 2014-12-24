package cn.edu.buaa.sei.SVI.interpreter.logic;

import cn.edu.buaa.sei.SVI.struct.logic.LogicFunctionTemplate;

/**
 * <i>FunctionTemplateInferencer</i> is inferencer for native method.
 * */
public interface FunctionTemplateInferencer extends Inferencer{
	public Boolean interpret(LogicFunctionTemplate template) throws Exception;
}
