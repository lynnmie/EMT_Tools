package cn.edu.buaa.sei.SVI.struct.core.function;

import cn.edu.buaa.sei.SVI.struct.core.CompositeStruct;

/**
 * <i>FunctionBody</i> provides the implementation of its owner <i>Function</i>.<br>
 * The children of the <i>FunctionBody</i> as a <i>Struct</i> includes its Programs or nothing, not the owner Function.<br>
 * 1. <i>UFunctionBody</i> provides users to define the program structure in its implementation.<br>
 * 2. <i>FunctionBodyAPI</i> provides programmers to define the implementation of the function.<br>
 * 3. By using getFunction(), <i>FunctionBody</i> could further get the template of the function so to use the arguments values in it.
 * */
public interface FunctionBody extends CompositeStruct{
	/**
	 * Return its owner function
	 * */
	public Function getFunction();
	/**
	 * Set its owner function
	 * */
	public void setFunction(Function function);
}
