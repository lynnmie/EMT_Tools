package cn.edu.buaa.sei.exLmf.ogm.impl;

import java.io.File;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import cn.edu.buaa.sei.exLmf.metamodel.LClass;
import cn.edu.buaa.sei.exLmf.metamodel.LClassObject;
import cn.edu.buaa.sei.exLmf.metamodel.LDataObject;
import cn.edu.buaa.sei.exLmf.metamodel.LEnum;
import cn.edu.buaa.sei.exLmf.metamodel.LMultipleObject;
import cn.edu.buaa.sei.exLmf.metamodel.LObject;
import cn.edu.buaa.sei.exLmf.metamodel.LPackage;
import cn.edu.buaa.sei.exLmf.metamodel.LStructuralFeature;
import cn.edu.buaa.sei.exLmf.ogm.IObjectGroup;
import cn.edu.buaa.sei.exLmf.ogm.IObjectWorld;
import cn.edu.buaa.sei.exLmf.ogm.OGResource;
import cn.edu.buaa.sei.exLmf.ogm.OGResourceReader;
import cn.edu.buaa.sei.exLmf.ogm.OGResourceWriter;
import cn.edu.buaa.sei.exLmf.translater.EcoreModelReader;
import cn.edu.buaa.sei.exLmf.translater.IModelReader;

public class Test {

	public static void main(String[] args) {
		/*try {
			LPackage root = readModel(new File("test.ecore"));
			IObjectWorld cache = getCache(root);
			
			System.out.println(root.getContainer());
			LClass type = (LClass) root.getClassifierByName("Person");
			System.out.println(type.getAbsolutePath());
			System.out.println(ModelAccessor.access(root, "Person"));
			System.out.println(cache.containModelClass("Person"));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		
		test2();
	}
	
	public static void test2(){
		XMLDBReader reader = new XMLDBReader();
		try {
			reader.setResource(new OG_DirectoryImpl(new File("data")));
			reader.read();
			System.out.println("Reading Complete!!!");
			IObjectWorld cache = reader.getCache();
			
			
			Map<LClass,IObjectGroup> groups = cache.getGroups();
			Set<LClass> types = groups.keySet();
			for(LClass type:types){
				IObjectGroup group = cache.getObjectGroup(type);
				System.out.println("****************** "+type.getAbsolutePath()+" *********************");
				Set<LClassObject> objs = group.getObjects();
				for(LClassObject obj:objs){
					System.out.println("========================== "+obj.hashCode()+" =============================");
					System.out.println(printLClassObject(obj));
				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void readTest(){
		try {
			LPackage template = readModel(new File("test.ecore"));
			IObjectWorld cache = getCache(template);
			OGResourceReader reader = new XMLFileReader(cache);
			//readObject(new File("test.xml"),cache);
			
			OG_XMLFile[] files = {new OG_XMLFile(new File("test.xml")),new OG_XMLFile(new File("test2.xml"))};
			
			reader.setResource(files[0]);
			reader.read();
			
			reader.setResource(files[1]);
			reader.read();
			
			reader.setResource(files[0]);
			reader.link();
			
			Map<LClass,IObjectGroup> groups = cache.getGroups();
			Set<LClass> types = groups.keySet();
			for(LClass type:types){
				IObjectGroup group = cache.getObjectGroup(type);
				System.out.println("****************** "+type.getAbsolutePath()+" *********************");
				Set<LClassObject> objs = group.getObjects();
				for(LClassObject obj:objs){
					System.out.println("========================== "+obj.hashCode()+" =============================");
					System.out.println(printLClassObject(obj));
				}
			}
			
			OGResourceWriter writer = new XMLDBWriter(cache);
			writer.setResource(new OG_DirectoryImpl(new File("data")));
			writer.write();
			System.out.println("Writting Successfully!");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public static LPackage readModel(File file) throws Exception{
		if(file==null)throw new Exception("Null file is invalid");
		
		IModelReader modelReader = new EcoreModelReader("EcoreReader");
		modelReader.setInputStream(file);
		LPackage root = modelReader.read();
		return root;
	}
	public static IObjectWorld getCache(LPackage template) throws Exception{
		if(template==null)throw new Exception("Null template is invalid");
		return new ObjectWorld(template);
	}
	public static void readObject(File file,IObjectWorld cache) throws Exception{
		if(file==null||cache==null)throw new Exception("Null cache|file is invalid");
		
		OG_XMLFile resource = new OG_XMLFile(file);
		OGResourceReader reader = new XMLFileReader(cache);
		reader.setResource((OGResource) resource);
		reader.read();
	}
	
	
	public static String printLClassObject(LClassObject obj) throws Exception{
		if(obj==null)return null;
		LClass type = obj.getType();
		StringBuilder code = new StringBuilder();
		
		code.append("{").append("\n\t<<").append(obj.getClass().getName()).append("@").append(obj.hashCode()).append(">>");
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
		
		code.append("\n}");
		return code.toString();
	}
	public static String printDataObject(LDataObject obj) throws Exception{
		if(obj==null)return null;
		if(obj.type() instanceof LEnum)
			return obj.literalVal().getLiteral();
		else
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
