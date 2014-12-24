package cn.edu.buaa.sei.SVI.struct.numeric;

import cn.edu.buaa.sei.SVI.struct.core.function.Function;

/**
 * NumericFunction is a Numeric Struct which could be computed by Computer and produce
 * Numeric Result.<br>
 * NumericFunction could only contain NumericFunctionTemplate whose output is NumericVariable.
 * */
public interface NumericFunction extends CompositeNumericStruct,Function{
	public NumericFunctionTemplate getTemplate();
}
