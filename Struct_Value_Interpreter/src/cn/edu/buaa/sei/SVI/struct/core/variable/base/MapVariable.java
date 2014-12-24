package cn.edu.buaa.sei.SVI.struct.core.variable.base;

import java.util.Map;

import cn.edu.buaa.sei.SVI.struct.core.variable.TypedVariable;

public interface MapVariable extends TypedVariable{
	/**
	 * Return the value to which the bindable refers.
	 * */
	@SuppressWarnings("rawtypes")
	public Map read() throws Exception;
	/**
	 * Write the value to the bindable space.
	 * @exception Exception {TypedVariable} class cast failed.
	 * */
	@SuppressWarnings("rawtypes")
	public void assign(Map val) throws Exception;
}
