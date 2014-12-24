package cn.edu.buaa.sei.SVI.struct.core.program;

import cn.edu.buaa.sei.SVI.struct.core.Struct;

public interface CallStatement extends Statement{
	/**
	 * Return the child Struct which would be interpreted during the Call Statement.
	 * */
	public Struct getChild();
}
