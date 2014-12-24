package cn.edu.buaa.sei.SVI.interpreter.logic;

import cn.edu.buaa.sei.SVI.struct.numeric.logic.Equal;

public interface EqualInferencer extends Inferencer{
	public Boolean interpret(Equal op) throws Exception;
}
