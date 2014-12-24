package cn.edu.buaa.sei.SVI.struct.group.extend;

import cn.edu.buaa.sei.SVI.struct.core.function.Function;
import cn.edu.buaa.sei.SVI.struct.group.Group;
import cn.edu.buaa.sei.SVI.struct.group.GroupFunctionTemplate;

/**
 * TableMapTemplate is the FunctionTemplate for TableMap.<br>
 * TableMap is defined as "tmapper(A,P): return B = {(x,y)|y = P(x) where x in A}"<br>
 * MapTemplate has two arguments as [A,P] in which A is Group and P is Function.<br>
 * Note: P has exactly one argument which receives the value in Group A, and Mapper function
 * does not need FunctionBody because system automatically interpret the function by its template.<br>
 * Such a Function without body and can be executed is called <b>Native Function</b>
 * */
public interface TableMapTemplate extends GroupFunctionTemplate{
	/**
	 * To assign the value to the 2 arguments in tableMapper(group,function)
	 * @exception Exception group==null||function==null
	 * @exception Exception function is not template of: P(x) in which x receives elements in group.
	 * */
	public void assign(Group group,Function function) throws Exception;
	
	public static class _Pair{
		Object key,value;
		public _Pair(Object key,Object value) throws Exception{
			if(key==null)throw new Exception("Null key is invalid");
			this.key=key;this.value=value;
		}
		public Object getKey(){return this.key;}
		public Object getValue(){return this.value;}
	}
}
