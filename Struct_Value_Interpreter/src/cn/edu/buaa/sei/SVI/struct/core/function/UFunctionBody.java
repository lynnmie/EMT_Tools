package cn.edu.buaa.sei.SVI.struct.core.function;

import cn.edu.buaa.sei.SVI.struct.core.program.Program;

/**
 * <i>UFunctionBody</i> allow users to define their own implementation of the function.
 * */
public interface UFunctionBody extends FunctionBody{
	/**
	 * Return the implementation of the Function {by user definition}
	 * */
	public Program getProgram();
}
