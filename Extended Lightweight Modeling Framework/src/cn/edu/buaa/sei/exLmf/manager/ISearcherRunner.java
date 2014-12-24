package cn.edu.buaa.sei.exLmf.manager;

import java.util.Map;

import cn.edu.buaa.sei.exLmf.metamodel.LModelElement;

public interface ISearcherRunner {
	
	public void install(String name,ILModelSearcher searcher);
	public void uninstall(String name);
	public Boolean isInstalled(String name);
	
	public void setMainObject(LModelElement element);
	public void pushTask(String path) throws Exception;
	public String popTask() throws Exception;
	
	public Object runOne() throws Exception;
	public Map<String,Object> runAll() throws Exception;
}
