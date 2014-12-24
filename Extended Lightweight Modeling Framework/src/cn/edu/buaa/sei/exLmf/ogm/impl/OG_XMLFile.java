package cn.edu.buaa.sei.exLmf.ogm.impl;

import java.io.File;

import cn.edu.buaa.sei.exLmf.ogm.OG_File;

public class OG_XMLFile implements OG_File{
	
	public static final String ROOT = "root";
	public static final String ID = "_id";
	public static final String REF = "ref";
	public static final String ITEM = "__item";
	
	File file;
	
	public OG_XMLFile(File file) throws Exception{
		if(file==null)throw new Exception("Null file is invalid");
		this.file = file;
	}
	@Override
	public File getFile() {return file;}
	@Override
	public void reset(File file) throws Exception {
		if(file==null)throw new Exception("Null file is invalid");
		this.file = file;
	}

}
