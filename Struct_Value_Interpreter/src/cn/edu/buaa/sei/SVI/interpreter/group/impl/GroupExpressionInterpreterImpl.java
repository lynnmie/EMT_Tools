package cn.edu.buaa.sei.SVI.interpreter.group.impl;

import cn.edu.buaa.sei.SVI.interpreter.core.RegisterMachine;
import cn.edu.buaa.sei.SVI.interpreter.group.GroupExpressionInterpreter;
import cn.edu.buaa.sei.SVI.interpreter.group.GroupInterpreter;
import cn.edu.buaa.sei.SVI.struct.core.Struct;
import cn.edu.buaa.sei.SVI.struct.core.extend.GroupStruct;
import cn.edu.buaa.sei.SVI.struct.group.Group;
import cn.edu.buaa.sei.SVI.struct.group.GroupExpression;
import cn.edu.buaa.sei.SVI.struct.group.GroupOperator;

public class GroupExpressionInterpreterImpl implements GroupExpressionInterpreter{

	@Override
	public Group interpret(GroupStruct input) throws Exception {
		if(input==null)throw new Exception("Null input is invalid");
		if(!(input instanceof GroupExpression))throw new Exception("GroupExpression required");
		GroupExpression expression = (GroupExpression) input;
		return this.interpret(expression);
	}

	@Override
	public Object interpret(Struct input) throws Exception {
		if(input==null)throw new Exception("Null input is invalid");
		if(!(input instanceof GroupExpression))throw new Exception("GroupExpression required");
		GroupExpression expression = (GroupExpression) input;
		return this.interpret(expression);
	}

	@Override
	public Group interpret(GroupExpression expr) throws Exception {
		if(expr==null)throw new Exception("Null expression is invalid");
		
		GroupOperator op = expr.getOperator();
		if(op==null)throw new Exception("Structure Error: null operator");
		
		GroupInterpreter interpreter = (GroupInterpreter)RegisterMachine.getRegister().get(op);
		
		return interpreter.interpret(op);
	}
	
}
