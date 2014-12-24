package cn.edu.buaa.sei.exLmf.translater;

import java.io.File;

import cn.edu.buaa.sei.exLmf.metamodel.LPackage;

public interface IModelWriter {
	public void setOutputStream(File file);
	public void setModel(LPackage p);
	public Boolean validate();
	public void write() throws Exception;
}
