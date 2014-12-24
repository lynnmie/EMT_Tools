package cn.edu.buaa.sei.SVI.struct.group.extend;

import cn.edu.buaa.sei.SVI.struct.core.function.Function;
import cn.edu.buaa.sei.SVI.struct.group.Group;
import cn.edu.buaa.sei.SVI.struct.group.GroupFunctionTemplate;

/**
 * MapTemplate is the FunctionTemplate for Mapper.<br>
 * Mapper is defined as "mapper(A,P): return B = {y|y = P(x) where x in A}"<br>
 * MapTemplate has two arguments as [A,P] in which A is Group and P is Function.<br>
 * Note: P has exactly one argument which receives the value in Group A, and Mapper function
 * does not need FunctionBody because system automatically interpret the function by its template.<br>
 * Such a Function without body and can be executed is called <b>Native Function</b>
 * */
public interface MapTemplate extends GroupFunctionTemplate{
	/**
	 * To assign the value to the 2 arguments in mapper(group,function)
	 * @exception Exception group==null||function==null
	 * @exception Exception function is not template of: P(x) in which x receives elements in group.
	 * */
	public void assign(Group group,Function function) throws Exception;
}
