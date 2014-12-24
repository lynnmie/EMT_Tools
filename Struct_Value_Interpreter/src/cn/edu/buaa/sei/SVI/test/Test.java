package cn.edu.buaa.sei.SVI.test;

import java.io.File;
import java.io.FileInputStream;
import java.util.Set;

import cn.edu.buaa.sei.SVI.interpreter.core.Interpreter;
import cn.edu.buaa.sei.SVI.manage.IStructImporter;
import cn.edu.buaa.sei.SVI.manage.InterpreterRegisterMachine;
import cn.edu.buaa.sei.SVI.manage.StructManager;
import cn.edu.buaa.sei.SVI.manage.impl.SVIManageFactory;
import cn.edu.buaa.sei.SVI.manage.impl.SVIStream;
import cn.edu.buaa.sei.SVI.struct.core.Struct;

public class Test {
	

	static InterpreterRegisterMachine rm = SVIManageFactory.getRegisterMachine();

	public static void main(String[] args) {
		try {
			test1();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void test1() throws Exception{
		StructManager manager = create("data/c.xml");
		Set<Struct> tops = manager.getTopStructs();
		
		Struct top = tops.iterator().next();
		
		//IStructAssigner assigner = SVIManageFactory.createStructAssigner();
		/*assigner.assignByName(top, "a", true, 0);
		assigner.assignByName(top, "b", false, 0);
		assigner.assignByName(top, "c", false, 0);
		assigner.assignByName(top, "d", true, 0);
		assigner.assignByName(top, "e", true, 0);
		assigner.assignByName(top, "f", true, 0);
		*/
		Interpreter interpreter = rm.get(top);
		Object result = interpreter.interpret(top);
		System.out.println(top.toString()+" --> "+result);
	}
	
	public static StructManager create(String file) throws Exception{
		IStructImporter reader = SVIManageFactory.createXMLStructImporter();
		SVIStream resource = SVIManageFactory.createStreamResource();
		
		resource.setInputStream(new FileInputStream(new File(file)));
		reader.setInput(resource);
		
		return reader.read();
	}

}
