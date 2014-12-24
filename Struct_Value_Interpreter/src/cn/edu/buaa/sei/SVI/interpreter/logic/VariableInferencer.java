package cn.edu.buaa.sei.SVI.interpreter.logic;

import cn.edu.buaa.sei.SVI.interpreter.core.MemoryReader;
import cn.edu.buaa.sei.SVI.struct.logic.LogicVariable;

/**
 * VariableInferencer is both Inferencer {return boolean} and MemoryReader {input Bindable}
 * */
public interface VariableInferencer extends Inferencer,MemoryReader{
	public Boolean interpret(LogicVariable var) throws Exception;
}
