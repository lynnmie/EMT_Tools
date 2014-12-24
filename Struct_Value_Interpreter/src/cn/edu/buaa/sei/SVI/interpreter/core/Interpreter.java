package cn.edu.buaa.sei.SVI.interpreter.core;

import cn.edu.buaa.sei.SVI.struct.core.Struct;

/**
 * <i>Interpreter</i> computes the result of a given Struct Element.
 * */
public interface Interpreter {
	//public static InterpreterRegister register = SInterpreterRegister.register;
	/**
	 * Interpret the Struct to produce its result.
	 * @exception Exception input==null;
	 * @exception Exception state machine return EXCEPTION {children struct computation}
	 * @exception Exception child computation failed {state machine return UNREADY} {depends}
	 * */
	public Object interpret(Struct input) throws Exception;
}
