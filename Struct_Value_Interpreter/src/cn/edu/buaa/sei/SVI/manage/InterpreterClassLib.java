package cn.edu.buaa.sei.SVI.manage;

import java.util.Set;

import cn.edu.buaa.sei.SVI.interpreter.core.Interpreter;

public interface InterpreterClassLib {
	/**
	 * Return all the loaded classes of Interpreters.
	 * */
	@SuppressWarnings("rawtypes")
	public Set<Class> getLoadedInterpreters();
	/**
	 * Checking whether a given interpreter class has been loaded into the Accessor.
	 * */
	@SuppressWarnings("rawtypes")
	public boolean isLoaded(Class itype);
	/**
	 * Load the specified classes of Interpreters from a given resource.
	 * */
	@SuppressWarnings("rawtypes")
	public void load(Class itype) throws Exception;
	/**
	 * Return a new Interpreter for a given type.
	 * @exception Exception itype == null
	 * @exception Exception itype cannot create instances.
	 * */
	@SuppressWarnings("rawtypes")
	public Interpreter createInterpreter(Class itype) throws Exception;
}
