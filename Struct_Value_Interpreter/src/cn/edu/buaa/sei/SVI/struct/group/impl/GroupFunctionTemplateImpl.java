package cn.edu.buaa.sei.SVI.struct.group.impl;

import cn.edu.buaa.sei.SVI.struct.core.CompositeStruct;
import cn.edu.buaa.sei.SVI.struct.core.function.Function;
import cn.edu.buaa.sei.SVI.struct.core.function.impl.FunctionTemplateImpl;
import cn.edu.buaa.sei.SVI.struct.core.variable.Variable;
import cn.edu.buaa.sei.SVI.struct.group.GroupFunction;
import cn.edu.buaa.sei.SVI.struct.group.GroupFunctionTemplate;
import cn.edu.buaa.sei.SVI.struct.group.GroupVariable;

public class GroupFunctionTemplateImpl extends FunctionTemplateImpl implements GroupFunctionTemplate{

	GroupFunctionTemplateImpl(String name, Variable[] arguments,
			CompositeStruct container) throws Exception {
		super(name, arguments, new GroupVariableImpl(OUT_NAME), container);
	}

	@Override
	public void setFunction(Function function) throws Exception {
		if(function==null)
			this.function=null;
		else if(!(function instanceof GroupFunction))
			throw new Exception("GroupFunction Required");
		else this.function=function;
	}
	@Override
	public void setFunction(GroupFunction function) {
		this.function=function;
	}
	@Override
	public GroupFunction getFunction(){
		if(this.function==null)return null;
		return (GroupFunction) this.function;
	}
	@Override
	public GroupVariable getOutput(){
		if(this.out==null)return null;
		else return (GroupVariable) this.out;
	}
	
	@Override
	public String toString(){return "Group "+super.toString();}
	
}
