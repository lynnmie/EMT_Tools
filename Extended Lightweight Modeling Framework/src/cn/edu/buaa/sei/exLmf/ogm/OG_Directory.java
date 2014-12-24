package cn.edu.buaa.sei.exLmf.ogm;

import java.io.File;

public interface OG_Directory extends OGResource{
	public File getDirectory();
	public void setDirectory(File directory) throws Exception;
}
