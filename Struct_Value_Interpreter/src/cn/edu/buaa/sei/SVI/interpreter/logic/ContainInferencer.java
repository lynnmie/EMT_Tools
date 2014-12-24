package cn.edu.buaa.sei.SVI.interpreter.logic;

import cn.edu.buaa.sei.SVI.struct.group.Contain;

public interface ContainInferencer extends Inferencer{
	public Boolean interpret(Contain op) throws Exception;
}
