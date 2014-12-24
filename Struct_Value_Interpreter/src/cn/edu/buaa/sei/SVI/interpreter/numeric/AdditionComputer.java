package cn.edu.buaa.sei.SVI.interpreter.numeric;

import cn.edu.buaa.sei.SVI.struct.numeric.Addition;

public interface AdditionComputer extends Computer{
	public Number interpret(Addition op) throws Exception;
}
