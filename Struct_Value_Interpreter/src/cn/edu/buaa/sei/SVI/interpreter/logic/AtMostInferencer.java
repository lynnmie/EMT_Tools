package cn.edu.buaa.sei.SVI.interpreter.logic;

import cn.edu.buaa.sei.SVI.struct.logic.AtMost;

public interface AtMostInferencer extends Inferencer{
	public Boolean interpret(AtMost op) throws Exception;
}
