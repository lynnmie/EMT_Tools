package cn.edu.buaa.sei.SVI.interpreter.numeric;

import cn.edu.buaa.sei.SVI.struct.group.Cardinality;

public interface CardinalityComputer extends Computer{
	public Number interpret(Cardinality op) throws Exception;
}
