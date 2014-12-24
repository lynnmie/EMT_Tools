package cn.edu.buaa.sei.SVI.interpreter.logic;

import cn.edu.buaa.sei.SVI.struct.logic.Conjunction;

public interface ConjunctionInferencer extends Inferencer{
	public Boolean interpret(Conjunction op) throws Exception;
}
