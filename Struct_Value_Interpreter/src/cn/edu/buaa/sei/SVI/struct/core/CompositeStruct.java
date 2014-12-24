package cn.edu.buaa.sei.SVI.struct.core;

/**
 * CompositeStruct <i>{abstract}</i> is a struct with structure.<br>
 * It could be: <b>Expression[Operator]</b>, <b>Function[FunctionTemplate|Context|FunctionBody]</b>
 * */
public interface CompositeStruct extends Struct{
	/**
	 * Return all the children of the CompositeStruct
	 * */
	public Struct[] getChildrenStructs();
	/**
	 * Append a new child Struct into the children list.
	 * @exception Exception child == null
	 * */
	public void addChildStruct(Struct child) throws Exception;
	/**
	 * Remove an existing child Struct into the children list.
	 * @exception Exception child is not in the list
	 * */
	public void removeChildStruct(Struct child) throws Exception;
	/**
	 * Checking whether the child is in the children list.
	 * */
	public boolean containChildStruct(Struct child);
	/**
	 * Return the current size of children list.<br>
	 * <b>Note: the length of children is based on this function, not the struct[].length</b>
	 * */
	public int getChildrenStructSize();
}
