package cn.edu.buaa.sei.exLmf.ogm;

public interface OGResourceWriter {
	public void setResource(OGResource resource) throws Exception;
	public OGResource getResource();
	
	public IObjectWorld getCache();
	//public void setCache(IObjectWorld cache) throws Exception;
	
	public void write() throws Exception;
}
