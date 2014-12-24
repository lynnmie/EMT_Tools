package cn.edu.buaa.sei.SVI.interpreter.logic;

import cn.edu.buaa.sei.SVI.struct.numeric.logic.Smaller;

public interface SmallerInferencer extends Inferencer{
	public Boolean interpret(Smaller op) throws Exception;
}
