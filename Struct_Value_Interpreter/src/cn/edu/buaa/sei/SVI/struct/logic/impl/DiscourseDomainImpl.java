package cn.edu.buaa.sei.SVI.struct.logic.impl;

import java.util.Set;

import cn.edu.buaa.sei.SVI.struct.core.CompositeStruct;
import cn.edu.buaa.sei.SVI.struct.core.Struct;
import cn.edu.buaa.sei.SVI.struct.core.variable.Variable;
import cn.edu.buaa.sei.SVI.struct.core.variable.impl.TypedVariableImpl;
import cn.edu.buaa.sei.SVI.struct.core.variable.impl.VariableFactory;
import cn.edu.buaa.sei.SVI.struct.logic.DiscourseDomain;

public class DiscourseDomainImpl extends TypedVariableImpl implements DiscourseDomain{
	
	CompositeStruct container;
	Variable iterator;

	protected DiscourseDomainImpl(String name,CompositeStruct container) throws Exception {
		super(name, Set.class);
		if(container==null)
			throw new Exception("Null Container is invalid");
		
		this.container=container;
		this.iterator = VariableFactory.createFreeVariable(this.name+".iter");
		this.container.addChildStruct(iterator);
	}

	@Override
	public Struct[] getChildrenStructs() {return this.container.getChildrenStructs();}

	@Override
	public void addChildStruct(Struct child) throws Exception {this.container.addChildStruct(child);}

	@Override
	public void removeChildStruct(Struct child) throws Exception {this.container.removeChildStruct(child);}

	@Override
	public boolean containChildStruct(Struct child) {
		return this.container.containChildStruct(child);
	}

	@Override
	public int getChildrenStructSize() {
		return this.container.getChildrenStructSize();
	}

	@SuppressWarnings("rawtypes")
	@Override
	public void assign(Set val) throws Exception {
		this.val=val;
	}

	@Override
	public Variable getIterator() {
		return this.iterator;
	}

	@SuppressWarnings("rawtypes")
	@Override
	public Set read() throws Exception{
		if(this.val==null)return null;
		else return (Set) this.val;
	}
	
	@Override
	public String toString(){return this.name;}
}
