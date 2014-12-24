package cn.edu.buaa.sei.SVI.interpreter.logic.impl;

import cn.edu.buaa.sei.SVI.interpreter.core.RegisterMachine;
import cn.edu.buaa.sei.SVI.interpreter.logic.ExpressionInferencer;
import cn.edu.buaa.sei.SVI.interpreter.logic.Inferencer;
import cn.edu.buaa.sei.SVI.struct.core.Struct;
import cn.edu.buaa.sei.SVI.struct.core.extend.LogicStruct;
import cn.edu.buaa.sei.SVI.struct.logic.LogicExpression;
import cn.edu.buaa.sei.SVI.struct.logic.LogicOperator;

public class ExpressionInferencerImpl implements ExpressionInferencer{

	@Override
	public Boolean interpret(LogicStruct input) throws Exception {
		if(input==null)throw new Exception("Null input is invalid");
		if(!(input instanceof LogicExpression))throw new Exception("LogicExpression required");
		
		LogicExpression expr = (LogicExpression) input;
		return this.interpret(expr);
	}

	@Override
	public Object interpret(Struct input) throws Exception {
		if(input==null)throw new Exception("Null input is invalid");
		if(!(input instanceof LogicExpression))throw new Exception("LogicExpression required");
		
		LogicExpression expr = (LogicExpression) input;
		return this.interpret(expr);
	}

	@Override
	public Boolean interpret(LogicExpression expr) throws Exception {
		if(expr==null)throw new Exception("Null expression is invalid");
		
		LogicOperator op = expr.getOperator();
		if(op==null)throw new Exception("Structure Error: null operator");
		
		Inferencer inferencer = (Inferencer) RegisterMachine.getRegister().get(op);
		return inferencer.interpret(op);
	}
	
}
