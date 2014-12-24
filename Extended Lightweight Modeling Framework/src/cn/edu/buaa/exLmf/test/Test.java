package cn.edu.buaa.exLmf.test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import javax.xml.XMLConstants;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.sax.SAXSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.ErrorHandler;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;

import cn.edu.buaa.sei.exLmf.manager.IModelCreator;
import cn.edu.buaa.sei.exLmf.manager.IObjectSpace;
import cn.edu.buaa.sei.exLmf.manager.impl.ModelCreatorImpl;
import cn.edu.buaa.sei.exLmf.manager.impl.ObjectSpace;
import cn.edu.buaa.sei.exLmf.metamodel.LAttribute;
import cn.edu.buaa.sei.exLmf.metamodel.LClass;
import cn.edu.buaa.sei.exLmf.metamodel.LClassObject;
import cn.edu.buaa.sei.exLmf.metamodel.LClassifier;
import cn.edu.buaa.sei.exLmf.metamodel.LDataObject;
import cn.edu.buaa.sei.exLmf.metamodel.LFactory;
import cn.edu.buaa.sei.exLmf.metamodel.LMultipleObject;
import cn.edu.buaa.sei.exLmf.metamodel.LObject;
import cn.edu.buaa.sei.exLmf.metamodel.LPackage;
import cn.edu.buaa.sei.exLmf.metamodel.LReference;
import cn.edu.buaa.sei.exLmf.metamodel.LStructuralFeature;
import cn.edu.buaa.sei.exLmf.metamodel.impl.LAttributeImpl;
import cn.edu.buaa.sei.exLmf.metamodel.impl.LClassImpl;
import cn.edu.buaa.sei.exLmf.metamodel.impl.LPackageImpl;
import cn.edu.buaa.sei.exLmf.metamodel.impl.LPrimitiveTypeImpl;
import cn.edu.buaa.sei.exLmf.metamodel.impl.LReferenceImpl;
import cn.edu.buaa.sei.exLmf.metamodel.impl.LTypedElementImpl;
import cn.edu.buaa.sei.exLmf.translater.EcoreModelReader;
import cn.edu.buaa.sei.exLmf.translater.EcoreModelWriter;
import cn.edu.buaa.sei.exLmf.translater.IModelReader;
import cn.edu.buaa.sei.exLmf.translater.IModelWriter;

public class Test {
	public static void main(String[] args) {
		/*LPackage p = createPackage1();
		List<LClassifier> types = p.getTypes();
		for(int i=0;i<types.size();i++){
			LClass type = (LClass) types.get(i);
			System.out.println(printClass(type));
		}*/
		/*LPackage p;
		try {
			p = createPackage2();
			IModelPrinter printer = new ModelPrinter("Printer");
			System.out.println(printer.printLPackage(p));
			
			List<LClassifier> types = p.getTypes();
			for(int i=0;i<types.size();i++){
				LClassifier type = types.get(i);
				if(type instanceof LClass)
					System.out.println(printer.printLClass((LClass) type));
			}
			
			LClass HLR = (LClass) p.getClassifierByName("HLR");
			IObjectSpace os = new ObjectSpace("TEST_II_OS",p);
			
			//LDataObject obj = os.createDataObject(LPrimitiveTypeImpl.INT, "15");
			os.createClassObject(HLR);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		
		/*IObjectSpace os = createSpace1();
		IObjectWriter writer = new XMLObjectWriter("PROM_II");
		writer.setObjectSpace(os);
		writer.setResource(new File("obj.xml"));
		try {
			writer.translate();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		
		
		/*IObjectSpace os = createSpace1();
		IObjectWriter ow = new XMLObjectWriter("TEST_III");
		ow.setObjectSpace(os);
		try {
			ow.setOutputStream(new FileOutputStream(new File("obj.xml")));
			ow.translate();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		
		/*try {
			LPackage p = createPackage1();
			IObjectReader oi = new XMLObjectReader("READER I");
			oi.setInputStream(new FileInputStream("obj.xml"));
			oi.setTemplate(p);
			IObjectSpace os = oi.translate();
			
			Collection<LClassObject> objs = os.getAllObjects();
			for(LClassObject obj:objs)
				System.out.println(printLClassObject(obj));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		System.out.println("Creating Model Reader...");
		IModelReader reader = new EcoreModelReader("TEST-1");
		System.out.println("Config the input ecore file: R.ecore");
		reader.setInputStream(new File("R.ecore"));
		try {
			LPackage p = reader.read();
			System.out.println("Reading LPackage Successed");
			
			System.out.println("Creating Model Writer...");
			IModelWriter writer = new EcoreModelWriter("TEST-1");
			System.out.println("Config the output ecore file: out.ecore");
			writer.setOutputStream(new File("out.ecore"));
			System.out.println("Config the Model");
			writer.setModel(p);
			
			writer.write();
			System.out.println("Writting Finished!");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
	}
	
	public static void testSchemaObject(File xsd,File xml){
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		factory.setNamespaceAware(true);
		SchemaFactory schemaFactory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
		try {
			Schema schema = schemaFactory.newSchema(new SAXSource(new InputSource(new FileInputStream(xsd))));
			factory.setSchema(schema);
			DocumentBuilder builder = factory.newDocumentBuilder();
			builder.setErrorHandler(new ErrorHandler() {  
		          @Override  
		          public void warning(SAXParseException exception) throws SAXException {  
		              throw new RuntimeException(exception);  
		          }  
		          @Override  
		          public void fatalError(SAXParseException exception) throws SAXException {  
		              throw new RuntimeException(exception);  
		          }  
		          @Override  
		          public void error(SAXParseException exception) throws SAXException {  
		              throw new RuntimeException(exception);  
		          }  
		      });
			
			Document document = builder.parse(new FileInputStream(xml));
			System.out.println(document);
			Element root = (Element) document.getChildNodes().item(0);
			System.out.println(root.getTagName());
		} catch (FileNotFoundException | SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void test3(){
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		factory.setNamespaceAware(true);
		SchemaFactory schemaFactory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
		try {
			Schema schema = schemaFactory.newSchema(new SAXSource(new InputSource(new FileInputStream(new File("test.xsd")))));
			factory.setSchema(schema);
			DocumentBuilder builder = factory.newDocumentBuilder();
			builder.setErrorHandler(new ErrorHandler() {  
		          @Override  
		          public void warning(SAXParseException exception) throws SAXException {  
		              throw new RuntimeException(exception);  
		          }  
		          @Override  
		          public void fatalError(SAXParseException exception) throws SAXException {  
		              throw new RuntimeException(exception);  
		          }  
		          @Override  
		          public void error(SAXParseException exception) throws SAXException {  
		              throw new RuntimeException(exception);  
		          }  
		      });
			
			Document document = builder.parse(new FileInputStream(new File("test.xml")));
			System.out.println(document);
			Element root = (Element) document.getChildNodes().item(0);
			System.out.println(root.getTagName());
		} catch (FileNotFoundException | SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public static void test2(){
		/*IObjectImporter im = new XMLObjectImporter("TEST_II");
		im.setModel(createPackage1());
		im.setResource(new File("obj.xml"));
		try {
			IObjectSpace os = im.translate();
			Collection<LClassObject> objs = os.getAllObjects();
			for(LClassObject obj:objs){
				System.out.println(printLClassObject(obj));
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		test1();*/
	}
	
	public static IObjectSpace createSpace1() throws Exception{
		LPackage p = createPackage1();
		IObjectSpace os = new ObjectSpace("OSpace",p);
		
		LClass person = (LClass) p.getClassifierByName("Person");
		LClassObject a = os.createClassObject(person);
		a.set(person.getFeatureByName("name"), os.createDataObject(LPrimitiveTypeImpl.STRING, "John Horse"));
		a.set(person.getFeatureByName("age"),os.createDataObject(LPrimitiveTypeImpl.INT, "20"));
		a.set(person.getFeatureByName("tall"), os.createDataObject(LPrimitiveTypeImpl.FLOAT, "180.0"));
		
		LClassObject b = os.createClassObject(person);
		b.set(person.getFeatureByName("name"), os.createDataObject(LPrimitiveTypeImpl.STRING, "John Steve"));
		b.set(person.getFeatureByName("age"),os.createDataObject(LPrimitiveTypeImpl.INT, "50"));
		b.set(person.getFeatureByName("tall"), os.createDataObject(LPrimitiveTypeImpl.FLOAT, "170.0"));
		
		LClassObject c = os.createClassObject(person);
		c.set(person.getFeatureByName("name"), os.createDataObject(LPrimitiveTypeImpl.STRING, "QA"));
		c.set(person.getFeatureByName("age"),os.createDataObject(LPrimitiveTypeImpl.INT, "48"));
		c.set(person.getFeatureByName("tall"), os.createDataObject(LPrimitiveTypeImpl.FLOAT, "170.0"));
		
		a.set(person.getFeatureByName("father"), b);
		a.set(person.getFeatureByName("mother"), c);
		
		a.add(person.getFeatureByName("friends"), b);
		a.add(person.getFeatureByName("friends"), c);
		
		return os;
	}
	
	
	public static void test1() throws Exception{
		LPackage p = createPackage1();
		
		System.out.println(p.getName());
		System.out.println(p.getNsPrefix());
		System.out.println(p.getNsURI());
		
		List<LClassifier> types = p.getTypes();
		System.out.println(types.size());
		
		LFactory factory = p.getFactory();
		LClass person = (LClass) p.getClassifierByName("Person");
		LClassObject a = factory.create(person);
		a.set(person.getFeatureByName("name"), factory.create(LPrimitiveTypeImpl.STRING, "John Horse"));
		a.set(person.getFeatureByName("age"),factory.create(LPrimitiveTypeImpl.INT, "20"));
		a.set(person.getFeatureByName("tall"), factory.create(LPrimitiveTypeImpl.FLOAT, "180.0"));
		
		LClassObject b = factory.create(person);
		b.set(person.getFeatureByName("name"), factory.create(LPrimitiveTypeImpl.STRING, "John Steve"));
		b.set(person.getFeatureByName("age"),factory.create(LPrimitiveTypeImpl.INT, "50"));
		b.set(person.getFeatureByName("tall"), factory.create(LPrimitiveTypeImpl.FLOAT, "170.0"));
		
		LClassObject c = factory.create(person);
		c.set(person.getFeatureByName("name"), factory.create(LPrimitiveTypeImpl.STRING, "QA"));
		c.set(person.getFeatureByName("age"),factory.create(LPrimitiveTypeImpl.INT, "48"));
		c.set(person.getFeatureByName("tall"), factory.create(LPrimitiveTypeImpl.FLOAT, "170.0"));
		
		a.set(person.getFeatureByName("father"), b);
		a.set(person.getFeatureByName("mother"), c);
		
		a.add(person.getFeatureByName("friends"), b);
		a.add(person.getFeatureByName("friends"), c);
		
		System.out.println(printLClassObject(a));
		System.out.println(printLClassObject(b));
		System.out.println(printLClassObject(c));
	}
	
	public static LPackage createPackage1() throws Exception{
		LPackage p = new LPackageImpl("test","www.example.com/Test","Test");
		
		LClass person = new LClassImpl("Person",p);person.setClassifierID(0);
		LClass family = new LClassImpl("Family",p);family.setClassifierID(1);
		LClass house = new LClassImpl("House",null);house.setClassifierID(2);
		
		LAttribute p_name = new LAttributeImpl(0,"name",person);
		LAttribute p_age  = new LAttributeImpl(1,"age",person);
		LAttribute p_tall = new LAttributeImpl(2,"tall",null);
		
		p_name.setDataType(LPrimitiveTypeImpl.STRING);
		p_name.setUpperBound(1);p_name.setLowerBound(0);
		p_age.setDataType(LPrimitiveTypeImpl.INT);
		p_age.setUpperBound(1);p_age.setLowerBound(0);
		p_tall.setDataType(LPrimitiveTypeImpl.FLOAT);
		p_tall.setUpperBound(1);p_tall.setLowerBound(0);
		
		person.addAttribute(p_name);
		person.addAttribute(p_age);
		person.addAttribute(p_tall);
		
		LReference p_parant = new LReferenceImpl(3,"father",person);
		LReference p_mother = new LReferenceImpl(4,"mother",null);
		LReference p_friend = new LReferenceImpl(5,"friends",null);
		
		p_parant.setContainment(true);
		p_mother.setContainment(false);
		p_friend.setContainment(true);
		p_parant.setLClass(person);
		p_mother.setLClass(person);
		p_friend.setLClass(person);
		p_parant.setLowerBound(0);p_parant.setUpperBound(1);
		p_friend.setLowerBound(0);p_friend.setUpperBound(LTypedElementImpl.UNBOUNDED);
		
		person.addReference(p_parant);
		person.addReference(p_mother);
		person.addReference(p_friend);
		
		LReference f_members = new LReferenceImpl(0,"members",null);
		LReference f_house = new LReferenceImpl(1,"house",null);
		
		f_members.setLClass(person);
		f_members.setUpperBound(10);
		f_house.setLClass(family);
		
		family.addFeature(f_members);
		family.addFeature(f_house);
	
		p.addType(person);
		p.addType(family);
		p.addType(house);
		
		return p;
	}

	@SuppressWarnings("static-access")
	public static LPackage createPackage2() throws Exception{
		IModelCreator manager = new ModelCreatorImpl("TEST_II");
		LPackage p = manager.getRoot();
		
		LClass requirement = manager.createAbstractClass(p, "Requirement");
		manager.createAttribute(requirement, "rid", LPrimitiveTypeImpl.STRING);
		
		LClass hlr = manager.createClass(p, "HLR");
		LClass llr = manager.createClass(p, "LLR");
		LClass sr = manager.createClass(p, "SR");
		
		hlr.addSuperType(requirement);
		llr.addSuperType(requirement);
		sr.addSuperType(requirement);
		
		manager.createMultipleReference(sr, "hlrs", hlr, 1, LMultipleObject.UNBOUNDED, manager.UNIQUE_INORDER);
		manager.createMultipleReference(hlr, "llrs", llr, 1, LMultipleObject.UNBOUNDED, manager.UNIQUE_INORDER);
		
		return p;
	}
	
	public static String printClass(LClass type){
		if(type==null)return null;
		StringBuilder code = new StringBuilder();
		
		code.append(type.getName()).append("[").append(type.getClassifierID()).append("]:");
		if(type.isAbstract())code.append("<abstract>");
		if(type.isFinal())code.append("<final>");
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
	public static String printLClassObject(LClassObject obj) throws Exception{
		if(obj==null)return null;
		LClass type = obj.getType();
		StringBuilder code = new StringBuilder();
		
		code.append("{").append("\n\t<<").append(obj.hashCode()).append(">>");
		List<LStructuralFeature> features = type.getAllFeatures();
		for(int i=0;i<features.size();i++){
			LStructuralFeature feature = features.get(i);
			LObject val = obj.get(feature);
			
			code.append("\n\t").append(feature.getName()).append("[").append(feature.getType().getName()).append("]: ");
			if(val==null)code.append("null");
			else if(val instanceof LDataObject)code.append(printDataObject((LDataObject) val));
			else if(val instanceof LClassObject)code.append(val.hashCode());
			else if(val instanceof LMultipleObject) code.append(printMultipleObject((LMultipleObject) val));
			else code.append("Unknown");
		}
		
		code.append("}");
		return code.toString();
	}
	public static String printDataObject(LDataObject obj){
		if(obj==null)return null;
		return obj.getValue().toString();
	}
	public static String printMultipleObject(LMultipleObject obj) throws Exception{
		if(obj==null)return null;
		StringBuilder code = new StringBuilder();
		
		if(obj.isUnique())code.append("{");
		else code.append("[");
		
		Collection<LObject> list = obj.getAllObjects();
		Iterator<LObject> itor = list.iterator();
		
		while(itor.hasNext()){
			LObject elm = itor.next();
			if(elm instanceof LDataObject)code.append(printDataObject((LDataObject) elm));
			else code.append(elm.hashCode());
			if(itor.hasNext())code.append(", ");
		}
		
		if(obj.isUnique())code.append("}");
		else code.append("]");
		return code.toString();
	}
}
