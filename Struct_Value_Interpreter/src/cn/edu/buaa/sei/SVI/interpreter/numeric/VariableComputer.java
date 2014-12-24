package cn.edu.buaa.sei.SVI.interpreter.numeric;

import cn.edu.buaa.sei.SVI.interpreter.core.MemoryReader;
import cn.edu.buaa.sei.SVI.struct.numeric.NumericVariable;

public interface VariableComputer extends MemoryReader,Computer{
	public Number interpret(NumericVariable var) throws Exception;
}
