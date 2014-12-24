package cn.edu.buaa.sei.SVI.manage;

import cn.edu.buaa.sei.SVI.interpreter.core.Interpreter;
import cn.edu.buaa.sei.SVI.struct.core.Struct;

public interface InterpreterRegisterMachine {
	public StructClassLib getStructClassLibrary();
	public InterpreterClassLib getInterpreterClassLibrary();
	public StructInterpreterClassLinker getLinker();
	
	/**
	 * Loading the register map from a given resource {.i file(xml)|database}<br>
	 * Note: you can load different .i files for many times. Argument <b>force</b>
	 * is set true when you want to overwrite the original links between classes,
	 * while false you want to keep original links.
	 * */
	public void loadRegisterMap(SVIResource resource,boolean force) throws Exception;
	/**
	 * Return Interpreter of a given Struct whose class has been linked {registered} with it.
	 * */
	public Interpreter getInterpreter(Struct element) throws Exception;
	/**
	 * Return Interpreter of a given Struct class which has been linked {registered} with it.
	 * */
	@SuppressWarnings("rawtypes")
	public Interpreter getInterpreter(Class stype) throws Exception;
	
	public Interpreter get(Struct element) throws Exception;
	@SuppressWarnings("rawtypes")
	public Interpreter get(Class stype) throws Exception;
}
