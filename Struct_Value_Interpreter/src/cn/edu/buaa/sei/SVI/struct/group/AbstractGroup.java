package cn.edu.buaa.sei.SVI.struct.group;

import cn.edu.buaa.sei.SVI.struct.core.variable.Variable;
import cn.edu.buaa.sei.SVI.struct.logic.LogicFunction;

/**
 * AbstractGroup is a group that describe Group in logic function like:<br>
 * <b>{x|P(x)=true}</b> in which P(x) is the logic function.
 * */
public interface AbstractGroup extends Group{
	/**
	 * Return the condition {formation: P(x)}
	 * */
	public LogicFunction getCondition();
	/**
	 * Return the variable in the condition function {x in P(x)}
	 * */
	public Variable getVariable();
	/**
	 * Return the domain in which we talked about this group.
	 * */
	public Group getDomain();
}
