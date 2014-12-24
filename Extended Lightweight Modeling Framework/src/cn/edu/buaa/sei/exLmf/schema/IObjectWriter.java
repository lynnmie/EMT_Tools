package cn.edu.buaa.sei.exLmf.schema;


import java.io.OutputStream;

import cn.edu.buaa.sei.exLmf.manager.IObjectSpace;

public interface IObjectWriter {
	public void setOutputStream(OutputStream out);
	public void setObjectSpace(IObjectSpace os);
	public boolean validate();
	public void translate() throws Exception;
}
