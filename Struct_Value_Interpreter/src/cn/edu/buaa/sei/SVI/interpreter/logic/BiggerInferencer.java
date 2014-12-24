package cn.edu.buaa.sei.SVI.interpreter.logic;

import cn.edu.buaa.sei.SVI.struct.numeric.logic.Bigger;

public interface BiggerInferencer extends Inferencer{
	public Boolean interpret(Bigger op) throws Exception;
}
