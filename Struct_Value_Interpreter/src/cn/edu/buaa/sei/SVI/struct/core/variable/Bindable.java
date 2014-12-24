package cn.edu.buaa.sei.SVI.struct.core.variable;

import cn.edu.buaa.sei.SVI.struct.core.ProtoStruct;

/**
 * Bindable <i>{abstract}</i> is a memory block in which stores the values.<br>
 * A Bindable with name is called <b>Variable</b>.<br>
 * A Pure Bindable is a variable without name used by User.
 * */
public interface Bindable extends ProtoStruct{
	/**
	 * Return the value to which the bindable refers.
	 * @exception Exception {Referencer} null referring variable.
	 * */
	public Object read() throws Exception;
	/**
	 * Write the value to the bindable space.
	 * @exception Exception {TypedVariable} class cast failed.
	 * */
	public void assign(Object val) throws Exception;
}
