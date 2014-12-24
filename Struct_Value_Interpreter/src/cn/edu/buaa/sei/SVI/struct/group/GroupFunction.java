package cn.edu.buaa.sei.SVI.struct.group;

import cn.edu.buaa.sei.SVI.struct.core.function.Function;

/**
 * GroupFunction is the Function used in Group Computation.<br>
 * GroupFunctionTemplate is the template used in GroupFunction.
 * */
public interface GroupFunction extends CompositeGroupStruct,Function{
	public GroupFunctionTemplate getTemplate();
}
