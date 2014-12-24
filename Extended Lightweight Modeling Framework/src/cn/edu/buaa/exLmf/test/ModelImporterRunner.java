package cn.edu.buaa.exLmf.test;

import java.io.File;
import java.util.List;

import cn.edu.buaa.sei.exLmf.metamodel.LClass;
import cn.edu.buaa.sei.exLmf.metamodel.LClassifier;
import cn.edu.buaa.sei.exLmf.metamodel.LEnum;
import cn.edu.buaa.sei.exLmf.metamodel.LEnumLiteral;
import cn.edu.buaa.sei.exLmf.metamodel.LMultipleObject;
import cn.edu.buaa.sei.exLmf.metamodel.LPackage;
import cn.edu.buaa.sei.exLmf.metamodel.LStructuralFeature;
import cn.edu.buaa.sei.exLmf.translater.EcoreModelReader;
import cn.edu.buaa.sei.exLmf.translater.IModelReader;

public class ModelImporterRunner {

	public static void main(String[] args) {
		try {
			IModelReader im = new EcoreModelReader("Ecore_Reader");
			im.setInputStream(new File("R.ecore"));
			LPackage p = im.read();
			
			System.out.println(printPackage(p));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static String printClass(LClass type){
		if(type==null)return null;
		StringBuilder code = new StringBuilder();
		
		code.append(type.getName()).append("[").append(type.getClassifierID()).append("]:");
		if(type.isAbstract())code.append("<abstract> ");
		if(type.isFinal())code.append("<final> ");
		for(int i=0;i<type.getFeatures().size();i++){
			LStructuralFeature feature = type.getFeatures().get(i);
			code.append("\n\t-").append(feature.getName()).append("[").append(feature.getFeatureID()).
			append("]: ").append(feature.getType().getName()).append("(").append(feature.getLowerBound()).
			append("-");
			if(feature.getUpperBound()==LMultipleObject.UNBOUNDED)
				code.append("*").append(")");
			else
				code.append(feature.getUpperBound()).append(")");
		}
		
		return code.toString();
	}
	
	public static String printEnum(LEnum type){
		if(type==null)return null;
		StringBuilder code = new StringBuilder();
		
		code.append(type.getName()).append("{\n");
		
		List<LEnumLiteral> literals = type.getLiterals();
		for(int i=0;i<literals.size();i++)
			code.append("\t").append(literals.get(i).getLiteral()).append(" \t[").append(literals.get(i).getValue()).append("]\n");
		
		code.append("}");
		return code.toString();
	}
	
	public static String printClassifier(LClassifier type){
		if(type==null)return null;
		else if(type instanceof LClass)return printClass((LClass) type);
		else return printEnum((LEnum) type);
	}
	
	public static String printPackage(LPackage p){
		if(p==null)return null;
		StringBuilder code = new StringBuilder();
		
		code.append("PACKAGE -- ").append(p.getName()).append("{").append(p.getNsURI()).append("}").append(p.getNsPrefix()).append("\n\n");
		code.append(p.getName()).append("\n------------------------------------------------------------------\n");
		List<LClassifier> types = p.getTypes();
		for(int i=0;i<types.size();i++)
			code.append(printClassifier(types.get(i))).append("\n");
		code.append("\n------------------------------------------------------------------\n");
		return code.toString();
	}

}
