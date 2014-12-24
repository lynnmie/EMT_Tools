package cn.edu.buaa.sei.exLmf.translater;

import java.io.File;

import cn.edu.buaa.sei.exLmf.metamodel.LPackage;

public interface IModelReader {
	public void setInputStream(File file);
	public Boolean validate() throws Exception;
	public LPackage read() throws Exception;
}
