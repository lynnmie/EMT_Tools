package core;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.net.URLDecoder;
public class Test {

	public static void main(String[] args) {
		try {
			System.out.println("Name: "+getClassName(new File("StructAssigner.java")));
			System.out.println(getCurrentPath());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@SuppressWarnings("deprecation")
	public static String getCurrentPath() throws Exception{
		String path = URLDecoder.decode(Class.class.getResource("/").getPath());
		System.out.println(path);
		int k = path.lastIndexOf("/");
		path = path.substring(0, k).trim();
		k = path.lastIndexOf("/");
		path = path.substring(0, k).trim();
		return path+"/";
	}
	public static String getClassName(File file) throws Exception{
		if(file==null||file.isDirectory())throw new Exception("Null file is invalid");
		
		String path = file.getName().trim();
		if(!path.endsWith(".java"))throw new Exception(".java file is required");
		
		int k1 = path.lastIndexOf("\\")+1;
		int k2 = path.lastIndexOf(".");
		String class_name = path.substring(k1, k2).trim();
		
		BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
		String line = null; String pack_name = null;
		while((line=reader.readLine())!=null){
			line=line.trim();
			if(line.startsWith("package")){
				int k = line.lastIndexOf(";");
				pack_name = line.substring(7, k).trim();
			}
		}
		
		reader.close();
		if(pack_name==null)throw new Exception("Invalid java file: "+file.getAbsolutePath());
		
		return pack_name+"."+class_name;
	}
}
