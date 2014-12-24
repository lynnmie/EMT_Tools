package cn.edu.buaa.sei.SVI.interpreter.group;

import cn.edu.buaa.sei.SVI.struct.group.Group;
import cn.edu.buaa.sei.SVI.struct.group.GroupFunctionTemplate;

/**
 * <i>GroupFunctionTemplateInterpreter</i> interpret the native methods. It depends on
 * the type of function template: filter, map, tableMap
 * */
public interface GroupFunctionTemplateInterpreter extends GroupInterpreter{
	/**
	 * Return the result of "interpretation" in native functions.
	 * */
	public Group interpret(GroupFunctionTemplate template) throws Exception;
}
