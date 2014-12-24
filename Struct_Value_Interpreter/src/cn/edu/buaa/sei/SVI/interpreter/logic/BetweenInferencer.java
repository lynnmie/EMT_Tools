package cn.edu.buaa.sei.SVI.interpreter.logic;

import cn.edu.buaa.sei.SVI.struct.logic.Between;

public interface BetweenInferencer extends Inferencer{
	public Boolean interpret(Between op) throws Exception;
}
