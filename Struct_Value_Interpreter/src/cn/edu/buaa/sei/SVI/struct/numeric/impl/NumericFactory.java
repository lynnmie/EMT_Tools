package cn.edu.buaa.sei.SVI.struct.numeric.impl;

import cn.edu.buaa.sei.SVI.struct.core.StructArray;
import cn.edu.buaa.sei.SVI.struct.core.extend.NumericStruct;
import cn.edu.buaa.sei.SVI.struct.core.function.Context;
import cn.edu.buaa.sei.SVI.struct.core.function.FunctionBody;
import cn.edu.buaa.sei.SVI.struct.core.variable.Variable;
import cn.edu.buaa.sei.SVI.struct.logic.LogicExpression;
import cn.edu.buaa.sei.SVI.struct.logic.impl.LogicFactory;
import cn.edu.buaa.sei.SVI.struct.numeric.Addition;
import cn.edu.buaa.sei.SVI.struct.numeric.Division;
import cn.edu.buaa.sei.SVI.struct.numeric.Mod;
import cn.edu.buaa.sei.SVI.struct.numeric.Multiplication;
import cn.edu.buaa.sei.SVI.struct.numeric.NaturalVariable;
import cn.edu.buaa.sei.SVI.struct.numeric.NumericExpression;
import cn.edu.buaa.sei.SVI.struct.numeric.NumericFunction;
import cn.edu.buaa.sei.SVI.struct.numeric.NumericFunctionTemplate;
import cn.edu.buaa.sei.SVI.struct.numeric.NumericOperator;
import cn.edu.buaa.sei.SVI.struct.numeric.RationalVariable;
import cn.edu.buaa.sei.SVI.struct.numeric.RealVariable;
import cn.edu.buaa.sei.SVI.struct.numeric.Substraction;
import cn.edu.buaa.sei.SVI.struct.numeric.ZIntegerVariable;
import cn.edu.buaa.sei.SVI.struct.numeric.logic.Bigger;
import cn.edu.buaa.sei.SVI.struct.numeric.logic.EBigger;
import cn.edu.buaa.sei.SVI.struct.numeric.logic.ESmaller;
import cn.edu.buaa.sei.SVI.struct.numeric.logic.Equal;
import cn.edu.buaa.sei.SVI.struct.numeric.logic.Smaller;

public class NumericFactory {
	public static NaturalVariable createNaturalVariable(String name) throws Exception{
		return new NaturalVariableImpl(name);
	}
	public static ZIntegerVariable createZIntegerVariable(String name) throws Exception{
		return new ZIntegerVariableImpl(name);
	}
	public static RationalVariable createRationalVariable(String name) throws Exception{
		return new RationalVariableImpl(name);
	}
	public static RealVariable createRealVariable(String name) throws Exception{
		return new RealVariableImpl(name);
	}
	
	public static NumericExpression createAddition(NumericStruct left,
			NumericStruct right) throws Exception{
		Addition op = new AdditionImpl(left,right,new StructArray());
		return new NumericExpressionImpl(op,new StructArray());
	}
	public static NumericExpression createSubstract(NumericStruct left,
			NumericStruct right) throws Exception{
		Substraction op = new SubstractionImpl(left,right,new StructArray());
		return new NumericExpressionImpl(op,new StructArray());
	}
	public static NumericExpression createMultiplication(NumericStruct left,
			NumericStruct right) throws Exception{
		Multiplication op = new MultiplicationImpl(left,right,new StructArray());
		return new NumericExpressionImpl(op,new StructArray());
	}
	public static NumericExpression createMod(NumericStruct left,
			NumericStruct right) throws Exception{
		Mod op = new ModImpl(left,right,new StructArray());
		return new NumericExpressionImpl(op,new StructArray());
	}
	public static NumericExpression createDivision(NumericStruct left,
			NumericStruct right) throws Exception{
		Division op = new DivisionImpl(left,right,new StructArray());
		return new NumericExpressionImpl(op,new StructArray());
	}
	
	public static NumericFunctionTemplate createNaturalFunctionTemplate(String name,
			Variable[] arguments) throws Exception{
		return new NumericFunctionTemplateImpl(name,arguments,
				new NaturalVariableImpl(NumericFunctionTemplateImpl.OUT_NAME),new StructArray());
	}
	public static NumericFunctionTemplate createZIntegerFunctionTemplate(String name,
			Variable[] arguments) throws Exception{
		return new NumericFunctionTemplateImpl(name,arguments,
				new ZIntegerVariableImpl(NumericFunctionTemplateImpl.OUT_NAME),new StructArray());
	}
	public static NumericFunctionTemplate createRationalFunctionTemplate(String name,
			Variable[] arguments) throws Exception{
		return new NumericFunctionTemplateImpl(name,arguments,
				new RationalVariableImpl(NumericFunctionTemplateImpl.OUT_NAME),new StructArray());
	}
	public static NumericFunctionTemplate createRealFunctionTemplate(String name,
			Variable[] arguments) throws Exception{
		return new NumericFunctionTemplateImpl(name,arguments,
				new RealVariableImpl(NumericFunctionTemplateImpl.OUT_NAME),new StructArray());
	}
	public static NumericFunctionTemplate createVoidFunctionTemplate(String name,
			Variable[] arguments) throws Exception{
		return new NumericFunctionTemplateImpl(name,arguments,
				null,new StructArray());
	}
	
	public static NumericFunction createNumericFunction(NumericFunctionTemplate template,Context context,FunctionBody body) throws Exception{
		NumericFunction function = new NumericFunctionImpl(template,new StructArray());
		function.setContext(context);
		function.setBody(body);
		return function;
	}

	public static LogicExpression createBigger(NumericStruct left,NumericStruct right) throws Exception{
		Bigger op = new BiggerImpl(left,right,new StructArray());
		return LogicFactory.createLogicExpression(op);
	}
	public static LogicExpression createSmaller(NumericStruct left,NumericStruct right) throws Exception{
		Smaller op = new SmallerImpl(left,right,new StructArray());
		return LogicFactory.createLogicExpression(op);
	}
	public static LogicExpression createEqual(NumericStruct left,NumericStruct right) throws Exception{
		Equal op = new EqualImpl(left,right,new StructArray());
		return LogicFactory.createLogicExpression(op);
	}
	public static LogicExpression createESmaller(NumericStruct left,NumericStruct right) throws Exception{
		ESmaller op = new ESmallerImpl(left,right,new StructArray());
		return LogicFactory.createLogicExpression(op);
	}
	public static LogicExpression createEBigger(NumericStruct left,NumericStruct right) throws Exception{
		EBigger op = new EBiggerImpl(left,right,new StructArray());
		return LogicFactory.createLogicExpression(op);
	}
	
	public static NumericExpression createNumericExpression(NumericOperator op)throws Exception{
		return new NumericExpressionImpl(op,new StructArray());
	}
	
}
