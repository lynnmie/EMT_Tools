package cn.edu.buaa.sei.SVI.struct.core.variable;

/**
 * <i>TypedVariable</i> is a Variable whose type has been decided at first.<br>
 * 1. Library has provided basic TypedVariable: bool, int, long, float, double, char, string
 * 2. UTypedVariable provide users to defines TypedVariable to bind the type for their own defined type.
 * */
public interface TypedVariable extends Variable{
	/**
	 * Checking whether the value to be set is compatible with the binded type.
	 * */
	public boolean compatible(Object val);
	@SuppressWarnings("rawtypes")
	/**
	 * Return the binded type to the variable.
	 * */
	public Class getType();
}
