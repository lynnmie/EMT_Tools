package cn.edu.buaa.sei.SVI.interpreter.logic;

import cn.edu.buaa.sei.SVI.struct.logic.Disjunction;

public interface DisjunctionInferencer extends Inferencer{
	public Boolean interpret(Disjunction op) throws Exception;
}
