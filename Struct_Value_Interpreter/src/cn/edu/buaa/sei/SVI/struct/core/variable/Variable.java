package cn.edu.buaa.sei.SVI.struct.core.variable;

/**
 * Variable <i>{abstract}</i> is a Bindable with a name.<br>
 * 1. Variable could be <i>TypedVariable</i> whose value type has been set.<br>
 * 2. Variable could be FreeVariable whose type is dynamically decided.<br>
 * 3. Variable could be Referencer whose value is stored in a referred Variable.<br>
 * 4. Variable could be Parameter whose value is used in Function.<br>
 * */
public interface Variable extends Bindable{
	
	/**
	 * Return the variable name.
	 * */
	public String getName();
}
