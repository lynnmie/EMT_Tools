package cn.edu.buaa.sei.SVI.struct.group.impl;

import cn.edu.buaa.sei.SVI.struct.core.CompositeStruct;
import cn.edu.buaa.sei.SVI.struct.core.Struct;
import cn.edu.buaa.sei.SVI.struct.core.function.Function;
import cn.edu.buaa.sei.SVI.struct.core.function.FunctionTemplate;
import cn.edu.buaa.sei.SVI.struct.core.variable.Variable;
import cn.edu.buaa.sei.SVI.struct.core.variable.impl.VariableFactory;
import cn.edu.buaa.sei.SVI.struct.group.Group;
import cn.edu.buaa.sei.SVI.struct.group.GroupFunction;
import cn.edu.buaa.sei.SVI.struct.group.GroupVariable;
import cn.edu.buaa.sei.SVI.struct.group.extend.MapTemplate;
import cn.edu.buaa.sei.SVI.struct.logic.LogicFunction;

public class MapTemplateImpl implements MapTemplate{
	
	String name="mapper";
	Variable[] arguments = new Variable[2];
	GroupVariable out;
	
	GroupFunction function;
	CompositeStruct container;
	
	/**
	 * Children: [out,a1,a2]<br>
	 * Format: mapper(group,F(x))
	 * */
	public MapTemplateImpl(CompositeStruct container) throws Exception{
		if(container==null)throw new Exception("Null container is invalid");
		 this.out = new GroupVariableImpl(OUT_NAME);
		 
		 this.arguments[0]=new GroupVariableImpl("group");
		 this.arguments[1]=VariableFactory.createFreeVariable("F");
		 
		 this.container.addChildStruct(this.out);
		 this.container.addChildStruct(this.arguments[0]);
		 this.container.addChildStruct(this.arguments[1]);
	}

	@Override
	public String getName() {return this.name;}
	@Override
	public Variable[] getArguments() {return this.arguments;}
	@Override
	public GroupVariable getOutput() {return this.out;}

	@Override
	public GroupFunction getFunction() {return this.function;}
	@Override
	public void setFunction(GroupFunction function) {this.function=function;}
	@Override
	public void setFunction(Function function) throws Exception {
		if(function==null)this.function=null;
		else if(function instanceof GroupFunction)this.function=(GroupFunction) function;
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
	public void assign(Object[] values) throws Exception {
		if(values==null||values.length!=2)
			throw new Exception("Invalid length of values {required 2}");
		
		if(values[0]==null||!(values[0] instanceof Group))
			throw new Exception("arguments[0] requires Group");
		if(values[1]==null||!(values[1] instanceof LogicFunction))
			throw new Exception("arguments[1] requires LogicFunction");
		
		Function function = (Function) values[1];
		FunctionTemplate temp = function.getTemplate();
		if(temp==null||temp.getArguments()==null||temp.getArguments().length==0)
			throw new Exception("arguments[1] format: P(x)");
		
		arguments[0].assign(values[0]);
		arguments[1].assign(values[1]);
	}
	@Override
	public void assign(Group group, Function function) throws Exception {
		if(group==null||function==null)
			throw new Exception("Null Values error");
		
		FunctionTemplate temp = function.getTemplate();
		if(temp==null||temp.getArguments()==null||temp.getArguments().length==0)
			throw new Exception("arguments[1] format: P(x)");
		
		arguments[0].assign(group);
		arguments[1].assign(function);
	}

	@Override
	public String toString(){
		return "mapper(A,F(x))";
	}
}
