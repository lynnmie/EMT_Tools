package cn.edu.buaa.exLmf.test;

import java.io.File;
import java.io.FileOutputStream;

import cn.edu.buaa.sei.exLmf.schema.ISchemaGenerator;
import cn.edu.buaa.sei.exLmf.schema.impl.XMLSchemaGenerator;
import cn.edu.buaa.sei.exLmf.translater.EcoreModelReader;
import cn.edu.buaa.sei.exLmf.translater.IModelReader;

public class SchemaGeneratorRunner {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
			generateSchema(new File("R.ecore"),new File("schema.xsd"));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void generateSchema(File model,File schema) throws Exception{
		IModelReader importer = new EcoreModelReader("Model_Reader");
		ISchemaGenerator generator = new XMLSchemaGenerator("SCDGenerator");
		
		System.out.println("%%Loading the Model File from#: "+model.getAbsolutePath());
		importer.setInputStream(model);;
		generator.setModel(importer.read());
		System.out.println("%%Creating Pipes for output schema file#: "+schema.getAbsolutePath());
		generator.setOPipe(new FileOutputStream(schema));
		
		System.out.println("%%Generation Start!");
		generator.generateSchema();
		System.out.println("%%Generation Finished!");
	}

}
