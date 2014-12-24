package cn.edu.buaa.sei.SVI.manage.impl.xml_struct;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Set;

import cn.edu.buaa.sei.SVI.manage.IStructImporter;
import cn.edu.buaa.sei.SVI.manage.IStructPrinter;
import cn.edu.buaa.sei.SVI.manage.StructManager;
import cn.edu.buaa.sei.SVI.manage.impl.SVIStream;
import cn.edu.buaa.sei.SVI.manage.impl.StructManagerImpl;
import cn.edu.buaa.sei.SVI.struct.core.Struct;
import cn.edu.buaa.sei.SVI.struct.core.variable.Variable;
import cn.edu.buaa.sei.SVI.struct.logic.DiscourseDomain;
import cn.edu.buaa.sei.SVI.struct.logic.LogicExpression;
import cn.edu.buaa.sei.SVI.struct.logic.LogicFunction;
import cn.edu.buaa.sei.SVI.struct.logic.LogicFunctionTemplate;
import cn.edu.buaa.sei.SVI.struct.logic.impl.LogicFactory;

public class Test {

	public static void main(String[] args) {
		try {
			SVIStream resource = new SVIStream();
			resource.setInputStream(new FileInputStream(new File("data/a.s")));
			IStructImporter importer = new XMLStructImporter();
			importer.setInput(resource);
			StructManager manager = importer.read();
			
			System.out.println(manager.getTopStructs().iterator().next().toString());
			resource.setOutputStream(new FileOutputStream(new File("data/c.xml")));
			IStructPrinter printer = new XMLStructPrinter();
			printer.setOutputStream(resource);
			printer.write(manager);
			System.out.println("Writting complete...");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void test1() throws Exception{
		SVIStream resource = new SVIStream();
		resource.setOutputStream(new FileOutputStream(new File("data/c.xml")));
		IStructPrinter printer = new XMLStructPrinter();
		printer.setOutputStream(resource);
		
		StructManager manager = new StructManagerImpl();
		manager.putTopStruct(create1());
		printer.write(manager);
		
		System.out.println("Writting successfully");
	}
	
	public static void test2() throws Exception{
		SVIStream resource = new SVIStream();
		resource.setInputStream(new FileInputStream(new File("data/a.s")));
		IStructImporter importer = new XMLStructImporter();
		importer.setInput(resource);
		
		StructManager manager = importer.read();
		Set<Struct> tops = manager.getTopStructs();
		for(Struct top:tops){
			System.out.println("===============================");
			System.out.println(top.toString());
		}
	}
	
	public static Struct create1() throws Exception{
		DiscourseDomain HLR = LogicFactory.createDiscourseDomain("HLR");
		DiscourseDomain LLR = LogicFactory.createDiscourseDomain("LLR");
		
		LogicFunctionTemplate template = LogicFactory.createLogicFunctionTemplate(
				"traceable", new Variable[]{HLR.getIterator(),LLR.getIterator()});
		LogicFunction traceable = LogicFactory.createLogicFunction(template);
		
		LogicExpression L = LogicFactory.createExistential(LLR, traceable);
		
		return LogicFactory.createUniversal(HLR, L);
	}

}
