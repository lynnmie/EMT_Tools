package cn.edu.buaa.sei.SVI.manage.impl;

import cn.edu.buaa.sei.SVI.manage.IStructAssigner;
import cn.edu.buaa.sei.SVI.manage.IStructImporter;
import cn.edu.buaa.sei.SVI.manage.IStructPrinter;
import cn.edu.buaa.sei.SVI.manage.IStructSearcher;
import cn.edu.buaa.sei.SVI.manage.InterpreterClassLib;
import cn.edu.buaa.sei.SVI.manage.InterpreterRegisterMachine;
import cn.edu.buaa.sei.SVI.manage.StructClassLib;
import cn.edu.buaa.sei.SVI.manage.StructManager;
import cn.edu.buaa.sei.SVI.manage.impl.interpreter_access.InterpreterLib;
import cn.edu.buaa.sei.SVI.manage.impl.interpreter_access.RegisterMachine;
import cn.edu.buaa.sei.SVI.manage.impl.interpreter_access.StructLib;
import cn.edu.buaa.sei.SVI.manage.impl.searcher_impl.StructAssigner;
import cn.edu.buaa.sei.SVI.manage.impl.searcher_impl.StructSearcher1;
import cn.edu.buaa.sei.SVI.manage.impl.xml_struct.XMLStructImporter;
import cn.edu.buaa.sei.SVI.manage.impl.xml_struct.XMLStructPrinter;


public class SVIManageFactory {
	public static SVIStream createStreamResource(){return new SVIStream();}
	public static StructManager createStructManager(){return new StructManagerImpl();}
	
	public static IStructSearcher createStructSearcher(){return StructSearcher1.create();}
	public static IStructAssigner createStructAssigner(){return StructAssigner.create();}
	
	public static IStructPrinter  createXMLStructPrinter(){return new XMLStructPrinter();}
	public static IStructImporter createXMLStructImporter(){return new XMLStructImporter();}
	
	public static InterpreterClassLib getInterpreterLib(){return InterpreterLib.getLibrary();}
	public static StructClassLib getStructLib(){return StructLib.getStructLibrary();}
	
	public static InterpreterRegisterMachine getRegisterMachine(){return RegisterMachine.getMachine();}
}
