package cn.edu.buaa.sei.SVI.interpreter.logic;

import cn.edu.buaa.sei.SVI.struct.logic.Universal;

public interface UniversalInferencer extends Inferencer{
	public Boolean interpret(Universal op) throws Exception;
}
