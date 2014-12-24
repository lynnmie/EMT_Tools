package cn.edu.buaa.sei.SVI.editor.assign_view;

import java.io.File;
import java.io.FileInputStream;
import java.util.Set;

import cn.edu.buaa.sei.SVI.manage.IStructAssigner;
import cn.edu.buaa.sei.SVI.manage.IStructImporter;
import cn.edu.buaa.sei.SVI.manage.StructManager;
import cn.edu.buaa.sei.SVI.manage.impl.SVIManageFactory;
import cn.edu.buaa.sei.SVI.manage.impl.SVIStream;
import cn.edu.buaa.sei.SVI.struct.core.Struct;
import cn.edu.buaa.sei.SVI.struct.core.variable.ReferenceVariable;
import cn.edu.buaa.sei.SVI.struct.core.variable.Variable;

public class Test {
	public static void main(String[] args){
		
	}
	
	public static String printVariable(Variable x){
		if(x==null)return null;
		
		StringBuilder code = new StringBuilder();
		
		code.append(x.getName()).append(": ").append(x.getClass().getName()).append("@").append(x.hashCode());
		if(x instanceof ReferenceVariable)
			code.append(" --> ").append(((ReferenceVariable) x).getRefer().getName()).append(": ").
			append(((ReferenceVariable) x).getRefer().getClass().getName()).append("@").append(((ReferenceVariable) x).getRefer().hashCode());
		
		return code.toString();
	}
	public static StructManager readSM(File file) throws Exception{
		if(file==null)throw new Exception("Null file is invalid");
		SVIStream in = new SVIStream();
		in.setInputStream(new FileInputStream(file));
		IStructImporter reader = SVIManageFactory.createXMLStructImporter();
		reader.setInput(in);
		return reader.read();
	}
	public static void jtable1(){
		
	}
	
	public static void test1(){
		try {
			StructManager manager = readSM(new File("g.xml"));
			Set<Struct> tops = manager.getTopStructs();
			for(Struct top:tops){
				System.out.println(top.toString());
			}
			
			Struct top = tops.iterator().next();
			
			IStructAssigner assigner = SVIManageFactory.createStructAssigner();
			Variable HLR = assigner.getVariableByName(top, "HLR", 0);
			Variable LLR_iter = assigner.getVariableByName(top,"LLR.iter",0);
			Variable llr = assigner.getVariableByName(top, "llr", 0);
			
			System.out.println(printVariable(HLR));
			System.out.println(printVariable(LLR_iter));
			System.out.println(printVariable(llr));
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
