package cn.edu.buaa.sei.SVI.struct.core.function;

import cn.edu.buaa.sei.SVI.struct.core.CompositeStruct;
import cn.edu.buaa.sei.SVI.struct.core.variable.Bindable;
import cn.edu.buaa.sei.SVI.struct.core.variable.Variable;

/**
 * <i>FunctionTemplate</i> is the template of <i>Function</i> which defines the input arguments and output variable.<br>
 * To call a function {<b>Call Function Statement</b>}, the first is to config the Struct which provide their results to assign the arguments.<br>
 * The arguments are the children of the FunctionTemplate as Struct, but not Function which owns it.
 * */
public interface FunctionTemplate extends CompositeStruct{
	/**
	 * Return the name of Function
	 * */
	public String getName();
	/**
	 * Return the Input Arguments of the Function
	 * */
	public Variable[] getArguments();
	/**
	 * Return the output Argument of the Function {without name}
	 * */
	public Bindable getOutput();
	/**
	 * Assign the value after Interpretation to call the function.<br>
	 * 1. If arguments[i] is Referencer, then it should be assigned with a Variable so to refer to it.<br>
	 * 2. If values[i] is Struct, then it should be interpreted at first and assigned the result to the arguments[i].<br>
	 * 3. The values[i] or its interpretation result should be compatible with arguments[i] if it is a TypedVariable.<br>
	 * @exception Exception values.length!=arguments.length
	 * @exception Exception !arguments[i].compatible(values[i])
	 * */
	public void assign(Object[] values) throws Exception;
	/**
	 * Return the Function it is owned.
	 * */
	public Function getFunction();
	/**
	 * Set the Function it is owned.
	 * @throws Exception Function Type Error
	 * */
	public void setFunction(Function function) throws Exception;
}
