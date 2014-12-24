package cn.edu.buaa.sei.SVI.struct.core.variable.base;

import cn.edu.buaa.sei.SVI.struct.core.variable.TypedVariable;

public interface StringVariable extends TypedVariable{
	/**
	 * Return the value to which the bindable refers.
	 * @exception Exception {Referencer} null referring variable.
	 * */
	public String read() throws Exception;
	/**
	 * Write the value to the bindable space.
	 * @exception Exception {TypedVariable} class cast failed.
	 * */
	public void assign(String val) throws Exception;
}
