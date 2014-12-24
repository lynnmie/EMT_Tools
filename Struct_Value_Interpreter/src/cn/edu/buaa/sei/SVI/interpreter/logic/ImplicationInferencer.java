package cn.edu.buaa.sei.SVI.interpreter.logic;

import cn.edu.buaa.sei.SVI.struct.logic.Implication;

public interface ImplicationInferencer extends Inferencer{
	public Boolean interpret(Implication op) throws Exception;
}
