package cn.edu.buaa.sei.SVI.interpreter.numeric;

import cn.edu.buaa.sei.SVI.struct.numeric.NumericExpression;

public interface ExpressionComputer extends Computer{
	public Number interpret(NumericExpression expr) throws Exception;
}
