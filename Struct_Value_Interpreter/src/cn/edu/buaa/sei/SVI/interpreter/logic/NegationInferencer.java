package cn.edu.buaa.sei.SVI.interpreter.logic;

import cn.edu.buaa.sei.SVI.struct.logic.Negation;

public interface NegationInferencer extends Inferencer{
	public Boolean interpret(Negation op) throws Exception;
}
