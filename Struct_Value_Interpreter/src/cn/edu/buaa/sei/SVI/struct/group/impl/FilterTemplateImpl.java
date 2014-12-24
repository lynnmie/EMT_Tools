package cn.edu.buaa.sei.SVI.struct.group.impl;

import cn.edu.buaa.sei.SVI.struct.core.CompositeStruct;
import cn.edu.buaa.sei.SVI.struct.core.Struct;
import cn.edu.buaa.sei.SVI.struct.core.function.Function;
import cn.edu.buaa.sei.SVI.struct.core.variable.Variable;
import cn.edu.buaa.sei.SVI.struct.core.variable.impl.VariableFactory;
import cn.edu.buaa.sei.SVI.struct.group.Group;
import cn.edu.buaa.sei.SVI.struct.group.GroupFunction;
import cn.edu.buaa.sei.SVI.struct.group.GroupVariable;
import cn.edu.buaa.sei.SVI.struct.group.extend.FilterTemplate;
import cn.edu.buaa.sei.SVI.struct.logic.LogicFunction;
import cn.edu.buaa.sei.SVI.struct.logic.LogicFunctionTemplate;

public class FilterTemplateImpl implements FilterTemplate{
	
	CompositeStruct container;
	String name="filter";
	Variable[] arguments = new Variable[2];
	GroupVariable out;
	
	GroupFunction funciton;
	
	/**
	 * Children: [out,a1,a2]<br>
	 * Format: filter(group,condition(x))
	 * */
	public FilterTemplateImpl(CompositeStruct container) throws Exception{
		if(container!=null)
			throw new Exception("Container is null");
		
		this.container=container;
		this.out=new GroupVariableImpl(OUT_NAME);
		this.arguments[0]=new GroupVariableImpl("group");
		this.arguments[1]=VariableFactory.createFreeVariable("condition");
		
		this.container.addChildStruct(this.out);
		this.container.addChildStruct(this.arguments[0]);
		this.container.addChildStruct(this.arguments[1]);
	}

	@Override
	public GroupVariable getOutput() {return this.out;}

	@Override
	public GroupFunction getFunction() {return this.funciton;}
	@Override
	public void setFunction(GroupFunction function) {this.funciton=function;}
	@Override
	public void setFunction(Function function) throws Exception {
		if(function==null)this.funciton=null;
		else if(function instanceof GroupFunction)this.funciton=(GroupFunction) function;
		else throw new Exception("GroupFunction is required");
	}

	@Override
	public Struct[] getChildrenStructs() {return this.container.getChildrenStructs();}
	@Override
	public void addChildStruct(Struct child) throws Exception {this.container.addChildStruct(child);}
	@Override
	public void removeChildStruct(Struct child) throws Exception {this.container.removeChildStruct(child);}
	@Override
	public boolean containChildStruct(Struct child) {return this.container.containChildStruct(child);}
	@Override
	public int getChildrenStructSize() {return this.container.getChildrenStructSize();}

	@Override
	public String getName() {return this.name;}
	@Override
	public Variable[] getArguments() {return this.arguments;}

	@Override
	public void assign(Object[] values) throws Exception {
		if(values==null||values.length!=2)
			throw new Exception("Invalid length of values {required 2}");
		
		if(values[0]==null||!(values[0] instanceof Group))
			throw new Exception("arguments[0] requires Group");
		if(values[1]==null||!(values[1] instanceof LogicFunction))
			throw new Exception("arguments[1] requires LogicFunction");
		
		LogicFunction function = (LogicFunction) values[1];
		LogicFunctionTemplate temp = function.getTemplate();
		if(temp==null||temp.getArguments()==null||temp.getArguments().length==0)
			throw new Exception("arguments[1] format: P(x)");
		
		arguments[0].assign(values[0]);
		arguments[1].assign(values[1]);
	}
	@Override
	public void assign(Group group, LogicFunction function)
			throws Exception {
		if(group==null||function==null)
			throw new Exception("Null Values error");
		
		LogicFunctionTemplate temp = function.getTemplate();
		if(temp==null||temp.getArguments()==null||temp.getArguments().length==0)
			throw new Exception("arguments[1] format: P(x)");
		
		arguments[0].assign(group);
		arguments[1].assign(function);
	}
	
	@Override
	public String toString(){
		return "filter(A,condition(x))";
	}

}
