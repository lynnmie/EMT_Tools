package cn.edu.buaa.sei.SVI.interpreter.group;

import cn.edu.buaa.sei.SVI.struct.group.CartesianProduct;
import cn.edu.buaa.sei.SVI.struct.group.Group;

public interface CartesianProductInterpreter extends GroupInterpreter{
	public Group interpret(CartesianProduct op) throws Exception;
}
