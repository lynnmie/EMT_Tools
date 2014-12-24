package cn.edu.buaa.sei.SVI.struct.core.variable.base;

import java.util.List;

import cn.edu.buaa.sei.SVI.struct.core.variable.TypedVariable;

public interface ListVariable extends TypedVariable{
	/**
	 * Return the value to which the bindable refers.
	 * */
	@SuppressWarnings("rawtypes")
	public List read() throws Exception;
	/**
	 * Write the value to the bindable space.
	 * @exception Exception {TypedVariable} class cast failed.
	 * */
	@SuppressWarnings("rawtypes")
	public void assign(List val) throws Exception;
}
