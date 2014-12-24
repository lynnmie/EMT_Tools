package cn.edu.buaa.sei.SVI.struct.group;


import cn.edu.buaa.sei.SVI.struct.core.variable.TypedVariable;

/**
 * GroupVariable is a Variable used to store Set values.
 * */
public interface GroupVariable extends TypedVariable,AtomicGroupStruct{
	/**
	 * Return the value to which the bindable refers.
	 * @exception Exception {Referencer} null referring variable.
	 * */
	public Group read() throws Exception;
	/**
	 * Write the value to the bindable space.
	 * @exception Exception {TypedVariable} class cast failed.
	 * */
	public void assign(Group val) throws Exception;
}
