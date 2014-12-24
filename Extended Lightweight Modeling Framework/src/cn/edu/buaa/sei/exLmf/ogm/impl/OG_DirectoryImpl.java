package cn.edu.buaa.sei.exLmf.ogm.impl;

import java.io.File;

import cn.edu.buaa.sei.exLmf.ogm.OG_Directory;

public class OG_DirectoryImpl implements OG_Directory{
	File directory;
	public OG_DirectoryImpl(File directory) throws Exception{
		if(directory==null||!directory.isDirectory())throw new Exception("Directory required...");
		this.directory = directory;
	}
	@Override
	public File getDirectory() {return this.directory;}
	@Override
	public void setDirectory(File directory) throws Exception {
		if(directory==null||!directory.isDirectory())throw new Exception("Directory required...");
		this.directory = directory;
	}
}
