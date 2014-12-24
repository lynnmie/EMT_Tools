package cn.edu.buaa.sei.SVI.interpreter.numeric;

import cn.edu.buaa.sei.SVI.struct.numeric.Mod;

public interface ModComputer extends Computer{
	public Number interpret(Mod op) throws Exception;
}
