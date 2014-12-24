package cn.edu.buaa.sei.exLmf.ogm;

import java.util.Map;

import cn.edu.buaa.sei.exLmf.metamodel.LClass;
import cn.edu.buaa.sei.exLmf.metamodel.LPackage;

public interface IObjectWorld {
	
	public static final String SPLIT = ".";
	public LPackage getModelSpace();
	
	public LClass getModelClass(String path) throws Exception;
	public boolean containModelClass(LClass type);
	public boolean containModelClass(String path);
	public void load(LPackage model) throws Exception;
	
	public IObjectGroup getObjectGroup(String path) throws Exception;
	public IObjectGroup getObjectGroup(LClass type) throws Exception;
	public void clearObjectSpace();
	
	public Map<LClass,IObjectGroup> getGroups();
}

