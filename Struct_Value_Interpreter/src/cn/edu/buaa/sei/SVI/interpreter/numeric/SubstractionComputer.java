package cn.edu.buaa.sei.SVI.interpreter.numeric;
import cn.edu.buaa.sei.SVI.struct.numeric.Substraction;

public interface SubstractionComputer extends Computer{
	public Number interpret(Substraction op) throws Exception;
}
