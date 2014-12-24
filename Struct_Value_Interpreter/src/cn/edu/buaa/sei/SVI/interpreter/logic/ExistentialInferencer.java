package cn.edu.buaa.sei.SVI.interpreter.logic;

import cn.edu.buaa.sei.SVI.struct.logic.Existential;

public interface ExistentialInferencer extends Inferencer{
	public Boolean interpret(Existential op) throws Exception;
}
