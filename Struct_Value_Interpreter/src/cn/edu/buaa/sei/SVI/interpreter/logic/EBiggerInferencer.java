package cn.edu.buaa.sei.SVI.interpreter.logic;

import cn.edu.buaa.sei.SVI.struct.numeric.logic.EBigger;

public interface EBiggerInferencer extends Inferencer{
	public Boolean interpret(EBigger op) throws Exception;
}
