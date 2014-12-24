package cn.edu.buaa.sei.SVI.struct.core.function.impl;

import java.util.HashMap;
import java.util.Map;

import cn.edu.buaa.sei.SVI.struct.core.CompositeStruct;
import cn.edu.buaa.sei.SVI.struct.core.Struct;
import cn.edu.buaa.sei.SVI.struct.core.StructArray;
import cn.edu.buaa.sei.SVI.struct.core.function.Context;

public class ContextImpl implements Context{
	Map<String,Struct> map = new HashMap<String,Struct>();
	Context parent;
	CompositeStruct container;
	
	public ContextImpl(Context parent) throws Exception{
		this.parent=parent;
		if(parent!=null)
			parent.addChildStruct(this);
		this.container = new StructArray();
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
	public Context getParentContext() {return this.parent;}

	@Override
	public Struct getStruct(String name) throws Exception {
		if(name==null)
			throw new Exception("Null name is invalid in accessing Struct in context.");
		if(this.map.containsKey(name))
			return this.map.get(name);
		else if(this.parent!=null)
			return this.parent.getStruct(name);
		else
			throw new Exception("Undefined accessing name: "+name);
	}

	@Override
	public boolean containStruct(String name) {
		if(this.map.containsKey(name))return true;
		else if(this.parent==null)return false;
		else return this.parent.containStruct(name);
	}

	@Override
	public void addStruct(String name, Struct element) throws Exception {
		if(name==null||element==null)
			throw new NullPointerException("Null Invalid Arguments found");
		if(this.map.containsKey(name))
			throw new Exception("Name Conflict with existing Struct: "+name);
		
		this.map.put(name, element);
		this.addChildStruct(element);
	}

	@Override
	public void removeStruct(String name) throws Exception {
		if(name==null)
			throw new NullPointerException("Null is not in the context");
		else if(!this.map.containsKey(name))
			throw new Exception("Undefined Local Name: "+name);
		else{
			Struct element = this.map.get(name);
			this.map.remove(name);
			this.removeChildStruct(element);
		}
	}
}
