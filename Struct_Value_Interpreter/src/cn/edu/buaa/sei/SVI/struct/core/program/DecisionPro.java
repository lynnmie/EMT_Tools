package cn.edu.buaa.sei.SVI.struct.core.program;

import cn.edu.buaa.sei.SVI.struct.core.extend.LogicStruct;

/**
 * DecisionPro presents the Decision Structure of Program.
 * */
public interface DecisionPro extends Program{
	/**
	 * Return the condition which could be interpreted to boolean result.
	 * */
	public LogicStruct getCondition();
	/**
	 * Return the child executed program which would be runned if condition is true.
	 * */
	public Program getExecution();
}
