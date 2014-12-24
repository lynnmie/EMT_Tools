package cn.edu.buaa.sei.exLmf.schema;
import java.io.InputStream;

import cn.edu.buaa.sei.exLmf.manager.IObjectSpace;
import cn.edu.buaa.sei.exLmf.metamodel.LPackage;

public interface IObjectReader {
	public void setTemplate(LPackage template);
	public void setInputStream(InputStream in);
	public boolean validate();
	public IObjectSpace translate() throws Exception;
}
