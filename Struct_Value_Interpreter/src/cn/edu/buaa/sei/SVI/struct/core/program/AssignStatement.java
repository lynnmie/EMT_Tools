package cn.edu.buaa.sei.SVI.struct.core.program;

import cn.edu.buaa.sei.SVI.struct.core.Struct;
import cn.edu.buaa.sei.SVI.struct.core.variable.Variable;

public interface AssignStatement extends Statement{
	/**
	 * Return the Variable which would be assigned.
	 * */
	public Variable getVariable();
	/**
	 * Return the <i>Struct</i> whose result would be assigned to the Variable.
	 * */
	public Struct getValueStruct();
}
