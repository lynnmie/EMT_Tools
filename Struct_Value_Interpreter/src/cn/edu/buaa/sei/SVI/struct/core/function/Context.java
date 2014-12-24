package cn.edu.buaa.sei.SVI.struct.core.function;

import cn.edu.buaa.sei.SVI.struct.core.CompositeStruct;
import cn.edu.buaa.sei.SVI.struct.core.Struct;

/**
 * Context provides an access to the External Elements {mainly Variable & Function} so to 
 * be used between Functions as a data pipe between them.<br>
 * 1) In theory, there is exactly one Global Context in which all functions are defined, whose parent is null.<br>
 * 2) In theory, each function contains one Local Context. <br>
 * 3) In this system, we allow different functions share the same one.<br>
 * */
public interface Context extends CompositeStruct{
	/**
	 * Return the parent context.
	 * */
	public Context getParentContext();
	/**
	 * Return the Struct in Local Context or its Parent Context by using Name Index.
	 * */
	public Struct getStruct(String name) throws Exception;
	/**
	 * Checking whether the local context or its parent context contain Struct with the name Index.
	 * */
	public boolean containStruct(String name);
	/**
	 * Add a new Struct into the Local Struct. The Struct must be <b>Variable</b> or <b>Function</b>
	 * @exception Exception element==null || name==null
	 * @exception Exception element is not Variable or Function
	 * @exception Exception element's name conflict with other names in element of local context
	 * */
	public void addStruct(String name,Struct element) throws Exception;
	/**
	 * Remove an existing Struct in the Context. The element must be in the Local Context.
	 * @exception Exception name == null
	 * @exception Exception !localMap.conatinKey(name)
	 * */
	public void removeStruct(String name) throws Exception;
}
