package cn.edu.buaa.sei.exLmf.ogm;

import java.io.File;

public interface OG_File extends OGResource{
	public File getFile();
	public void reset(File file) throws Exception;
}
