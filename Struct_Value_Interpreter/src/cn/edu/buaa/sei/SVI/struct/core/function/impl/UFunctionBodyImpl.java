package cn.edu.buaa.sei.SVI.struct.core.function.impl;

import cn.edu.buaa.sei.SVI.struct.core.CompositeStruct;
import cn.edu.buaa.sei.SVI.struct.core.Struct;
import cn.edu.buaa.sei.SVI.struct.core.StructArray;
import cn.edu.buaa.sei.SVI.struct.core.function.Function;
import cn.edu.buaa.sei.SVI.struct.core.function.UFunctionBody;
import cn.edu.buaa.sei.SVI.struct.core.program.Program;

/**
 * UFunctionBodyImpl provides a general framework to implement the programs defined by users.
 * */
public class UFunctionBodyImpl implements UFunctionBody{
	Function function;
	CompositeStruct container;
	Program program;
	
	public UFunctionBodyImpl(Program program) throws Exception{
		if(program==null)
			throw new Exception("Function Body must not be Null");
		this.program=program;
		this.addChildStruct(program);
		container = new StructArray();
	}

	@Override
	public Function getFunction() {return this.function;}

	@Override
	public void setFunction(Function function) {this.function=function;}

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
	public Program getProgram() {return this.program;}

}
