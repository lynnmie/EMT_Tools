package cn.edu.buaa.sei.SVI.struct.core.function.impl;

import cn.edu.buaa.sei.SVI.struct.core.CompositeStruct;
import cn.edu.buaa.sei.SVI.struct.core.Struct;
import cn.edu.buaa.sei.SVI.struct.core.function.Function;
import cn.edu.buaa.sei.SVI.struct.core.function.FunctionTemplate;
import cn.edu.buaa.sei.SVI.struct.core.variable.Bindable;
import cn.edu.buaa.sei.SVI.struct.core.variable.Variable;

public abstract class FunctionTemplateImpl implements FunctionTemplate{
	
	protected CompositeStruct container;
	protected String name;
	protected Variable[] arguments;
	protected Bindable out;
	
	protected Function function;
	
	/**
	 * Children: [out,a1,a2,...,an]
	 * */
	protected FunctionTemplateImpl(String name,Variable[] arguments,
			Bindable out,CompositeStruct container) throws Exception{
		if(name==null||container==null)
			throw new Exception("Name|Container should not be Null");
		
		this.name=name;
		this.arguments=arguments;
		this.out=out;
		this.container=container;
		
		if(this.out!=null)this.container.addChildStruct(out);
		if(this.arguments!=null)
			for(int i=0;i<this.arguments.length;i++){
				Variable ai = this.arguments[i];
				if(ai==null)throw new Exception("arguments["+i+"] is Null");
				this.container.addChildStruct(ai);
			}
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
	public Bindable getOutput() {return this.out;}

	@Override
	public void assign(Object[] values) throws Exception {
		if(this.arguments==null||this.arguments.length==0){
			if(values!=null && values.length!=0)
				throw new Exception("Null arguments require zero values for input");
		}
		else{
			if(values==null)
				throw new Exception("Values required for: "+this.arguments.length);
			if(values.length!=this.arguments.length)
				throw new Exception("Arguments length matches failed: "+this.arguments.length+"<--"+values.length);
			
			for(int i=0;i<this.arguments.length;i++)
				this.arguments[i].assign(values[i]);
		}
	}

	@Override
	public Function getFunction() {return this.function;}
	
	@Override
	public String toString(){
		StringBuilder code = new StringBuilder();
		
		code.append(this.name).append("(");
		if(this.arguments!=null)
			for(int i=0;i<this.arguments.length;i++){
				code.append(this.arguments[i].getName());
				if(i!=this.arguments.length-1)
					code.append(",");
			}
		code.append(")");
		
		return code.toString();
	}
}
