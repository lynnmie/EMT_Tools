package cn.edu.buaa.sei.SVI.struct.logic;

import cn.edu.buaa.sei.SVI.struct.core.function.FunctionTemplate;

/**
 * LogicFunctionTemplate is the template of LogicFunction. Its owner could only be LogicFunction.<br>
 * LogicFunction return Logic Variable {Boolean} as its output.
 * */
public interface LogicFunctionTemplate extends CompositeLogicStruct,FunctionTemplate{
	/**
	 * Return the output boolean Argument of the Function {with name "out"}
	 * */
	public LogicVariable getOutput();
	/**
	 * Return the Function it is owned.
	 * */
	public LogicFunction getFunction();
	/**
	 * Set the Function it is owned.
	 * */
	public void setFunction(LogicFunction function);
}
