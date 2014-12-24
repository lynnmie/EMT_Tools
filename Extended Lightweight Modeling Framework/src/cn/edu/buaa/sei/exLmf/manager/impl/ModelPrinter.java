package cn.edu.buaa.sei.exLmf.manager.impl;

import java.util.List;

import cn.edu.buaa.sei.exLmf.manager.IModelPrinter;
import cn.edu.buaa.sei.exLmf.metamodel.LAttribute;
import cn.edu.buaa.sei.exLmf.metamodel.LClass;
import cn.edu.buaa.sei.exLmf.metamodel.LClassifier;
import cn.edu.buaa.sei.exLmf.metamodel.LDataType;
import cn.edu.buaa.sei.exLmf.metamodel.LEnum;
import cn.edu.buaa.sei.exLmf.metamodel.LEnumLiteral;
import cn.edu.buaa.sei.exLmf.metamodel.LMultipleObject;
import cn.edu.buaa.sei.exLmf.metamodel.LPackage;
import cn.edu.buaa.sei.exLmf.metamodel.LPrimitiveType;
import cn.edu.buaa.sei.exLmf.metamodel.LReference;
import cn.edu.buaa.sei.exLmf.metamodel.LStructuralFeature;

public class ModelPrinter implements IModelPrinter{
	String name;
	
	public ModelPrinter(String name){this.name=name;}

	@Override
	public String printLPackage(LPackage p) {
		if(p==null)return null;
		
		String name = p.getName();
		String uri = p.getNsURI();
		String prefix = p.getNsPrefix();
		
		LPackage container = p.getContainer();
		List<LPackage> subPackages = p.getSubPackages();
		List<LClassifier> types = p.getTypes();
		
		StringBuilder code = new StringBuilder();
		
		code.append("Package Name: ").append(name).append("\n");
		code.append("NameSpace URI: ").append(uri).append("\n");
		code.append("Package Prefix: ").append(prefix).append("\n");
		if(container!=null)
			code.append("Container: ").append(container.getName()).append("<").append(container.getNsURI()).append(">\n").append("\n");
		
		code.append("Sub Packages{\n");
		for(int i=0;i<subPackages.size();i++){
			LPackage sub = subPackages.get(i);
			code.append("\t").append("[").append(sub.getNsURI()).append("]: ").append(sub.getName()).append("\n");
		}
		code.append("}\n\n");
		
		code.append("Classifiers{\n");
		for(int i=0;i<types.size();i++){
			LClassifier type = types.get(i);
			code.append("\t").append("[").append(type.getClassifierID()).append("]: ").append(type.getName()).append("\n");
		}
		code.append("}\n");
		
		return code.toString();
	}
	@Override
	public String printLClassifier(LClassifier type) {
		if(type==null)return null;
		
		int id = type.getClassifierID();
		LPackage container = type.getContainer();
		String name = type.getName();
		String instance = type.getInstanceName();
		
		StringBuilder code = new StringBuilder();
		
		code.append("Classifier[").append(id).append("]: ");
		if(container!=null)code.append(container.getName());
		code.append("::").append(name).append("(").append(instance).append(")");
		
		return code.toString();
	}
	@Override
	public String printLClass(LClass type) {
		if(type==null)return null;
		
		StringBuilder code = new StringBuilder();
		
		code.append(this.printLClassifier(type)).append("\n\n");
		
		List<LStructuralFeature> features = type.getAllFeatures();
		code.append("Attributes{\n");
		for(int i=0;i<features.size();i++){
			LStructuralFeature f = features.get(i);
			LClassifier container = f.getContainer();
			String name = f.getName();
			int fid = f.getFeatureID();
			int lower = f.getLowerBound();
			int upper = f.getUpperBound();
			LClassifier ftype = f.getType();
			
			code.append("\t").append("[").append(fid).append("] ").append(container.getName()).append("::").
			append(name).append(": ").append(ftype.getName()).append("(").append(lower).append("-->");
			
			if(upper==LMultipleObject.UNBOUNDED)code.append("*");
			else code.append(upper);
			code.append(")\n");
		}
		code.append("}\n");
		return code.toString();
	}
	@Override
	public String printLDataType(LDataType type) {return this.printLClassifier(type);}
	@Override
	public String printLPrimitiveType(LPrimitiveType type) {return this.printLDataType(type);}
	@Override
	public String printLEnum(LEnum type) {
		if(type==null)return null;
		
		StringBuilder code = new StringBuilder();
		
		code.append(this.printLDataType(type)).append("\n\n");
		List<LEnumLiteral> literals = type.getLiterals();
		
		code.append("Literals{\n");
		for(int i=0;i<literals.size();i++){
			LEnumLiteral literal = literals.get(i);
			code.append("\t").append(literal.getName()).append(": ").append(literal.getValue()).append("\n");
		}
		code.append("}\n");
		
		return code.toString();
	}
	@Override
	public String printLStructrualFeature(LStructuralFeature feature) {
		if(feature==null)return null;
		StringBuilder code = new StringBuilder();
		
		LClassifier container = feature.getContainer();
		String name = feature.getName();
		int fid = feature.getFeatureID();
		LClassifier type = feature.getType();
		int lower = feature.getLowerBound();
		int upper = feature.getUpperBound();
		
		code.append("[").append(fid).append("] ").append(container.getName()).append(".").append(name).append(": ").append(type.getName()).append(" (").append(lower).append("--");
		
		if(upper==LMultipleObject.UNBOUNDED)code.append("*");
		else code.append(upper);
		
		code.append(")");
		return code.toString();
	}

	@Override
	public String printLAttribute(LAttribute attr) {return this.printLStructrualFeature(attr);}
	@Override
	public String printLReference(LReference ref) {return this.printLStructrualFeature(ref);}

	@Override
	public String printLEnumLiteral(LEnumLiteral literal) {
		if(literal==null)return null;
		
		StringBuilder code = new StringBuilder();
		
		code.append("[").append(literal.getFeatureID()).append("] ").append(literal.getName()).append(": ").append(literal.getValue());
		
		return code.toString();
	}

}
