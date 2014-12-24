package cn.edu.buaa.sei.SVI.struct.core.program;

import cn.edu.buaa.sei.SVI.struct.core.extend.LogicStruct;

/**
 * CirculationPro present the Circulation Structure of Programs, whose execution programs 
 * would be executed if condition keeps true.<br>
 * The children Stuct contains condition and its execution program.
 * */
public interface CirculationPro extends Program{
	/**
	 * Return the condition.
	 * */
	public LogicStruct getCondition();
	/**
	 * Return the execution children.
	 * */
	public Program getExecution();
}
