package cn.edu.buaa.sei.SVI.manage;

import java.util.Set;

import cn.edu.buaa.sei.SVI.struct.core.Struct;
import cn.edu.buaa.sei.SVI.struct.core.expression.Expression;
import cn.edu.buaa.sei.SVI.struct.core.function.Function;
import cn.edu.buaa.sei.SVI.struct.core.variable.Bindable;

/**
 * StructStateManager is a manager to manage the state of any Structs that has been put in
 * the manager.
 * */
public interface StructStateManager {
	/**
	 * Put a new Struct into the Manager as top, and all of its children...
	 * */
	public void put(Struct top);
	/**
	 * Return all the roots of Struct that has been loaded in the manager.
	 * */
	public Set<Struct> getTops();
	/**
	 * Return all the variables in the manager {so to be assigned}
	 * */
	public Set<Bindable> getVariables();
	/**
	 * Return all the expressions in the manager {so to check their values}
	 * */
	public Set<Expression> getExpressions();
	/**
	 * Return all the functions in the manager {so to check their values}
	 * */
	public Set<Function> getFunctions();
	
	/**
	 * Checking whether a given Struct has been loaded in the manager.
	 * */
	public boolean containStruct(Struct element);
	/**
	 * Set the value of a given Struct x as y.
	 * @exception Exception x==null
	 * @exception Exception this.containStruct(x)==false
	 * */
	public void setState(Struct x,Object y) throws Exception;
	/**
	 * Return the current state {result of interpretation} of a given Struct.
	 * */
	public Object getState(Struct x) throws Exception;
}
