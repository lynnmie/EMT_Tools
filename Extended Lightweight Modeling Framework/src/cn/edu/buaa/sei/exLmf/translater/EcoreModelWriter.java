package cn.edu.buaa.sei.exLmf.translater;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EEnum;
import org.eclipse.emf.ecore.EEnumLiteral;
import org.eclipse.emf.ecore.EModelElement;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.EcoreFactory;
import org.eclipse.emf.ecore.EcorePackage;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.xmi.impl.EcoreResourceFactoryImpl;

import cn.edu.buaa.sei.exLmf.metamodel.LAttribute;
import cn.edu.buaa.sei.exLmf.metamodel.LClass;
import cn.edu.buaa.sei.exLmf.metamodel.LClassifier;
import cn.edu.buaa.sei.exLmf.metamodel.LDataType;
import cn.edu.buaa.sei.exLmf.metamodel.LEnum;
import cn.edu.buaa.sei.exLmf.metamodel.LEnumLiteral;
import cn.edu.buaa.sei.exLmf.metamodel.LModelElement;
import cn.edu.buaa.sei.exLmf.metamodel.LMultipleObject;
import cn.edu.buaa.sei.exLmf.metamodel.LPackage;
import cn.edu.buaa.sei.exLmf.metamodel.LReference;
import cn.edu.buaa.sei.exLmf.metamodel.LStructuralFeature;
import cn.edu.buaa.sei.exLmf.metamodel.impl.LMFException;
import cn.edu.buaa.sei.exLmf.metamodel.impl.LPrimitiveTypeImpl;

public class EcoreModelWriter implements IModelWriter{
	
	String name;
	File file;
	LPackage p;
	Map<LModelElement,EModelElement> tmap = new HashMap<LModelElement,EModelElement>();
	EcoreFactory factory = EcoreFactory.eINSTANCE;

	public EcoreModelWriter(String name){this.name=name;}
	/**Tool Functions*/
	Exception getException(String func,String arg,String reason){
		return LMFException.create("Ecore Writer "+this.name, "EcoreModelWriter", func, arg, reason);
	}
	
	@Override
	public void setOutputStream(File file) {this.file=file;}
	@Override
	public void setModel(LPackage p) {this.p=p;}
	@Override
	public Boolean validate() {return (this.p!=null)&&(this.file!=null);}

	/**
	 * Write the LPackage into ecore file.
	 * @throws Exception 
	 * */
	@Override
	public void write() throws Exception {
		if(!this.validate())
			throw this.getException("write()", "validate()", "Validation Failed");
		EPackage ep = (EPackage) this.generateModelElement(p);
		
		URI uri = URI.createFileURI(file.getAbsolutePath());
		ResourceSet resourceSet = new ResourceSetImpl();
		resourceSet.getResourceFactoryRegistry().getExtensionToFactoryMap().put("ecore", new EcoreResourceFactoryImpl());
		Resource resource = resourceSet.createResource(uri);
		System.out.println("Creating URI: "+uri.path());
		
		resource.getContents().add(ep);
		resource.save(null);
	}
	
	/**
	 * 	Supporting Functions
	 * @throws Exception 
	 * */
	EModelElement generateModelElement(LModelElement elm) throws Exception{
		if(elm==null)return null;
		if(this.tmap.containsKey(elm))return this.tmap.get(elm);
		
		if(elm instanceof LPackage){return this.generatePackage((LPackage) elm);}
		else if(elm instanceof LClass){return this.generateClass((LClass) elm);}
		else if(elm instanceof LEnum){return this.generateEnum((LEnum) elm);}
		else if(elm instanceof LAttribute){return this.generateAttribute((LAttribute) elm);}
		else if(elm instanceof LReference){return this.generateReference((LReference) elm);}
		else if(elm instanceof LEnumLiteral){return this.generateLiteral((LEnumLiteral) elm);}
		else if(elm instanceof LDataType){return this.generateDataType((LDataType) elm);}
		throw this.getException("generateModelElement(elm)", "elm", "Unknown LModelElemnet: "+elm.getClass().getName());
	}
	/**
	 * Translate the LPackage to EPackage (EMF)
	 * @throws Exception 
	 * */
	EPackage generatePackage(LPackage p) throws Exception{
		if(p==null)
			throw this.getException("generatePackage(p)", "p", "Null");
		
		EPackage ep = factory.createEPackage();
		ep.setName(p.getName());
		ep.setNsPrefix(p.getNsPrefix());
		ep.setNsURI(p.getNsURI());
		this.tmap.put(p, ep);
		
		List<LPackage> child_package = p.getSubPackages();
		for(int i=0;i<child_package.size();i++){
			LPackage cp = child_package.get(i);
			EPackage ecp = (EPackage) this.generateModelElement(cp);
			
			if(ecp==null)throw this.getException("generatePackage(p)", "p.subPackages["+i+"]", "Generating Child Package Failed");
			
			ep.getESubpackages().add(ecp);
		}
		
		List<LClassifier> types = p.getTypes();
		for(int i=0;i<types.size();i++){
			LClassifier type = types.get(i);
			EClassifier etype = (EClassifier) this.generateModelElement(type);
			
			if(etype==null)throw this.getException("generatePackage(p)", "p.classifier["+i+"]", "Generating Child Classifier Failed");
			
			ep.getEClassifiers().add(etype);
		}
		
		return ep;
	}
	/**
	 * Translate LClass to EClass (EMF)
	 * @throws Exception 
	 * */
	EClass generateClass(LClass type) throws Exception{
		if(type==null)throw this.getException("generateClass(type)", "type", "Null");
		
		EClass etype = factory.createEClass();
		etype.setAbstract(type.isAbstract());
		etype.setInstanceClassName(type.getInstanceName());
		etype.setInstanceTypeName(type.getInstanceName());
		etype.setInterface(false);
		etype.setName(type.getName());
		this.tmap.put(type, etype);
		
		List<LClass> supers = type.getSuperTypes();
		for(int i=0;i<supers.size();i++){
			LClass stype = supers.get(i);
			EClass setype = (EClass) this.generateModelElement(stype);
			
			if(setype==null)this.getException("generateClass(type)", "SuperClass \""+stype.getName()+"\"", "Class Generationg Failed");
			
			etype.getESuperTypes().add(setype);
		}
		
		List<LStructuralFeature> features = type.getFeatures();
		for(int i=0;i<features.size();i++){
			LStructuralFeature feature = features.get(i);
			EStructuralFeature efeature = (EStructuralFeature) this.generateModelElement(feature);
			
			if(efeature==null)throw this.getException("generateClass(type)", "Feature \""+feature.getName()+"\"", "Structural Feature Generation Failed");
			
			etype.getEStructuralFeatures().add(efeature);
		}
		
		return etype;
	}
	/**
	 * Translate LEnum to EEnum
	 * @throws Exception 
	 * */
	EEnum generateEnum(LEnum type) throws Exception{
		if(type==null)throw this.getException("generateEnum(type)", "type", "Null");
		EEnum etype = this.factory.createEEnum();
		etype.setInstanceClassName(type.getInstanceName());
		etype.setInstanceTypeName(type.getInstanceName());
		etype.setName(type.getName());
		this.tmap.put(type, etype);
		
		List<LEnumLiteral> literals = type.getLiterals();
		for(int i=0;i<literals.size();i++){
			LEnumLiteral literal = literals.get(i);
			EEnumLiteral eliteral = (EEnumLiteral) this.generateModelElement(literal);
			
			if(eliteral==null)throw this.getException("generateEnum(type)", "type.literals \""+literal.getName()+"\"", "EnumLiteral Generation Failed");
			
			etype.getELiterals().add(eliteral);
		}
		
		return etype;
	}
	/**
	 * Translate LAttribute to EAttribute
	 * @throws Exception 
	 * */
	EAttribute generateAttribute(LAttribute attribute) throws Exception{
		if(attribute==null)
			throw this.getException("generateAttribute(attribute)", "attribute", "Null");
		EAttribute attr = this.factory.createEAttribute();
		attr.setChangeable(attribute.isChangable());
		attr.setLowerBound(attribute.getLowerBound());
		attr.setUpperBound(attribute.getUpperBound());
		if(attribute.getUpperBound()==LMultipleObject.UNBOUNDED)
			attr.setUpperBound(-1);
		attr.setName(attribute.getName());
		attr.setOrdered(attribute.isOrdered());
		attr.setUnique(attribute.isUnique());
		attr.setID(attribute.isRequired());
		this.tmap.put(attribute, attr);
		
		EDataType etype = (EDataType) this.generateModelElement(attribute.getDataType());
		if(etype==null)
			throw this.getException("generateAttribute(attribute)", "attribute.etype: \""+attribute.getDataType().getName()+"\"", "Generation Failed");
		attr.setEType(etype);
		
		return attr;
	}
	/**
	 * Translate LReference to EReference
	 * @throws Exception 
	 * */
	EReference generateReference(LReference reference) throws Exception{
		if(reference==null)
			throw this.getException("generateReference(reference)", "reference", "Null");
		EReference ref = this.factory.createEReference();
		
		ref.setChangeable(reference.isChangable());
		ref.setContainment(reference.isContainment());
		ref.setOrdered(reference.isOrdered());
		ref.setUnique(reference.isUnique());
		ref.setLowerBound(reference.getLowerBound());
		ref.setUpperBound(reference.getUpperBound());
		if(reference.getUpperBound()==LMultipleObject.UNBOUNDED)
			ref.setUpperBound(-1);
		ref.setName(reference.getName());
		this.tmap.put(reference, ref);
		
		ref.setEOpposite((EReference) this.generateModelElement(reference.getOpposite()));
		EClass etype = (EClass) this.generateModelElement(reference.getLClass());
		if(etype==null)
			throw this.getException("generateReference(reference)", "reference.type: \""+reference.getLClass().getName()+"\"", "Generation Failed");
		ref.setEType(etype);
		
		return ref;
	}
	/**
	 * Translate LEnumLiteral to EEnumLiteral
	 * @throws Exception 
	 * */
	EEnumLiteral generateLiteral(LEnumLiteral literal) throws Exception{
		if(literal==null)throw this.getException("generateLiteral(literal)", "literal", "Null");
		EEnumLiteral eliteral = this.factory.createEEnumLiteral();
		eliteral.setName(literal.getName());
		eliteral.setValue(literal.getValue());
		eliteral.setLiteral(literal.getLiteral());
		
		return eliteral;
	}
	/**
	 * Translate LDataType to EDataType
	 * @throws Exception 
	 * */
	EDataType generateDataType(LDataType type) throws Exception{
		if(type==null)
			throw this.getException("generateDataType(type)", "type", "Null");
		
		if(type==LPrimitiveTypeImpl.BOOL){return EcorePackage.Literals.EBOOLEAN;}
		else if(type==LPrimitiveTypeImpl.INT){return EcorePackage.Literals.EINT;}
		else if(type==LPrimitiveTypeImpl.LONG){return EcorePackage.Literals.ELONG;}
		else if(type==LPrimitiveTypeImpl.STRING){return EcorePackage.Literals.ESTRING;}
		else if(type==LPrimitiveTypeImpl.FLOAT){return EcorePackage.Literals.EFLOAT;}
		else if(type==LPrimitiveTypeImpl.DOUBLE){return EcorePackage.Literals.EDOUBLE;}
		else throw this.getException("generateDataType(type)", "type", "Unknown Type: "+type.getClass().getName());
	}

}
