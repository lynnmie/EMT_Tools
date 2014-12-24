package cn.edu.buaa.sei.SVI.struct.group.extend;

import cn.edu.buaa.sei.SVI.struct.group.Group;
import cn.edu.buaa.sei.SVI.struct.group.GroupFunctionTemplate;
import cn.edu.buaa.sei.SVI.struct.logic.LogicFunction;

/**
 * FilterTemplate is the FunctionTemplate for Filter.<br>
 * Filter is defined as "filter(A,P): return B = {x|(x in A) && P(x)}"<br>
 * FilterTemplate has two arguments as [A,P] in which A is Group and P is LogicFunction.<br>
 * Note: P has exactly one argument which receives the value of Group A, and Filter function
 * does not need FunctionBody because system automatically interpret the function by its template.<br>
 * Such a Function without body and can be executed is called <b>Native Function</b>
 * */
public interface FilterTemplate extends GroupFunctionTemplate{
	/**
	 * To assign the value to the 2 arguments in filter(group,function)
	 * @exception Exception group==null||function==null
	 * @exception Exception function is not template of: P(x) in which x receives elements in group.
	 * */
	public void assign(Group group,LogicFunction function) throws Exception;
}
