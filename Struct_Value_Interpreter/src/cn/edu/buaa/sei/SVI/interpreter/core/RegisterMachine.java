package cn.edu.buaa.sei.SVI.interpreter.core;
import cn.edu.buaa.sei.SVI.manage.InterpreterRegisterMachine;
import cn.edu.buaa.sei.SVI.manage.impl.SVIManageFactory;

public class RegisterMachine {
	public static InterpreterRegisterMachine getRegister(){return SVIManageFactory.getRegisterMachine();}
}
