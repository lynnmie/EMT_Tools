package cn.edu.buaa.sei.SVI.struct.core.function;

import cn.edu.buaa.sei.SVI.struct.core.CompositeStruct;

/**
 * <i>Function</i> is a <i>CompositeStruct</i> which could be called to be runned to compute its output variable 
 * based on Input Arguments in <i>FunctionTemplate</i> and <i>Context</i>.<br>
 * <i>Context</i> provides external values to be used by using name to search.<br>
 * <i>FunctionTemplate</i> defines the Standard Structure of Function Definition, and Data Pipe.
 * */
public interface Function extends CompositeStruct{
	public FunctionTemplate getTemplate();
	public Context getContext();
	public FunctionBody getBody();
	public void setContext(Context context);
	public void setBody(FunctionBody body);
}
