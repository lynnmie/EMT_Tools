package cn.edu.buaa.sei.SVI.struct.numeric.impl;

import cn.edu.buaa.sei.SVI.struct.core.CompositeStruct;
import cn.edu.buaa.sei.SVI.struct.core.function.Function;
import cn.edu.buaa.sei.SVI.struct.core.function.impl.FunctionTemplateImpl;
import cn.edu.buaa.sei.SVI.struct.core.variable.Variable;
import cn.edu.buaa.sei.SVI.struct.numeric.NaturalVariable;
import cn.edu.buaa.sei.SVI.struct.numeric.NumericFunction;
import cn.edu.buaa.sei.SVI.struct.numeric.NumericFunctionTemplate;
import cn.edu.buaa.sei.SVI.struct.numeric.NumericVariable;
import cn.edu.buaa.sei.SVI.struct.numeric.RationalVariable;
import cn.edu.buaa.sei.SVI.struct.numeric.RealVariable;
import cn.edu.buaa.sei.SVI.struct.numeric.ZIntegerVariable;

public class NumericFunctionTemplateImpl extends FunctionTemplateImpl implements NumericFunctionTemplate{
	public static final String OUT_NAME = "out";
	
	protected NumericFunctionTemplateImpl(String name, Variable[] arguments,
			NumericVariable out, CompositeStruct container) throws Exception {
		super(name, arguments, out, container);
	}
	
	@Override
	public NumericVariable getOutput(){
		if(this.out==null)return null;
		else return (NumericVariable) this.out;
	}
	@Override
	public NumericFunction getFunction(){
		if(this.function==null)return null;
		else return (NumericFunction) this.function;
	}

	@Override
	public void setFunction(Function function) throws Exception {
		if(function==null)this.function=null;
		else if(function instanceof NumericFunction)this.function=function;
		else throw new Exception("Function must be NumericFunction");
	}

	@Override
	public void setFunction(NumericFunction function) {this.function=function;}
	
	@Override
	public String toString(){
		StringBuilder code = new StringBuilder();
		
		if(this.out==null)code.append("void ");
		else if(this.out instanceof NaturalVariable)code.append("Natural ");
		else if(this.out instanceof ZIntegerVariable)code.append("Integer ");
		else if(this.out instanceof RationalVariable)code.append("Rational");
		else if(this.out instanceof RealVariable)code.append("Real ");
		
		code.append(super.toString());
		
		return code.toString();
	}
}
