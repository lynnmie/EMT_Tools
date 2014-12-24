package cn.edu.buaa.sei.SVI.struct.group;

import cn.edu.buaa.sei.SVI.struct.core.function.FunctionTemplate;

/**
 * GroupFunctionTemplate is a <i>FunctionTemplate</i> which returns Group as its result.<br>
 * GroupFunctionTemplate is a <i>FunctionTemplate</i> which used in GroupFunction.
 * */
public interface GroupFunctionTemplate extends CompositeGroupStruct,FunctionTemplate{
	public static final String OUT_NAME = "out";
	/**
	 * Return the output Argument of the Function {with name "Out"}
	 * */
	public GroupVariable getOutput();
	/**
	 * Return the Function it is owned.
	 * */
	public GroupFunction getFunction();
	/**
	 * Set the Function it is owned.
	 * */
	public void setFunction(GroupFunction function);
}
