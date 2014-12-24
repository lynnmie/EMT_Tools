package cn.edu.buaa.sei.SVI.interpreter.logic;

import cn.edu.buaa.sei.SVI.struct.logic.Equivalence;

public interface EquivalenceInferencer extends Inferencer{
	public Boolean interpret(Equivalence op) throws Exception;
}
