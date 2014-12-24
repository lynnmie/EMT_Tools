package cn.edu.buaa.sei.exLmf.manager;

import cn.edu.buaa.sei.exLmf.metamodel.LAttribute;
import cn.edu.buaa.sei.exLmf.metamodel.LClass;
import cn.edu.buaa.sei.exLmf.metamodel.LClassifier;
import cn.edu.buaa.sei.exLmf.metamodel.LDataType;
import cn.edu.buaa.sei.exLmf.metamodel.LEnum;
import cn.edu.buaa.sei.exLmf.metamodel.LEnumLiteral;
import cn.edu.buaa.sei.exLmf.metamodel.LPackage;
import cn.edu.buaa.sei.exLmf.metamodel.LPrimitiveType;
import cn.edu.buaa.sei.exLmf.metamodel.LReference;
import cn.edu.buaa.sei.exLmf.metamodel.LStructuralFeature;

public interface IModelPrinter {
	public String printLPackage(LPackage p);
	public String printLClassifier(LClassifier type);
	public String printLClass(LClass type);
	public String printLDataType(LDataType type);
	public String printLPrimitiveType(LPrimitiveType type);
	public String printLEnum(LEnum type);
	public String printLStructrualFeature(LStructuralFeature feature);
	public String printLAttribute(LAttribute attr);
	public String printLReference(LReference ref);
	public String printLEnumLiteral(LEnumLiteral literal);
}
