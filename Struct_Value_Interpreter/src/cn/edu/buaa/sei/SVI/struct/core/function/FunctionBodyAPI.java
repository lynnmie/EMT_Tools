package cn.edu.buaa.sei.SVI.struct.core.function;

/**
 * <i>FunctionBodyAPI</i> allows programmers to develop new function or operation into new library.<br>
 * The Implementation of the <i>FunctionBodyAPI</i> depends on the execution of execute().<br>
 * In execute(), programmers could use input data or set output data by using this.getFunction().getTemplate() or this.getFunction().getContext().<br>
 * The behaviours depends on the execute(). 
 * */
public interface FunctionBodyAPI extends FunctionBody{
	/**
	 * Executing the implementation of the function body.<br>
	 * Using Input Data in Context and Template and set Output in Template.
	 * */
	public void execute() throws Exception;
}
