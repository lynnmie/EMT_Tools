package cn.edu.buaa.sei.SVI.struct.logic;

import cn.edu.buaa.sei.SVI.struct.core.function.Function;

/**
 * LogicFunction is the <i>Function</i> used in Logic Inference.<br>
 * LogicFunction's template is a LogicFunctionTemplate whose output variable is Boolean Variable.
 * */
public interface LogicFunction extends CompositeLogicStruct,Function{
	/**
	 * Return the template of the function.
	 * */
	public LogicFunctionTemplate getTemplate();
}
