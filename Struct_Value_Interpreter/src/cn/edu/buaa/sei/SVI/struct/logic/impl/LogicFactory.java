package cn.edu.buaa.sei.SVI.struct.logic.impl;

import cn.edu.buaa.sei.SVI.struct.core.StructArray;
import cn.edu.buaa.sei.SVI.struct.core.extend.LogicStruct;
import cn.edu.buaa.sei.SVI.struct.core.function.Context;
import cn.edu.buaa.sei.SVI.struct.core.variable.Variable;
import cn.edu.buaa.sei.SVI.struct.logic.AtLeast;
import cn.edu.buaa.sei.SVI.struct.logic.AtMost;
import cn.edu.buaa.sei.SVI.struct.logic.Between;
import cn.edu.buaa.sei.SVI.struct.logic.Conjunction;
import cn.edu.buaa.sei.SVI.struct.logic.DiscourseDomain;
import cn.edu.buaa.sei.SVI.struct.logic.Disjunction;
import cn.edu.buaa.sei.SVI.struct.logic.Equivalence;
import cn.edu.buaa.sei.SVI.struct.logic.Existential;
import cn.edu.buaa.sei.SVI.struct.logic.Implication;
import cn.edu.buaa.sei.SVI.struct.logic.LogicExpression;
import cn.edu.buaa.sei.SVI.struct.logic.LogicFunction;
import cn.edu.buaa.sei.SVI.struct.logic.LogicFunctionTemplate;
import cn.edu.buaa.sei.SVI.struct.logic.LogicOperator;
import cn.edu.buaa.sei.SVI.struct.logic.LogicVariable;
import cn.edu.buaa.sei.SVI.struct.logic.Negation;
import cn.edu.buaa.sei.SVI.struct.logic.Universal;

public class LogicFactory {
	public static LogicVariable createLogicVariable(String name) throws Exception{
		return new LogicVariableImpl(name);
	}
	public static LogicExpression createConjunction(LogicStruct[] operands) throws Exception{
		Conjunction op = new ConjunctionImpl(operands,new StructArray());
		return new LogicExpressionImpl(op,new StructArray());
	}
	public static LogicExpression createDisjunction(LogicStruct[] operands) throws Exception{
		Disjunction op = new DisjunctionImpl(operands,new StructArray());
		return new LogicExpressionImpl(op,new StructArray());
	}
	public static LogicExpression createNegation(LogicStruct operand) throws Exception{
		Negation op = new NegationImpl(operand,new StructArray());
		return new LogicExpressionImpl(op,new StructArray());
	}
	public static LogicExpression createImplication(LogicStruct precondition,
			LogicStruct conclusion) throws Exception{
		Implication op = new ImplicationImpl(precondition,conclusion,new StructArray());
		return new LogicExpressionImpl(op,new StructArray());
	}
	public static LogicExpression createEquivalence(LogicStruct left,LogicStruct right) throws Exception{
		Equivalence op = new EquivalenceImpl(left,right,new StructArray());
		return new LogicExpressionImpl(op,new StructArray());
	}
	public static DiscourseDomain createDiscourseDomain(String name) throws Exception{
		return new DiscourseDomainImpl(name,new StructArray());
	}
	public static LogicExpression createUniversal(DiscourseDomain domain,
			LogicStruct scope)throws Exception {
		Universal op = new UniversalImpl(domain,scope,new StructArray());
		return new LogicExpressionImpl(op,new StructArray());
	}
	public static LogicExpression createExistential(DiscourseDomain domain,
			LogicStruct scope) throws Exception{
		Existential op = new ExistentialImpl(domain,scope,new StructArray());
		return new LogicExpressionImpl(op,new StructArray());
	}
	public static LogicExpression createAtMost(DiscourseDomain domain,
			LogicStruct scope,int upper) throws Exception{
		AtMost op = new AtMostImpl(domain,scope,new StructArray());
		op.setUpperBound(upper);
		return new LogicExpressionImpl(op,new StructArray());
	}
	public static LogicExpression createAtLeast(DiscourseDomain domain,
			LogicStruct scope,int lower) throws Exception{
		AtLeast op = new AtLeastImpl(domain,scope,new StructArray());
		op.setLowerBound(lower);
		return new LogicExpressionImpl(op,new StructArray());
	}
	public static LogicExpression createBetween(DiscourseDomain domain,
			LogicStruct scope,int upper,int lower) throws Exception{
		Between op = new BetweenImpl(domain,scope,new StructArray());
		op.setBound(lower, upper);
		return new LogicExpressionImpl(op,new StructArray());
	}
	public static LogicExpression createLogicExpression(LogicOperator op) throws Exception{
		return new LogicExpressionImpl(op,new StructArray());
	}
	
	public static LogicFunctionTemplate createLogicFunctionTemplate(String name,
			Variable[] arguments) throws Exception{
		return new LogicFunctionTemplateImpl(name,arguments,new StructArray());
	}
	public static LogicFunction createLogicFunction(LogicFunctionTemplate template) throws Exception{
		return new LogicFunctionImpl(template,null,null,new StructArray());
	}
	public static LogicFunction createLogicFunction(LogicFunctionTemplate template,Context context) throws Exception{
		return new LogicFunctionImpl(template,null,context,new StructArray());
	}
	
}
