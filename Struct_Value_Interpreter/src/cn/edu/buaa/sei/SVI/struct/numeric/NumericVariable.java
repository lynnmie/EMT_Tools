package cn.edu.buaa.sei.SVI.struct.numeric;

import cn.edu.buaa.sei.SVI.struct.core.variable.TypedVariable;

/**
 * <i>NumericVariable</i> is a <i>NumericStruct</i> which could be computed to produce numeric result.<br>
 * <i>NumericVariable</i> is a <i>TypedVariable</i> which need to be set with Number Value.
 * */
public interface NumericVariable extends AtomicNumericStruct,TypedVariable{
	/**
	 * Return the value to which the bindable refers.
	 * @exception Exception {Referencer} null referring variable.
	 * */
	public Number read() throws Exception;
	/**
	 * Write the value to the bindable space.
	 * @exception Exception {TypedVariable} class cast failed.
	 * */
	public void assign(Number val) throws Exception;
}
