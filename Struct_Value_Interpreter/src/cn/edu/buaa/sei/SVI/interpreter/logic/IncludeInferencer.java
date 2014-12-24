package cn.edu.buaa.sei.SVI.interpreter.logic;

import cn.edu.buaa.sei.SVI.struct.group.Include;

public interface IncludeInferencer extends Inferencer{
	public Boolean interpret(Include op) throws Exception;
}
