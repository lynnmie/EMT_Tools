package cn.edu.buaa.sei.SVI.interpreter.numeric.impl;

import cn.edu.buaa.sei.SVI.interpreter.core.RegisterMachine;
import cn.edu.buaa.sei.SVI.interpreter.numeric.Computer;
import cn.edu.buaa.sei.SVI.interpreter.numeric.ExpressionComputer;
import cn.edu.buaa.sei.SVI.struct.core.Struct;
import cn.edu.buaa.sei.SVI.struct.core.extend.NumericStruct;
import cn.edu.buaa.sei.SVI.struct.numeric.NumericExpression;
import cn.edu.buaa.sei.SVI.struct.numeric.NumericOperator;

public class ExpressionComputerImpl implements ExpressionComputer{

	@Override
	public Number interpret(NumericStruct input) throws Exception {
		if(input==null)throw new Exception("Null input is invalid");
		if(!(input instanceof NumericExpression))throw new Exception("NumericExpression required");
		
		NumericExpression expr = (NumericExpression) input;
		return this.interpret(expr);
	}

	@Override
	public Object interpret(Struct input) throws Exception {
		if(input==null)throw new Exception("Null input is invalid");
		if(!(input instanceof NumericExpression))throw new Exception("NumericExpression required");
		
		NumericExpression expr = (NumericExpression) input;
		return this.interpret(expr);
	}

	@Override
	public Number interpret(NumericExpression expr) throws Exception {
		if(expr==null)throw new Exception("Null expression is invalid");
		
		NumericOperator op = expr.getOperator();
		if(op==null)throw new Exception("Structure Error: null operator");
		
		Computer c = (Computer)RegisterMachine.getRegister().get(op);
		if(c==null)
			throw new Exception("Operator: "+op.getClass().getCanonicalName()+" has not been registered");
		
		return c.interpret(op);
	}
	
}
