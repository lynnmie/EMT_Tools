package cn.edu.buaa.sei.SVI.struct.logic.impl;

import cn.edu.buaa.sei.SVI.struct.core.CompositeStruct;
import cn.edu.buaa.sei.SVI.struct.core.function.Function;
import cn.edu.buaa.sei.SVI.struct.core.function.impl.FunctionTemplateImpl;
import cn.edu.buaa.sei.SVI.struct.core.variable.Variable;
import cn.edu.buaa.sei.SVI.struct.logic.LogicFunction;
import cn.edu.buaa.sei.SVI.struct.logic.LogicFunctionTemplate;
import cn.edu.buaa.sei.SVI.struct.logic.LogicVariable;

public class LogicFunctionTemplateImpl extends FunctionTemplateImpl implements LogicFunctionTemplate{
	
	protected LogicFunctionTemplateImpl(String name, Variable[] arguments,
			CompositeStruct container) throws Exception {
		super(name, arguments, new LogicVariableImpl(OUT_NAME), container);
	}

	public static final String OUT_NAME = "out";
	
	@Override
	public LogicVariable getOutput(){
		if(this.out==null)return null;
		else return (LogicVariable) this.out;
	}
	@Override
	public LogicFunction getFunction(){
		if(this.function==null)return null;
		else return (LogicFunction) this.function;
	}
	@Override
	public void setFunction(Function function) throws Exception {
		if(function==null)this.function=null;
		else if(function instanceof LogicFunction)this.function=function;
		else throw new Exception("Function must be LogicFunction");
	}
	@Override
	public void setFunction(LogicFunction function) {
		this.function=function;
	}
	
	@Override
	public String toString(){return "boolean "+super.toString();}
	
}
