package cn.edu.buaa.exLmf.test;

import java.io.File;
import java.io.FileInputStream;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import cn.edu.buaa.sei.exLmf.manager.IObjectSpace;
import cn.edu.buaa.sei.exLmf.metamodel.LClass;
import cn.edu.buaa.sei.exLmf.metamodel.LClassObject;
import cn.edu.buaa.sei.exLmf.metamodel.LDataObject;
import cn.edu.buaa.sei.exLmf.metamodel.LEnum;
import cn.edu.buaa.sei.exLmf.metamodel.LMultipleObject;
import cn.edu.buaa.sei.exLmf.metamodel.LObject;
import cn.edu.buaa.sei.exLmf.metamodel.LPackage;
import cn.edu.buaa.sei.exLmf.metamodel.LStructuralFeature;
import cn.edu.buaa.sei.exLmf.schema.IObjectReader;
import cn.edu.buaa.sei.exLmf.schema.impl.XMLObjectReader;
import cn.edu.buaa.sei.exLmf.translater.EcoreModelReader;
import cn.edu.buaa.sei.exLmf.translater.IModelReader;

public class DataImporterRunner {

	Number a;
	public static void main(String[] args) {
		try {
			IObjectSpace os = input(new File("R.ecore"),new File("testobj.xml"));
			Collection<LClassObject> objects = os.getAllObjects();
			for(LClassObject obj:objects)
				System.out.println(printLClassObject(obj));
		} catch (Exception e) {
			e.printStackTrace();
		}
		
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
	
	public static IObjectSpace input(File model,File data) throws Exception{
		IModelReader im = new EcoreModelReader("Ecore_Reader");
		im.setInputStream(model);;
		LPackage p = im.read();
		
		IObjectReader reader = new XMLObjectReader("Object_Reader");
		reader.setInputStream(new FileInputStream(data));
		reader.setTemplate(p);
		return reader.translate();
	}

}
