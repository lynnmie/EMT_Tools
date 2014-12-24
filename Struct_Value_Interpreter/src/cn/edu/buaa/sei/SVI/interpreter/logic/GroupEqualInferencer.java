package cn.edu.buaa.sei.SVI.interpreter.logic;

import cn.edu.buaa.sei.SVI.struct.group.GroupEqual;

public interface GroupEqualInferencer extends Inferencer{
	public Boolean interpret(GroupEqual op) throws Exception;
}
