package cn.edu.buaa.sei.SVI.manage;

import java.util.Set;

public interface StructClassLib {
	/**
	 * Return all the classes of Structs that has been loaded into the system library.
	 * */
	@SuppressWarnings("rawtypes")
	public Set<Class> getLoadedStructClasses(); 
	/**
	 * Checking whether a given Struct Class has been loaded.
	 * */
	@SuppressWarnings("rawtypes")
	public boolean isLoaded(Class stype);
	/**
	 * Load all the needed Struct Classes into lib.
	 * */
	@SuppressWarnings("rawtypes")
	public void load(Class stype) throws Exception;
}
