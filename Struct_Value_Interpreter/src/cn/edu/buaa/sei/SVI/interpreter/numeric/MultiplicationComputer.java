package cn.edu.buaa.sei.SVI.interpreter.numeric;

import cn.edu.buaa.sei.SVI.struct.numeric.Multiplication;

public interface MultiplicationComputer extends Computer{
	public Number interpret(Multiplication op) throws Exception;
}
